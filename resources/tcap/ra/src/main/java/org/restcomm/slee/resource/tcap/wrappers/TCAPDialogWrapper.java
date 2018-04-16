/*
 * TeleStax, Open Source Cloud Communications  
 * Copyright 2012, Telestax Inc and individual contributors
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

package org.restcomm.slee.resource.tcap.wrappers;

import java.util.concurrent.locks.ReentrantLock;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.TCAPSendException;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.TRPseudoState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.slee.resource.tcap.TCAPDialogActivityHandle;
import org.restcomm.slee.resource.tcap.TCAPResourceAdaptor;
import org.restcomm.slee.resource.tcap.events.TCAPEvent;

/**
 * 
 * @author amit bhayani
 * 
 */
public class TCAPDialogWrapper implements Dialog, TCAPEvent {

	private TCAPDialogActivityHandle activityHandle;
	private final TCAPResourceAdaptor ra;
	private Dialog wrappedDialog;
	
	private boolean keepedTimeout;

	/**
	 * @param activityHandle
	 * @param ra
	 * @param wrappedDialog
	 */
	public TCAPDialogWrapper(TCAPDialogActivityHandle activityHandle, TCAPResourceAdaptor ra, Dialog wrappedDialog) {
		super();
		this.activityHandle = activityHandle;
		this.ra = ra;
		this.wrappedDialog = wrappedDialog;
		this.activityHandle.setActivity(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#cancelInvocation
	 * (java.lang.Long)
	 */
	@Override
	public boolean cancelInvocation(Long invokeId) throws TCAPException {
		return this.wrappedDialog.cancelInvocation(invokeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#
	 * getApplicationContextName()
	 */
	@Override
	public ApplicationContextName getApplicationContextName() {
		return this.wrappedDialog.getApplicationContextName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDataLength(org
	 * .restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest)
	 */
	@Override
	public int getDataLength(TCBeginRequest event) throws TCAPSendException {
		return this.wrappedDialog.getDataLength(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDataLength(org
	 * .restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest)
	 */
	@Override
	public int getDataLength(TCContinueRequest event) throws TCAPSendException {
		return this.wrappedDialog.getDataLength(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDataLength(org
	 * .restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest)
	 */
	@Override
	public int getDataLength(TCEndRequest event) throws TCAPSendException {
		return this.wrappedDialog.getDataLength(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDataLength(org
	 * .restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest)
	 */
	@Override
	public int getDataLength(TCUniRequest event) throws TCAPSendException {
		return this.wrappedDialog.getDataLength(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getDialogId()
	 */
	@Override
	public Long getLocalDialogId() {
		return this.wrappedDialog.getLocalDialogId();
	}

	@Override
	public Long getRemoteDialogId() {
		return this.wrappedDialog.getRemoteDialogId();
	}

	@Override
	public boolean getPreviewMode() {
		return this.wrappedDialog.getPreviewMode();
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
    public int getLocalSsn() {
        return this.wrappedDialog.getLocalSsn();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getLocalAddress()
	 */
	@Override
	public SccpAddress getLocalAddress() {
		return this.wrappedDialog.getLocalAddress();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getMaxUserDataLength
	 * ()
	 */
	@Override
	public int getMaxUserDataLength() {
		return this.wrappedDialog.getMaxUserDataLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getNewInvokeId()
	 */
	@Override
	public Long getNewInvokeId() throws TCAPException {
		return this.wrappedDialog.getNewInvokeId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getRemoteAddress()
	 */
	@Override
	public SccpAddress getRemoteAddress() {
		return this.wrappedDialog.getRemoteAddress();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getState()
	 */
	@Override
	public TRPseudoState getState() {
		return this.wrappedDialog.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getUserInformation
	 * ()
	 */
	@Override
	public UserInformation getUserInformation() {
		return this.wrappedDialog.getUserInformation();
	}

	@Override
	public ReentrantLock getDialogLock() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#getUserObject()
	 */
	@Override
	public Object getUserObject() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#isEstabilished()
	 */
	@Override
	public boolean isEstabilished() {
		return this.wrappedDialog.isEstabilished();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#isStructured()
	 */
	@Override
	public boolean isStructured() {
		return this.wrappedDialog.isStructured();
	}

    @Override
    public int getNetworkId() {
        return this.wrappedDialog.getNetworkId();
    }

    @Override
    public void setNetworkId(int networkId) {
        this.wrappedDialog.setNetworkId(networkId);
    }

    @Override
    public long getIdleTaskTimeout() {
        return this.wrappedDialog.getIdleTaskTimeout();
    }

    @Override
    public void setIdleTaskTimeout(long idleTaskTimeoutMs) {
        this.wrappedDialog.setIdleTaskTimeout(idleTaskTimeoutMs);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#keepAlive()
	 */
	@Override
	public void keepAlive() {
		this.keepedTimeout = true;
	}

	public void startDialogTimeoutProc() {
		this.keepedTimeout = false;
	}

	public boolean checkDialogTimeoutProcKeeped() {
		return this.keepedTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#release()
	 */
	@Override
	public void release() {
		this.wrappedDialog.release();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#resetTimer(java
	 * .lang.Long)
	 */
	@Override
	public void resetTimer(Long invokeId) throws TCAPException {
		this.wrappedDialog.resetTimer(invokeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest)
	 */
	@Override
	public void send(TCBeginRequest event) throws TCAPSendException {
		this.wrappedDialog.send(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest)
	 */
	@Override
	public void send(TCContinueRequest event) throws TCAPSendException {
		this.wrappedDialog.send(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest)
	 */
	@Override
	public void send(TCEndRequest event) throws TCAPSendException {
		this.wrappedDialog.send(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest)
	 */
	@Override
	public void send(TCUserAbortRequest event) throws TCAPSendException {
		this.wrappedDialog.send(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#send(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest)
	 */
	@Override
	public void send(TCUniRequest event) throws TCAPSendException {
		this.wrappedDialog.send(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#sendComponent(org
	 * .restcomm.protocols.ss7.tcap.asn.comp.Component)
	 */
	@Override
	public void sendComponent(Component componentRequest) throws TCAPSendException {
		this.wrappedDialog.sendComponent(componentRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog#setUserObject(java
	 * .lang.Object)
	 */
	@Override
	public void setUserObject(Object arg0) {
		throw new UnsupportedOperationException();
	}
	
	public TCAPDialogActivityHandle getActivityHandle() {
		return this.activityHandle;
	}

	public void clear() {
		// TODO Any more cleaning here?
		if (this.activityHandle != null) {
			this.activityHandle.setActivity(null);
			this.activityHandle = null;
		}

		if (this.wrappedDialog != null) {
			this.wrappedDialog.setUserObject(null);
			this.wrappedDialog = null;
		}
	}
	
	public Dialog getWrappedDialog(){
		return this.wrappedDialog;
	}

	public TCAPResourceAdaptor getRa() {
		return ra;
	}

    @Override
    public String toString() {
        return this.wrappedDialog.toString();
    }

    @Override
    public Boolean isDoNotSendProtcolVersion() {
        return this.wrappedDialog.isDoNotSendProtcolVersion();
    }

    @Override
    public void setDoNotSendProtocolVersion(Boolean isSendProtocolVersion) {
        this.wrappedDialog.setDoNotSendProtocolVersion(isSendProtocolVersion);
    }

    @Override
    public long getStartTimeDialog() {
        return this.wrappedDialog.getStartTimeDialog();
    }

}
