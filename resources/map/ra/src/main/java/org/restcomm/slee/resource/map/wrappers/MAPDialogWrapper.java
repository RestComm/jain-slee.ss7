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

package org.restcomm.slee.resource.map.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.dialog.MAPDialogState;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.dialog.Reason;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.MessageType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.MAPResourceAdaptor;

/**
 * 
 * @author amit bhayani
 *
 */
public abstract class MAPDialogWrapper<T extends MAPDialog> implements MAPDialog {

	protected MAPDialogActivityHandle activityHandle;
	protected final MAPResourceAdaptor ra;
	protected T wrappedDialog;

	private boolean keepedTimeout;

	public MAPDialogWrapper(T wrappedDialog, MAPDialogActivityHandle activityHandle, MAPResourceAdaptor ra) {
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

	public void abort(MAPUserAbortChoice arg0) throws MAPException {
		this.wrappedDialog.abort(arg0);
	}

	public void addEricssonData(AddressString arg0, AddressString arg1) {
		this.wrappedDialog.addEricssonData(arg0, arg1);
	}

	public boolean cancelInvocation(Long arg0) throws MAPException {
		return this.wrappedDialog.cancelInvocation(arg0);
	}

	public void close(boolean arg0) throws MAPException {
		this.wrappedDialog.close(arg0);
	}

	public void closeDelayed(boolean prearrangedEnd) throws MAPException {
		this.wrappedDialog.closeDelayed(prearrangedEnd);
	}

	@Override
	public MessageType getTCAPMessageType() {
		return this.wrappedDialog.getTCAPMessageType();
	}

	@Override
	public AddressString getReceivedOrigReference() {
		return this.wrappedDialog.getReceivedOrigReference();
	}

	@Override
	public AddressString getReceivedDestReference() {
		return this.wrappedDialog.getReceivedDestReference();
	}

	@Override
	public MAPExtensionContainer getReceivedExtensionContainer() {
		return this.wrappedDialog.getReceivedExtensionContainer();
	}

	public MAPApplicationContext getApplicationContext() {
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

	public int getMessageUserDataLengthOnClose(boolean arg0) throws MAPException {
		return this.wrappedDialog.getMessageUserDataLengthOnClose(arg0);
	}

	public int getMessageUserDataLengthOnSend() throws MAPException {
		return this.wrappedDialog.getMessageUserDataLengthOnSend();
	}

	public MAPServiceBase getService() {
		throw new UnsupportedOperationException();
	}

	public MAPDialogState getState() {
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

	public void refuse(Reason arg0) throws MAPException {
		this.wrappedDialog.refuse(arg0);
	}

	public void release() {
		this.wrappedDialog.release();
	}

	public void resetInvokeTimer(Long arg0) throws MAPException {
		this.wrappedDialog.resetInvokeTimer(arg0);
	}

	public void send() throws MAPException {
		this.wrappedDialog.send();
	}

	public void sendDelayed() throws MAPException {
		this.wrappedDialog.sendDelayed();
	}

	public void sendErrorComponent(Long arg0, MAPErrorMessage arg1) throws MAPException {
		this.wrappedDialog.sendErrorComponent(arg0, arg1);
	}

	public void sendInvokeComponent(Invoke arg0) throws MAPException {
		this.wrappedDialog.sendInvokeComponent(arg0);
	}

	public void sendRejectComponent(Long arg0, Problem arg1) throws MAPException {
		this.wrappedDialog.sendRejectComponent(arg0, arg1);
	}

	public void sendReturnResultComponent(ReturnResult arg0) throws MAPException {
		this.wrappedDialog.sendReturnResultComponent(arg0);
	}

	public void sendReturnResultLastComponent(ReturnResultLast arg0) throws MAPException {
		this.wrappedDialog.sendReturnResultLastComponent(arg0);
	}

	public void setExtentionContainer(MAPExtensionContainer arg0) {
		this.wrappedDialog.setExtentionContainer(arg0);
	}

	public void setUserObject(Object arg0) {
		throw new UnsupportedOperationException();
	}
	
	public void setReturnMessageOnError(boolean val){
		this.wrappedDialog.setReturnMessageOnError(val);
	}

	public boolean getReturnMessageOnError(){
		return this.wrappedDialog.getReturnMessageOnError();
	}

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
    public long getStartTimeDialog() {
        return this.wrappedDialog.getStartTimeDialog();
    }

    @Override
    public int getLongTimer() {
        return wrappedDialog.getLongTimer();
    }

    @Override
    public int getMediumTimer() {
        return wrappedDialog.getMediumTimer();
    }

    @Override
    public int getShortTimer() {
        return wrappedDialog.getShortTimer();
    }

	public MAPDialogActivityHandle getActivityHandle() {
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

	public MAPResourceAdaptor getRa() {
		return ra;
	}

	
	public abstract T getWrappedDialog();
	
}
