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

package org.mobicents.slee.resource.cap.wrappers;

import org.mobicents.protocols.ss7.cap.api.CAPApplicationContext;
import org.mobicents.protocols.ss7.cap.api.CAPDialog;
import org.mobicents.protocols.ss7.cap.api.CAPException;
import org.mobicents.protocols.ss7.cap.api.CAPServiceBase;
import org.mobicents.protocols.ss7.cap.api.dialog.CAPDialogState;
import org.mobicents.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.mobicents.protocols.ss7.cap.api.dialog.CAPUserAbortReason;
import org.mobicents.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.mobicents.protocols.ss7.sccp.parameter.SccpAddress;
import org.mobicents.protocols.ss7.tcap.api.MessageType;
import org.mobicents.protocols.ss7.tcap.asn.comp.Invoke;
import org.mobicents.protocols.ss7.tcap.asn.comp.Problem;
import org.mobicents.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.mobicents.slee.resource.cap.CAPDialogActivityHandle;
import org.mobicents.slee.resource.cap.CAPResourceAdaptor;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public abstract class CAPDialogWrapper<T extends CAPDialog> implements CAPDialog {

    protected CAPDialogActivityHandle activityHandle;
    protected transient CAPResourceAdaptor ra;
    protected transient T wrappedDialog;

    private boolean keepedTimeout;
    protected final Long dialogId;

    public void restoreTransientData(CAPResourceAdaptor ra) {
        this.ra=ra;
    }

    public CAPDialogWrapper(T wrappedDialog, CAPDialogActivityHandle activityHandle, CAPResourceAdaptor ra) {
        this.wrappedDialog = wrappedDialog;
        dialogId= this.wrappedDialog.getLocalDialogId();
        this.activityHandle = activityHandle;
        this.activityHandle.setActivity(this);
        this.ra = ra;
    }

    public T getWrappedDialog() {
        if(wrappedDialog ==null)
            wrappedDialog =ra.getWrappedDialog(dialogId);
        return wrappedDialog;
    }

    public SccpAddress getLocalAddress(){
        return this.getWrappedDialog().getLocalAddress();
    }

    public SccpAddress getRemoteAddress(){
        return this.getWrappedDialog().getRemoteAddress();
    }

    public void abort(CAPUserAbortReason arg0) throws CAPException {
        this.getWrappedDialog().abort(arg0);
    }

    public boolean cancelInvocation(Long arg0) throws CAPException {
        return this.getWrappedDialog().cancelInvocation(arg0);
    }

    public CAPApplicationContext getApplicationContext() {
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

    public int getMessageUserDataLengthOnClose(boolean arg0) throws CAPException {
        return this.getWrappedDialog().getMessageUserDataLengthOnClose(arg0);
    }

    public int getMessageUserDataLengthOnSend() throws CAPException {
        return this.getWrappedDialog().getMessageUserDataLengthOnSend();
    }

    public boolean getReturnMessageOnError() {
        return this.getWrappedDialog().getReturnMessageOnError();
    }

    public CAPServiceBase getService() {
        throw new UnsupportedOperationException();
    }

    public CAPDialogState getState() {
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
//		this.getWrappedDialog().keepAlive();
        this.keepedTimeout = true;
    }

    public void startDialogTimeoutProc() {
        this.keepedTimeout = false;
    }

    public boolean checkDialogTimeoutProcKeeped() {
        return this.keepedTimeout;
    }

    public void release() {
        this.getWrappedDialog().release();
    }

    public void resetInvokeTimer(Long arg0) throws CAPException {
        this.getWrappedDialog().resetInvokeTimer(arg0);
    }

    public void send() throws CAPException {
        this.getWrappedDialog().send();
    }

    @Override
    public void sendDelayed() throws CAPException {
        this.getWrappedDialog().sendDelayed();
    }

    public void close(boolean arg0) throws CAPException {
        this.getWrappedDialog().close(arg0);
    }

    public void closeDelayed(boolean arg0) throws CAPException {
        this.getWrappedDialog().closeDelayed(arg0);
    }

    public CAPGprsReferenceNumber getGprsReferenceNumber() {
        return this.getWrappedDialog().getGprsReferenceNumber();
    }

    public CAPGprsReferenceNumber getReceivedGprsReferenceNumber() {
        return this.getWrappedDialog().getReceivedGprsReferenceNumber();
    }

    public MessageType getTCAPMessageType() {
        return this.getWrappedDialog().getTCAPMessageType();
    }

    public void sendErrorComponent(Long arg0, CAPErrorMessage arg1) throws CAPException {
        this.getWrappedDialog().sendErrorComponent(arg0, arg1);
    }

    public void sendInvokeComponent(Invoke arg0) throws CAPException {
        this.getWrappedDialog().sendInvokeComponent(arg0);
    }

    public void sendRejectComponent(Long arg0, Problem arg1) throws CAPException {
        this.getWrappedDialog().sendRejectComponent(arg0, arg1);
    }

    public void sendReturnResultLastComponent(ReturnResultLast arg0) throws CAPException {
        this.getWrappedDialog().sendReturnResultLastComponent(arg0);
    }

    public void setGprsReferenceNumber(CAPGprsReferenceNumber arg0) {
        this.getWrappedDialog().setGprsReferenceNumber(arg0);
    }

    public void setReturnMessageOnError(boolean val) {
        this.getWrappedDialog().setReturnMessageOnError(val);
    }

    public void setUserObject(Object arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
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

    public CAPDialogActivityHandle getActivityHandle() {
        return activityHandle;
    }

    public void clear() {
        //TODO Any more cleaning here?
        if (this.activityHandle != null) {
            this.activityHandle.setActivity(null);
            this.activityHandle = null;
        }
    }

    public CAPResourceAdaptor getRa() {
        return ra;
    }


}
