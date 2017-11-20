/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.mobicents.slee.resource.map.heartbeat;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.slee.resource.ResourceAdaptorContext;
import javax.slee.facilities.Tracer;

import org.mobicents.tools.heartbeat.api.Protocol;
import org.mobicents.tools.sip.balancer.NodeRegisterRMIStub;
import org.mobicents.tools.sip.balancer.SIPNode;

/**
 * <p>
 * implementation of the <code>LoadBalancerHeartBeatingService</code> interface.
 * </p>
 * 
 * <p>
 * It sends heartbeats and health information to the sip balancers configured
 * through the stack property org.mobicents.ha.javax.sip.BALANCERS
 * </p>
 * 
 * @author <A HREF="mailto:jean.deruelle@gmail.com">Jean Deruelle</A>
 *
 */
public class MAPLoadBalancerHeartBeatingServiceImpl
		implements MAPLoadBalancerHeartBeatingService, MAPLoadBalancerHeartBeatingServiceImplMBean {

	private Tracer tracer;
	public static String LB_HB_SERVICE_MBEAN_NAME = "org.mobicents.resources.restcomm-slee-ra-map-ra:type=load-balancer-heartbeat-service,name=";
	
	public final static String LOCAL_ADDRESS = "org.mobicents.resources.restcomm-slee-ra-map-ra.LOCAL_ADDRESS";

	public final static String POINTCODE = "org.mobicents.resources.restcomm-slee-ra-map-ra.POINTCODE";
	public final static String SCTP_PORT = "org.mobicents.resources.restcomm-slee-ra-map-ra.SCTP_PORT";
	public final static String SCTP_LB_PORT = "org.mobicents.resources.restcomm-slee-ra-map-ra.SCTP_LB_PORT";
	
	public static final int DEFAULT_RMI_PORT = 2000;
	public static final String BALANCER_PORT_CHAR_SEPARATOR = ":";
	public static final String BALANCERS_CHAR_SEPARATOR = ";";

	MBeanServer mBeanServer = null;
	String stackName = null;
	Properties stackProperties = null;
	// the balancers to send heartbeat to and our health info
	protected String balancers;
	// the jvmRoute for this node
	protected String jvmRoute;
	// the balancers names to send heartbeat to and our health info
	protected Map<String, MAPLoadBalancer> register = new ConcurrentHashMap<String, MAPLoadBalancer>();
	// heartbeat interval, can be modified through JMX
	protected long heartBeatInterval = 5000;
	protected Timer heartBeatTimer = new Timer();
	protected TimerTask hearBeatTaskToRun = null;
	protected List<String> cachedAnyLocalAddresses = new ArrayList<String>();
	protected boolean started = false;
	protected boolean gracefullyShuttingDown = false;
	protected String sessionId;

	protected Set<MAPLoadBalancerHeartBeatingListener> loadBalancerHeartBeatingListeners;
	// Caching the sipNodes to send to the LB as there is no reason for them to
	// change often or at all after startup
	ConcurrentHashMap<String, SIPNode> sipNodes = new ConcurrentHashMap<String, SIPNode>();

	ObjectName oname = null;

	public MAPLoadBalancerHeartBeatingServiceImpl() {
		loadBalancerHeartBeatingListeners = new CopyOnWriteArraySet<MAPLoadBalancerHeartBeatingListener>();
	}

	@Override
	public void init(ResourceAdaptorContext context,MBeanServer mBeanServer, String stackName, Properties stackProperties) {
		this.tracer=context.getTracer(MAPLoadBalancerHeartBeatingServiceImpl.class.getSimpleName());
		this.mBeanServer = mBeanServer;
		this.stackName = stackName;
		this.stackProperties = stackProperties;
		this.balancers = stackProperties.getProperty(BALANCERS);
		this.heartBeatInterval = Integer.parseInt(stackProperties.getProperty(HEARTBEAT_INTERVAL, "5000"));
	}

	public void stopBalancer() {
		stop();
	}

	@Override
	public void start() {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				stopBalancer();
				tracer.info("Shutting down the Load Balancer Link");
			}
		});

		if (!started) {
			if (balancers != null && balancers.length() > 0) {
				String[] balancerDescriptions = balancers.split(BALANCERS_CHAR_SEPARATOR);
				for (String balancerDescription : balancerDescriptions) {
					String balancerAddress = balancerDescription;
					int rmiPort = DEFAULT_RMI_PORT;
					if (balancerDescription.indexOf(BALANCER_PORT_CHAR_SEPARATOR) != -1) {
						String[] balancerDescriptionSplitted = balancerDescription.split(BALANCER_PORT_CHAR_SEPARATOR);
						balancerAddress = balancerDescriptionSplitted[0];
						try {
							rmiPort = Integer.parseInt(balancerDescriptionSplitted[1]);
						} catch (NumberFormatException e) {
							tracer.severe("Impossible to parse the following sip balancer port "
									+ balancerDescriptionSplitted[1], e);
						}
					}
					
					this.addBalancer(balancerAddress, rmiPort);
				}
			}
			started = true;
			sessionId = "" + System.currentTimeMillis();
		}
		if (sipNodes.isEmpty()) {
			tracer.info("Computing SIP Nodes to be sent to the LB");
			updateConnectorsAsSIPNode();
		}
		this.hearBeatTaskToRun = new BalancerPingTimerTask();

		// Delay the start with 2 seconds so nodes joining under load are really
		// ready to serve requests
		// Otherwise one of the listeneing points comes a bit later and results
		// in errors.
		this.heartBeatTimer.scheduleAtFixedRate(this.hearBeatTaskToRun, this.heartBeatInterval, this.heartBeatInterval);
		if (tracer.isFinestEnabled()) {
			tracer.finest("Created and scheduled tasks for sending heartbeats to the sip balancer every "
					+ heartBeatInterval + "ms.");
		}

		registerMBean();

		if (tracer.isFinestEnabled()) {
			tracer.finest("Load Balancer Heart Beating Service has been started");
		}
	}

	@Override
	public void stop() {
		// Force removal from load balancer upon shutdown
		// added for Issue 308
		// (http://code.google.com/p/mobicents/issues/detail?id=308)
		removeNodesFromBalancers();
		// cleaning
		// balancerNames.clear();
		register.clear();
		if (hearBeatTaskToRun != null) {
			this.hearBeatTaskToRun.cancel();
		}
		this.hearBeatTaskToRun = null;
		loadBalancerHeartBeatingListeners.clear();
		started = false;

		heartBeatTimer.cancel();

		unRegisterMBean();

		if (tracer.isFinestEnabled()) {
			tracer.finest("Load Balancer Heart Beating Service has been stopped");
		}
	}

	protected void registerMBean() {
		String mBeanName = LB_HB_SERVICE_MBEAN_NAME + this.stackName;
		try {
			oname = new ObjectName(mBeanName);
			if (mBeanServer != null && !mBeanServer.isRegistered(oname)) {
				mBeanServer.registerMBean(this, oname);
			}
		} catch (Exception e) {
			tracer.severe(
					"Could not register the Load Balancer Service as an MBean under the following name " + mBeanName,
					e);
		}
	}

	protected void unRegisterMBean() {
		String mBeanName = LB_HB_SERVICE_MBEAN_NAME + this.stackName;
		try {
			if (oname != null && mBeanServer != null && mBeanServer.isRegistered(oname)) {
				mBeanServer.unregisterMBean(oname);
			}
		} catch (Exception e) {
			tracer.severe("Could not unregister the stack as an MBean under the following name" + mBeanName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getHeartBeatInterval() {
		return heartBeatInterval;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHeartBeatInterval(long heartBeatInterval) {
		if (heartBeatInterval < 100)
			return;

		if (tracer.isFinestEnabled()) {
			tracer.finest("Setting HeartBeatInterval from " + this.heartBeatInterval + " to " + heartBeatInterval);
		}

		this.heartBeatInterval = heartBeatInterval;
		if (sipNodes.isEmpty()) {
			tracer.info("Computing SIP Nodes to be sent to the LB");
			updateConnectorsAsSIPNode();
		}
		this.hearBeatTaskToRun.cancel();
		this.hearBeatTaskToRun = new BalancerPingTimerTask();
		this.heartBeatTimer.scheduleAtFixedRate(this.hearBeatTaskToRun, 0, this.heartBeatInterval);

	}

	/**
	 * 
	 * @param hostName
	 * @param index
	 * @return
	 */
	private InetAddress fetchHostAddress(String hostName, int index) {
		if (hostName == null)
			throw new NullPointerException("Host name cant be null!!!");

		InetAddress[] hostAddr = null;
		try {
			hostAddr = InetAddress.getAllByName(hostName);
		} catch (UnknownHostException uhe) {
			throw new IllegalArgumentException("HostName is not a valid host name or it doesnt exists in DNS", uhe);
		}

		if (index < 0 || index >= hostAddr.length) {
			throw new IllegalArgumentException("Index in host address array is wrong, it should be [0]<x<["
					+ hostAddr.length + "] and it is [" + index + "]");
		}

		InetAddress address = hostAddr[index];
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getBalancers() {
		return this.register.keySet().toArray(new String[register.keySet().size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addBalancer(String addr, int rmiPort) {
		
		if (addr == null)
			throw new NullPointerException("addr cant be null!!!");

		InetAddress address = null;
		try {
			address = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Something wrong with host creation.", e);
		}
		String balancerName = address.getCanonicalHostName() + ":" + rmiPort;

		if (register.get(balancerName) != null) {
			tracer.info("Sip balancer " + balancerName + " already present, not added");
			return false;
		}

		MAPLoadBalancer sipLoadBalancer = new MAPLoadBalancer(this, address, rmiPort);
		register.put(balancerName, sipLoadBalancer);

		// notify the listeners
		for (MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener : loadBalancerHeartBeatingListeners) {
			loadBalancerHeartBeatingListener.loadBalancerAdded(sipLoadBalancer);
		}

		if (tracer.isFinestEnabled()) {
			tracer.finest("following balancer name : " + balancerName + "/address:" + addr + " added");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addBalancer(String hostName, int index, int rmiPort) {
		return this.addBalancer(fetchHostAddress(hostName, index).getHostAddress(), rmiPort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeBalancer(String addr, int rmiPort) {
		if (addr == null)
			throw new NullPointerException("addr cant be null!!!");

		InetAddress address = null;
		try {
			address = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Something wrong with host creation.", e);
		}

		MAPLoadBalancer sipLoadBalancer = new MAPLoadBalancer(this, address, rmiPort);

		String keyToRemove = null;
		Iterator<String> keyIterator = register.keySet().iterator();
		while (keyIterator.hasNext() && keyToRemove == null) {
			String key = keyIterator.next();
			if (register.get(key).equals(sipLoadBalancer)) {
				keyToRemove = key;
			}
		}

		if (keyToRemove != null) {
			register.remove(keyToRemove);

			// notify the listeners
			for (MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener : loadBalancerHeartBeatingListeners) {
				loadBalancerHeartBeatingListener.loadBalancerRemoved(sipLoadBalancer);
			}

			if (tracer.isFinestEnabled()) {
				tracer.finest("following balancer name : " + keyToRemove + "/address:" + addr + " removed");
			}

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeBalancer(String hostName, int index, int rmiPort) {
		InetAddress[] hostAddr = null;
		try {
			hostAddr = InetAddress.getAllByName(hostName);
		} catch (UnknownHostException uhe) {
			throw new IllegalArgumentException("HostName is not a valid host name or it doesnt exists in DNS", uhe);
		}

		if (index < 0 || index >= hostAddr.length) {
			throw new IllegalArgumentException("Index in host address array is wrong, it should be [0]<x<["
					+ hostAddr.length + "] and it is [" + index + "]");
		}

		InetAddress address = hostAddr[index];

		return this.removeBalancer(address.getHostAddress(), rmiPort);
	}

	protected void updateConnectorsAsSIPNode() {

		if (tracer.isFineEnabled()) {
			tracer.fine("Gathering connector information");
		}

		String pointcode = stackProperties.getProperty(POINTCODE);
		String sctpSrcPortString = stackProperties.getProperty(SCTP_PORT);
		String sctpDstPortString = stackProperties.getProperty(SCTP_LB_PORT);
		String ipAddress = stackProperties.getProperty(LOCAL_ADDRESS);
		if (ipAddress == null) {
			if (tracer.isFineEnabled()) {
				tracer.fine("Following IpAddress [" + ipAddress
						+ "] is null not pinging the LB for that null IP or it will cause routing issues");
			}
		} else {
			if (tracer.isFineEnabled()) {
				tracer.fine(
						"Creating new SIP Node for [" + ipAddress + "] to be added to the list for pinging the LB");
			}
			SIPNode node = new SIPNode(ipAddress, ipAddress);
			SIPNode previousValue = sipNodes.putIfAbsent(ipAddress, node);
			if (previousValue == null) {
				if (tracer.isFineEnabled()) {
					tracer.fine("Added a sip Node with the key [" + ipAddress + "]");
				}
			} else {
				node = previousValue;
				if (tracer.isFineEnabled()) {
					tracer.fine("SIPNode " + node + " was already present");
				}
			}

			if (pointcode != null) {
				node.getProperties().put("pointCode", pointcode);
			}
			
			if (sctpSrcPortString != null) {
				int sctpSrcPort = Integer.parseInt(sctpSrcPortString);
				node.getProperties().put(Protocol.SCTP_PORT, sctpSrcPort);
			}

			if (sctpDstPortString != null) {
				int sctpDstPort = Integer.parseInt(sctpDstPortString);
				node.getProperties().put("sctpPortLb", sctpDstPort);
			}
			
			if (jvmRoute != null)
				node.getProperties().put("jvmRoute", jvmRoute);

			node.getProperties().put("version", System.getProperty("org.mobicents.server.version", "0"));
			node.getProperties().put("sessionId", sessionId);

			if (gracefullyShuttingDown) {
				if (tracer.isFinestEnabled()) {
					tracer.finest("Adding GRACEFUL_SHUTDOWN prop to following SIP Node " + node);
				}
				Map<String, Serializable> properties = node.getProperties();
				properties.put("GRACEFUL_SHUTDOWN", "true");
			}

			if (tracer.isFineEnabled()) {
				tracer.fine("Added node [" + node + "] to the list for pinging the LB");
			}
		}
	}

	/**
	 * @param info
	 */
	protected void sendKeepAliveToBalancers() {
		if (sipNodes.isEmpty()) {
			tracer.info("Computing SIP Nodes to be sent to the LB as the list is currently empty");
			updateConnectorsAsSIPNode();
		}
		ArrayList<SIPNode> info = new ArrayList<SIPNode>(sipNodes.values());
		if (tracer.isFineEnabled()) {
			tracer.fine("Pinging balancers with info[" + info + "]");
		}
		Thread.currentThread().setContextClassLoader(NodeRegisterRMIStub.class.getClassLoader());
		for (MAPLoadBalancer balancerDescription : new HashSet<MAPLoadBalancer>(register.values())) {
			try {
				long startTime = System.currentTimeMillis();
				Registry registry = LocateRegistry.getRegistry(balancerDescription.getAddress().getHostAddress(),
						balancerDescription.getRmiPort());
				NodeRegisterRMIStub reg = (NodeRegisterRMIStub) registry.lookup("SIPBalancer");

				if (tracer.isFineEnabled()) {
					tracer.fine("Pinging the LB with the following Node Info [" + info + "]");
				}
				// https://github.com/RestComm/jain-sip.ha/issues/14
				// notify the listeners
				for (MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener : loadBalancerHeartBeatingListeners) {
					loadBalancerHeartBeatingListener.pingingloadBalancer(balancerDescription);
				}
				reg.handlePing(info);
				// notify the listeners
				for (MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener : loadBalancerHeartBeatingListeners) {
					loadBalancerHeartBeatingListener.pingedloadBalancer(balancerDescription);
				}
				if (tracer.isFineEnabled()) {
					tracer.fine("Pinged the LB with the following Node Info [" + info + "]");
				}
				balancerDescription.setDisplayWarning(true);
				if (!balancerDescription.isAvailable()) {
					tracer.info("Keepalive: SIP Load Balancer Found! " + balancerDescription);
				}
				balancerDescription.setAvailable(true);
				startTime = System.currentTimeMillis() - startTime;
				if (startTime > 200)
					tracer.warning(
							"Heartbeat sent too slow in " + startTime + " millis at " + System.currentTimeMillis());

			} catch (IOException e) {
				e.printStackTrace();
				balancerDescription.setAvailable(false);
				if (balancerDescription.isDisplayWarning()) {
					tracer.warning(
							"sendKeepAlive: Cannot access the SIP load balancer RMI registry: " + e.getMessage()
									+ "\nIf you need a cluster configuration make sure the SIP load balancer is running. Host "
									+ balancerDescription.toString());
				}
				balancerDescription.setDisplayWarning(false);
			} catch (Exception e) {
				balancerDescription.setAvailable(false);
				if (balancerDescription.isDisplayWarning()) {
					tracer.severe("sendKeepAlive: Cannot access the SIP load balancer RMI registry: " + e.getMessage()
							+ "\nIf you need a cluster configuration make sure the SIP load balancer is running. Host "
							+ balancerDescription.toString(), e);
				}
				balancerDescription.setDisplayWarning(false);
			}
		}
		if (tracer.isFineEnabled()) {
			tracer.fine("Finished gathering, Gathered info[" + info + "]");
		}
	}

	/**
	 * Contribution from Naoki Nishihara from OKI for Issue 1806 (SIP LB can not
	 * forward when node is listening on 0.0.0.0) Useful for a multi homed
	 * address, tries to reach a given load balancer from the list of ip
	 * addresses given in param
	 * 
	 * @param balancerAddr
	 *            the load balancer to try to reach
	 * @param info
	 *            the list of node info from which we try to access the load
	 *            balancer
	 * @return the list stripped from the nodes not able to reach the load
	 *         balancer
	 */
	protected ArrayList<SIPNode> getReachableSIPNodeInfo(MAPLoadBalancer balancer, ArrayList<SIPNode> info) {
		InetAddress balancerAddr = balancer.getAddress();
		if (balancerAddr.isLoopbackAddress()) {
			return info;
		}

		ArrayList<SIPNode> rv = new ArrayList<SIPNode>();
		for (SIPNode node : info) {
			if (tracer.isFineEnabled()) {
				tracer.fine("Checking if " + node + " is reachable");
			}
			try {
				NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByName(node.getIp()));
				// FIXME How can I determine the ttl?
				boolean b = balancerAddr.isReachable(ni, 5, 900);
				if (tracer.isFineEnabled()) {
					tracer.fine(node + " is reachable ? " + b);
				}
				if (b) {
					if (balancer.getCustomInfo() != null && !balancer.getCustomInfo().isEmpty()) {
						for (Entry<Object, Object> entry : balancer.getCustomInfo().entrySet()) {
							if (tracer.isFinestEnabled()) {
								tracer.finest("Adding custom info with key " + (String) entry.getKey() + " and value "
										+ (String) entry.getValue());
							}
							node.getProperties().put((String) entry.getKey(), (String) entry.getValue());
						}
					}
					rv.add(node);
				}
			} catch (IOException e) {
				tracer.severe("IOException", e);
			}
		}

		if (tracer.isFineEnabled()) {
			tracer.fine("Reachable SIP Node:[balancer=" + balancerAddr + "],[node info=" + rv + "]");
		}

		return rv;
	}

	/**
	 * @param info
	 */
	protected void removeNodesFromBalancers() {
		ArrayList<SIPNode> info = new ArrayList<SIPNode>(sipNodes.values());

		Thread.currentThread().setContextClassLoader(NodeRegisterRMIStub.class.getClassLoader());
		for (MAPLoadBalancer balancerDescription : new HashSet<MAPLoadBalancer>(register.values())) {
			try {
				Registry registry = LocateRegistry.getRegistry(balancerDescription.getAddress().getHostAddress(),
						balancerDescription.getRmiPort());
				NodeRegisterRMIStub reg = (NodeRegisterRMIStub) registry.lookup("SIPBalancer");
				reg.forceRemoval(info);
				if (!balancerDescription.isAvailable()) {
					tracer.info("Remove: SIP Load Balancer Found! " + balancerDescription);
					balancerDescription.setDisplayWarning(true);
				}
				balancerDescription.setAvailable(true);
			} catch (IOException e) {
				if (balancerDescription.isDisplayWarning()) {
					tracer.warning("remove: Cannot access the SIP load balancer RMI registry: " + e.getMessage()
							+ "\nIf you need a cluster configuration make sure the SIP load balancer is running.");
					balancerDescription.setDisplayWarning(false);
				}
				balancerDescription.setAvailable(true);
			} catch (Exception e) {
				if (balancerDescription.isDisplayWarning()) {
					tracer.severe(
							"remove: Cannot access the SIP load balancer RMI registry: " + e.getMessage()
									+ "\nIf you need a cluster configuration make sure the SIP load balancer is running.",
							e);
					balancerDescription.setDisplayWarning(false);
				}
				balancerDescription.setAvailable(true);
			}
		}
		if (tracer.isFineEnabled()) {
			tracer.fine("Finished gathering, Gathered info[" + info + "]");
		}
	}

	/**
	 * 
	 * @author <A HREF="mailto:jean.deruelle@gmail.com">Jean Deruelle</A>
	 *
	 */
	protected class BalancerPingTimerTask extends TimerTask {

		@Override
		public void run() {
			sendKeepAliveToBalancers();
		}
	}

	/**
	 * @param balancers
	 *            the balancers to set
	 */
	public void setBalancers(String balancers) {
		this.balancers = balancers;
	}

	@Override
	public String getJvmRoute() {
		return jvmRoute;
	}

	@Override
	public void setJvmRoute(String jvmRoute) {
		this.jvmRoute = jvmRoute;
	}

	@Override
	public void addLoadBalancerHeartBeatingListener(
			MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener) {
		loadBalancerHeartBeatingListeners.add(loadBalancerHeartBeatingListener);
	}

	@Override
	public void removeLoadBalancerHeartBeatingListener(
			MAPLoadBalancerHeartBeatingListener loadBalancerHeartBeatingListener) {
		loadBalancerHeartBeatingListeners.remove(loadBalancerHeartBeatingListener);
	}

	@Override
	public void sendSwitchoverInstruction(MAPLoadBalancer sipLoadBalancer, String fromJvmRoute, String toJvmRoute) {
		if (tracer.isInfoEnabled()) {
			tracer.info("switching over from " + fromJvmRoute + " to " + toJvmRoute);
		}
		if (fromJvmRoute == null || toJvmRoute == null) {
			return;
		}
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(NodeRegisterRMIStub.class.getClassLoader());
			Registry registry = LocateRegistry.getRegistry(sipLoadBalancer.getAddress().getHostAddress(),
					sipLoadBalancer.getRmiPort());
			NodeRegisterRMIStub reg = (NodeRegisterRMIStub) registry.lookup("SIPBalancer");
			reg.switchover(fromJvmRoute, toJvmRoute);
			sipLoadBalancer.setDisplayWarning(true);
			if (tracer.isInfoEnabled() && !sipLoadBalancer.isAvailable()) {
				tracer.info("Switchover: SIP Load Balancer Found! " + sipLoadBalancer);
			}
		} catch (IOException e) {
			sipLoadBalancer.setAvailable(false);
			if (sipLoadBalancer.isDisplayWarning()) {
				tracer.warning("Cannot access the SIP load balancer RMI registry: " + e.getMessage()
						+ "\nIf you need a cluster configuration make sure the SIP load balancer is running.");
				sipLoadBalancer.setDisplayWarning(false);
			}
		} catch (Exception e) {
			sipLoadBalancer.setAvailable(false);
			if (sipLoadBalancer.isDisplayWarning()) {
				tracer.severe(
						"Cannot access the SIP load balancer RMI registry: " + e.getMessage()
								+ "\nIf you need a cluster configuration make sure the SIP load balancer is running.",
						e);
				sipLoadBalancer.setDisplayWarning(false);
			}
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}

	@Override
	public void setGracefulShutdown(MAPLoadBalancer sipLoadBalancer, boolean gracefullyShuttingDown) {
		this.gracefullyShuttingDown = gracefullyShuttingDown;
		updateConnectorsAsSIPNode();
		// forcing keep alive sending to update the nodes in the LB with the
		// info
		// that the nodes are shutting down
		sendKeepAliveToBalancers();
	}

	@Override
	public void setCustomInfo(MAPLoadBalancer sipLoadBalancer) {
		updateConnectorsAsSIPNode();
	}

	@Override
	public MAPLoadBalancer[] getLoadBalancers() {
		// This is slow, but it is called rarely, so no prob
		return register.values().toArray(new MAPLoadBalancer[] {});
	}
}
