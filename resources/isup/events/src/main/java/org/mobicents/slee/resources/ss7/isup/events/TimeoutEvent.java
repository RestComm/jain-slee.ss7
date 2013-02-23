/**
 * 
 */
package org.mobicents.slee.resources.ss7.isup.events;

import java.io.Serializable;

import org.mobicents.protocols.ss7.isup.message.ISUPMessage;
import org.mobicents.protocols.ss7.isup.ISUPTimeout;
/**
 * @author baranowb
 *
 */
public class TimeoutEvent implements Serializable,ISUPTimeout {

	private ISUPMessage message;
	private int timerID;
	
	public TimeoutEvent(ISUPMessage message, int timerID) {
		super();
		this.message = message;
		this.timerID = timerID;
	}
	/**
	 * @return the transaction
	 */
	public ISUPMessage getMessage() {
		return message;
	}
	/**
	 * @return the timedOut
	 */
	public int getTimerID() {
		return timerID;
	}
}
