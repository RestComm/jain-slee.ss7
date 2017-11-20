package org.mobicents.slee.resource.map.heartbeat;

public abstract interface MAPLoadBalancerHeartBeatingListener {

	public abstract void loadBalancerAdded(MAPLoadBalancer paramLoadBalancer);

	public abstract void loadBalancerRemoved(MAPLoadBalancer paramLoadBalancer);

	public abstract void pingingloadBalancer(MAPLoadBalancer paramLoadBalancer);

	public abstract void pingedloadBalancer(MAPLoadBalancer paramLoadBalancer);
	
}
