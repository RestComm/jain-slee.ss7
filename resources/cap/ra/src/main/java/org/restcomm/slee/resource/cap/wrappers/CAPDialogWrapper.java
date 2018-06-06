/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.slee.resource.cap.wrappers;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPDialogState;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPUserAbortReason;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.MessageType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.CAPResourceAdaptor;

/**
 * 
 * @author sergey vetyutnev
 * @author amit bhayani
 * 
 */
public abstract class CAPDialogWrapper<T extends CAPDialog> implements CAPDialog {
	
	protected CAPDialogActivityHandle activityHandle;
	protected final CAPResourceAdaptor ra;
	protected T wrappedDialog;
	
	private boolean keepedTimeout;

	public CAPDialogWrapper(T wrappedDialog, CAPDialogActivityHandle activityHandle, CAPResourceAdaptor ra) {
		this.wrappedDialog = wrappedDialog;
		this.activityHandle = activityHandle;
		this.activityHandle.setActivity(this);
		this.ra = ra;
	}

    public SccpAddress getLocalAddress(){
    	return this.wrappedDialog.getLocalAddress();
    }
    
    public SccpAddress getRemoteAddress(){
    	return this.wrappedDialog.getRemoteAddress();
    }

	public void abort(CAPUserAbortReason arg0) throws CAPException {
		this.wrappedDialog.abort(arg0);
	}

	public boolean cancelInvocation(Long arg0) throws CAPException {
		return this.wrappedDialog.cancelInvocation(arg0);
	}

	public CAPApplicationContext getApplicationContext() {
		return this.wrappedDialog.getApplicationContext();
	}

	public Long getLocalDialogId() {
		return this.wrappedDialog.getLocalDialogId();
	}
	
	public Long getRemoteDialogId() {
		return this.wrappedDialog.getRemoteDialogId();
	}

	public int getMaxUserDataLength() {
		return this.wrappedDialog.getMaxUserDataLength();
	}

	public int getMessageUserDataLengthOnClose(boolean arg0) throws CAPException {
		return this.wrappedDialog.getMessageUserDataLengthOnClose(arg0);
	}

	public int getMessageUserDataLengthOnSend() throws CAPException {
		return this.wrappedDialog.getMessageUserDataLengthOnSend();
	}

	public boolean getReturnMessageOnError() {
		return this.wrappedDialog.getReturnMessageOnError();
	}

	public CAPServiceBase getService() {
		throw new UnsupportedOperationException();
	}

	public CAPDialogState getState() {
		return this.wrappedDialog.getState();
	}

	public Object getUserObject() {
		throw new UnsupportedOperationException();
	}

    @Override
    public int getNetworkId() {
        return this.wrappedDialog.getNetworkId();
    }

    @Override
    public void setNetworkId(int networkId) {
        this.wrappedDialog.setNetworkId(networkId);
    }

	public void keepAlive() {
//		this.wrappedDialog.keepAlive();
		this.keepedTimeout = true;
	}

	public void startDialogTimeoutProc() {
		this.keepedTimeout = false;
	}

	public boolean checkDialogTimeoutProcKeeped() {
		return this.keepedTimeout;
	}
	
	public void release() {
		this.wrappedDialog.release();
	}

	public void resetInvokeTimer(Long arg0) throws CAPException {
		this.wrappedDialog.resetInvokeTimer(arg0);
	}

	public void send() throws CAPException {
		this.wrappedDialog.send();
	}

	@Override
	public void sendDelayed() throws CAPException {
		this.wrappedDialog.sendDelayed();
	}

	public void close(boolean arg0) throws CAPException {
		this.wrappedDialog.close(arg0);
	}

	public void closeDelayed(boolean arg0) throws CAPException {
		this.wrappedDialog.closeDelayed(arg0);
	}

	public CAPGprsReferenceNumber getGprsReferenceNumber() {
		return this.wrappedDialog.getGprsReferenceNumber();
	}

	public CAPGprsReferenceNumber getReceivedGprsReferenceNumber() {
		return this.wrappedDialog.getReceivedGprsReferenceNumber();
	}

	public MessageType getTCAPMessageType() {
		return this.wrappedDialog.getTCAPMessageType();
	}

	public void sendErrorComponent(Long arg0, CAPErrorMessage arg1) throws CAPException {
		this.wrappedDialog.sendErrorComponent(arg0, arg1);
	}

	public void sendInvokeComponent(Invoke arg0) throws CAPException {
		this.wrappedDialog.sendInvokeComponent(arg0);
	}

	public void sendRejectComponent(Long arg0, Problem arg1) throws CAPException {
		this.wrappedDialog.sendRejectComponent(arg0, arg1);
	}

	public void sendReturnResultLastComponent(ReturnResultLast arg0) throws CAPException {
		this.wrappedDialog.sendReturnResultLastComponent(arg0);
	}

	public void setGprsReferenceNumber(CAPGprsReferenceNumber arg0) {
		this.wrappedDialog.setGprsReferenceNumber(arg0);
	}

	public void setReturnMessageOnError(boolean val) {
		this.wrappedDialog.setReturnMessageOnError(val);
	}

	public void setUserObject(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processInvokeWithoutAnswer(Long invokeId) {
		this.wrappedDialog.processInvokeWithoutAnswer(invokeId);
	}

	@Override
	public void setLocalAddress(SccpAddress address) {
		this.wrappedDialog.setLocalAddress(address);
	}

	@Override
	public void setRemoteAddress(SccpAddress address) {
		this.wrappedDialog.setRemoteAddress(address);
	}

    @Override
    public long getIdleTaskTimeout() {
        return this.wrappedDialog.getIdleTaskTimeout();
    }

    @Override
    public void setIdleTaskTimeout(long idleTaskTimeoutMs) {
        this.wrappedDialog.setIdleTaskTimeout(idleTaskTimeoutMs);
    }

    @Override
    public int getTimerCircuitSwitchedCallControlLong() {
        return this.wrappedDialog.getTimerCircuitSwitchedCallControlLong();
    }

    @Override
    public int getTimerCircuitSwitchedCallControlMedium() {
        return this.wrappedDialog.getTimerCircuitSwitchedCallControlMedium();
    }

    @Override
    public int getTimerCircuitSwitchedCallControlShort() {
        return this.wrappedDialog.getTimerCircuitSwitchedCallControlShort();
    }

    @Override
    public int getTimerGprsShort() {
        return this.wrappedDialog.getTimerGprsShort();
    }

    @Override
    public int getTimerSmsShort() {
        return this.wrappedDialog.getTimerSmsShort();
    }

	public CAPDialogActivityHandle getActivityHandle() {
		return activityHandle;
	}
	
	public void clear() {
		//TODO Any more cleaning here?
		if (this.activityHandle != null) {
			this.activityHandle.setActivity(null);
			this.activityHandle = null;
		}
		
		if(this.wrappedDialog != null){
			this.wrappedDialog.setUserObject(null);
			this.wrappedDialog = null;
		}
	}

	public CAPResourceAdaptor getRa() {
		return ra;
	}

	
	public abstract T getWrappedDialog();
	
}
