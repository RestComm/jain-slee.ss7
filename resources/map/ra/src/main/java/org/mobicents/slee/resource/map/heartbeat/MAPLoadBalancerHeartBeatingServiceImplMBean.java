package org.mobicents.slee.resource.map.heartbeat;

import java.io.IOException;

public interface MAPLoadBalancerHeartBeatingServiceImplMBean {

	public abstract void start();

	public abstract void stop();

	public abstract String[] getBalancers();

	public abstract boolean addBalancer(String balancerAddress, int index, int rmiPort)
			throws IOException;

	public abstract boolean removeBalancer(String balancerAddress, int index, int rmiPort);

	public abstract long getHeartBeatInterval();

	public abstract void setHeartBeatInterval(long paramLong);

	public abstract void setJvmRoute(String paramString);

	public abstract String getJvmRoute();
	
}
