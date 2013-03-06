package org.mobicents.slee.resources.ss7.isup.ratype;

import java.io.IOException;
import java.io.Serializable;
import javax.slee.SLEEException;
import javax.slee.resource.ActivityAlreadyExistsException;
import javax.slee.resource.StartActivityException;

import org.mobicents.protocols.ss7.isup.message.ISUPMessage;
import org.mobicents.protocols.ss7.isup.ParameterException;

public class CircuitActivity implements Serializable {
	
	private RAISUPProvider isupProvider;
	private int cic;
	private int dpc;
	
	/**
	 * Create client transaction activity
	 * @param arg0
	 * @return
	 * @throws TransactionAlredyExistsException
	 * @throws IllegalArgumentException
	 * @throws ActivityAlreadyExistsException
	 * @throws NullPointerException
	 * @throws IllegalStateException
	 * @throws SLEEException
	 * @throws StartActivityException
	 */
	public CircuitActivity(ISUPMessage message,int dpc,RAISUPProvider isupProvider) {
		this.cic=message.getCircuitIdentificationCode().getCIC();
		this.dpc=dpc;
		this.isupProvider=isupProvider;
	}
	
	public void sendMessage(ISUPMessage msg) throws ParameterException, IOException
	{
	    if(msg == null ){
	        throw new NullPointerException();
	    }

	    if(msg.getCircuitIdentificationCode() == null || msg.getCircuitIdentificationCode().getCIC() != this.cic){
	        throw new IllegalArgumentException("Wrong CIC value!");
	    }
		this.isupProvider.sendMessage(msg,dpc);
	}
	
	public void cancelTimer(int timerID)
	{
		this.isupProvider.cancelTimer(cic,dpc,timerID);
	}
	
	public int getDPC()
	{
		return this.dpc;
	}
	
	public int getCIC()
	{
		return this.cic;
	}
	
	public long getTransactionKey()
	{
		return CircuitActivity.generateTransactionKey(cic, dpc);
	}
	
	public static long generateTransactionKey(int cic,int dpc)
	{
		long currValue=dpc;
		currValue=(currValue<<14) + (long)cic;
		return currValue;
	}
}
