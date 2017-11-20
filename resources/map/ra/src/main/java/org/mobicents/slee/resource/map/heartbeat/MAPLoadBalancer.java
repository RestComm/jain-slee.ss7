package org.mobicents.slee.resource.map.heartbeat;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Properties;

import javax.sip.SipProvider;

public class MAPLoadBalancer implements Serializable {

	private static final long serialVersionUID = 1L;

	private InetAddress address;
	private int rmiPort;

	private transient MAPLoadBalancerHeartBeatingService loadBalancerHeartBeatingService;
	private transient boolean available;
	private transient boolean displayWarning;
	private transient Properties customInfo;

	public MAPLoadBalancer(MAPLoadBalancerHeartBeatingService loadBalancerHeartBeatingService, InetAddress address,
			int rmiPort) {
		this.available = false;
		this.displayWarning = true;
		this.address = address;
		this.rmiPort = rmiPort;
		this.loadBalancerHeartBeatingService = loadBalancerHeartBeatingService;
	}

	public static MAPLoadBalancer getInstanceWithoutRMI(
			MAPLoadBalancerHeartBeatingService loadBalancerHeartBeatingService, InetAddress address,
			SipProvider sipProvider) {
		return new MAPLoadBalancer(loadBalancerHeartBeatingService, address, -1);
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public InetAddress getAddress() {
		return this.address;
	}

	public int getRmiPort() {
		return this.rmiPort;
	}

	public void setRmiPort(int rmiPort) {
		this.rmiPort = rmiPort;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isDisplayWarning() {
		return this.displayWarning;
	}

	public void setDisplayWarning(boolean displayWarning) {
		this.displayWarning = displayWarning;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((this.address == null) ? 0 : this.address.hashCode());

		result = 31 * result + this.rmiPort;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		MAPLoadBalancer other = (MAPLoadBalancer) obj;
		if (this.address == null)
			if (other.address != null)
				return false;
			else if (!(this.address.equals(other.address))) {
				return false;
			}

		return (this.rmiPort == other.rmiPort);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("LoadBalancer:").append(getAddress().getHostAddress()).append(":" + getRmiPort());

		return stringBuilder.toString();
	}

	public void switchover(String fromJvmRoute, String toJvmRoute) {
		this.loadBalancerHeartBeatingService.sendSwitchoverInstruction(this, fromJvmRoute, toJvmRoute);
	}

	public void setGracefulShutdown(boolean shuttingDownGracefully) {
		this.loadBalancerHeartBeatingService.setGracefulShutdown(this, shuttingDownGracefully);
	}

	public Properties getCustomInfo() {
		return this.customInfo;
	}

	public void setCustomInfo(Properties customInfo) {
		this.customInfo = customInfo;
		this.loadBalancerHeartBeatingService.setCustomInfo(this);
	}
}