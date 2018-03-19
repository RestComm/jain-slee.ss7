package org.restcomm.slee.resources.ss7.isup.ratype;

import java.io.IOException;
import java.io.Serializable;
import javax.slee.SLEEException;
import javax.slee.resource.ActivityAlreadyExistsException;
import javax.slee.resource.StartActivityException;

import org.restcomm.protocols.ss7.isup.message.BlockingMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupBlockingMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupResetMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupUnblockingMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.ResetCircuitMessage;
import org.restcomm.protocols.ss7.isup.message.UnblockingMessage;

public class CircuitActivity implements Serializable {

	public static final int CALL_SESSION = 0;
	public static final int MAINTENANCE = 1;

	private RAISUPProvider isupProvider;
	private int cic;
	private int dpc;
	private int type = CALL_SESSION;
	
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
		this.type = getType(message);
	}

	public static int getType(ISUPMessage message) {
		switch (message.getMessageType().getCode()) {
			case BlockingMessage.MESSAGE_CODE:
			case UnblockingMessage.MESSAGE_CODE:
			case CircuitGroupBlockingMessage.MESSAGE_CODE:
			case CircuitGroupUnblockingMessage.MESSAGE_CODE:
			case CircuitGroupResetMessage.MESSAGE_CODE:
			case ResetCircuitMessage.MESSAGE_CODE:
				return CircuitActivity.MAINTENANCE;
			default:
				return CircuitActivity.CALL_SESSION;
		}
	}

	public int getType() {
		return type;
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
		return CircuitActivity.generateTransactionKey(cic, dpc, type);
	}
	
	public static long generateTransactionKey(int cic,int dpc, int code)
	{
		long currValue=dpc;
		currValue=(currValue<<14) + (long)cic;
		currValue= (currValue<<8) + code;
		return currValue;
	}

	@Override
	public String toString() {
		return "CircuitActivity(cic = " + cic + "; dpc = " + dpc + "; type = " +
				(type == CALL_SESSION ? "CALL":"MAINTENANCE") + ")";
	}
}
