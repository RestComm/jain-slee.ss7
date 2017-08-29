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

package org.mobicents.slee.resource.map.wrappers;

import org.mobicents.protocols.ss7.map.api.MAPApplicationContext;
import org.mobicents.protocols.ss7.map.api.MAPDialog;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPServiceBase;
import org.mobicents.protocols.ss7.map.api.dialog.MAPDialogState;
import org.mobicents.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.mobicents.protocols.ss7.map.api.dialog.Reason;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.mobicents.protocols.ss7.map.api.primitives.AddressString;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.sccp.parameter.SccpAddress;
import org.mobicents.protocols.ss7.tcap.api.MessageType;
import org.mobicents.protocols.ss7.tcap.asn.comp.Invoke;
import org.mobicents.protocols.ss7.tcap.asn.comp.Problem;
import org.mobicents.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.mobicents.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.mobicents.slee.resource.map.MAPDialogActivityHandle;
import org.mobicents.slee.resource.map.MAPResourceAdaptor;

/**
 *
 * @author amit bhayani
 *
 */
public abstract class MAPDialogWrapper<T extends MAPDialog> implements MAPDialog {

	protected transient MAPDialogActivityHandle activityHandle;
	protected transient MAPResourceAdaptor ra;
	private transient T wrappedDialog;

	private boolean keepedTimeout;
	protected final Long dialogId;

	public MAPDialogWrapper(T wrappedDialog, MAPDialogActivityHandle activityHandle, MAPResourceAdaptor ra) {
		this.wrappedDialog=wrappedDialog;
		this.activityHandle = activityHandle;
		this.activityHandle.setActivity(this);
		this.ra = ra;
		this.dialogId=activityHandle.getDialogId();
	}

    public SccpAddress getLocalAddress(){
    	return this.getWrappedDialog().getLocalAddress();
    }

    public SccpAddress getRemoteAddress(){
    	return this.getWrappedDialog().getRemoteAddress();
    }

	public void abort(MAPUserAbortChoice arg0) throws MAPException {
		this.getWrappedDialog().abort(arg0);
	}

	public void addEricssonData(AddressString arg0, AddressString arg1) {
		this.getWrappedDialog().addEricssonData(arg0, arg1);
	}

	public boolean cancelInvocation(Long arg0) throws MAPException {
		return this.getWrappedDialog().cancelInvocation(arg0);
	}

	public void close(boolean arg0) throws MAPException {
		this.getWrappedDialog().close(arg0);
	}

	public void closeDelayed(boolean prearrangedEnd) throws MAPException {
		this.getWrappedDialog().closeDelayed(prearrangedEnd);
	}

	@Override
	public MessageType getTCAPMessageType() {
		return this.getWrappedDialog().getTCAPMessageType();
	}

	@Override
	public AddressString getReceivedOrigReference() {
		return this.getWrappedDialog().getReceivedOrigReference();
	}

	@Override
	public AddressString getReceivedDestReference() {
		return this.getWrappedDialog().getReceivedDestReference();
	}

	@Override
	public MAPExtensionContainer getReceivedExtensionContainer() {
		return this.getWrappedDialog().getReceivedExtensionContainer();
	}

	public MAPApplicationContext getApplicationContext() {
		return this.getWrappedDialog().getApplicationContext();
	}

	public Long getLocalDialogId() {
		return this.getWrappedDialog().getLocalDialogId();
	}

	public Long getRemoteDialogId() {
		return this.getWrappedDialog().getRemoteDialogId();
	}

	public int getMaxUserDataLength() {
		return this.getWrappedDialog().getMaxUserDataLength();
	}

	public int getMessageUserDataLengthOnClose(boolean arg0) throws MAPException {
		return this.getWrappedDialog().getMessageUserDataLengthOnClose(arg0);
	}

	public int getMessageUserDataLengthOnSend() throws MAPException {
		return this.getWrappedDialog().getMessageUserDataLengthOnSend();
	}

	public MAPServiceBase getService() {
		throw new UnsupportedOperationException();
	}

	public MAPDialogState getState() {
		return this.getWrappedDialog().getState();
	}

	public Object getUserObject() {
		throw new UnsupportedOperationException();
	}

    @Override
    public int getNetworkId() {
        return this.getWrappedDialog().getNetworkId();
    }

    @Override
    public void setNetworkId(int networkId) {
        this.getWrappedDialog().setNetworkId(networkId);
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
		this.getWrappedDialog().refuse(arg0);
	}

	public void release() {
		this.getWrappedDialog().release();
	}

	public void resetInvokeTimer(Long arg0) throws MAPException {
		this.getWrappedDialog().resetInvokeTimer(arg0);
	}

	public void send() throws MAPException {
    	ra.storeMapDialogWrapper(this);
		this.getWrappedDialog().send();
	}

	public void sendDelayed() throws MAPException {
		this.getWrappedDialog().sendDelayed();
	}

	public void sendErrorComponent(Long arg0, MAPErrorMessage arg1) throws MAPException {
		this.getWrappedDialog().sendErrorComponent(arg0, arg1);
	}

	public void sendInvokeComponent(Invoke arg0) throws MAPException {
		this.getWrappedDialog().sendInvokeComponent(arg0);
	}

	public void sendRejectComponent(Long arg0, Problem arg1) throws MAPException {
		this.getWrappedDialog().sendRejectComponent(arg0, arg1);
	}

	public void sendReturnResultComponent(ReturnResult arg0) throws MAPException {
		this.getWrappedDialog().sendReturnResultComponent(arg0);
	}

	public void sendReturnResultLastComponent(ReturnResultLast arg0) throws MAPException {
		this.getWrappedDialog().sendReturnResultLastComponent(arg0);
	}

	public void setExtentionContainer(MAPExtensionContainer arg0) {
		this.getWrappedDialog().setExtentionContainer(arg0);
	}

	public void setUserObject(Object arg0) {
		throw new UnsupportedOperationException();
	}

	public void setReturnMessageOnError(boolean val){
		this.getWrappedDialog().setReturnMessageOnError(val);
	}

	public boolean getReturnMessageOnError(){
		return this.getWrappedDialog().getReturnMessageOnError();
	}

	public void processInvokeWithoutAnswer(Long invokeId) {
		this.getWrappedDialog().processInvokeWithoutAnswer(invokeId);
	}

	@Override
	public void setLocalAddress(SccpAddress address) {
		this.getWrappedDialog().setLocalAddress(address);
	}

	@Override
	public void setRemoteAddress(SccpAddress address) {
		this.getWrappedDialog().setRemoteAddress(address);
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

		if(this.getWrappedDialog() != null){
			this.getWrappedDialog().setUserObject(null);
			wrappedDialog=null;
		}
	}

	public MAPResourceAdaptor getRa() {
		return ra;
	}


	public final T getWrappedDialog() {
		if(wrappedDialog ==null)
			wrappedDialog =ra.getWrappedDialog(dialogId);
		return wrappedDialog;
	}

    public void restoreTransientData(MAPResourceAdaptor mapResourceAdaptor) {
		this.ra=mapResourceAdaptor;
		this.activityHandle=new MAPDialogActivityHandle(ra,dialogId);
    }
}
