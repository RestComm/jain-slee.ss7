/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
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

package org.mobicents.slee.resource.tcap.wrappers;

import org.mobicents.protocols.ss7.sccp.parameter.SccpAddress;
import org.mobicents.protocols.ss7.tcap.api.ComponentPrimitiveFactory;
import org.mobicents.protocols.ss7.tcap.api.DialogPrimitiveFactory;
import org.mobicents.protocols.ss7.tcap.api.TCAPException;
import org.mobicents.protocols.ss7.tcap.api.TCAPProvider;
import org.mobicents.protocols.ss7.tcap.api.TCListener;
import org.mobicents.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.mobicents.slee.resource.tcap.TCAPDialogActivityHandle;
import org.mobicents.slee.resource.tcap.TCAPResourceAdaptor;

/**
 * @author amit bhayani
 * 
 */
public class TCAPProviderWrapper implements TCAPProvider {

	final TCAPResourceAdaptor ra;

	private TCAPProvider wrappedProvider;

	/**
	 * @param ra
	 */
	public TCAPProviderWrapper(TCAPResourceAdaptor ra) {
		super();
		this.ra = ra;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.api.TCAPProvider#addTCListener(org.mobicents
	 * .protocols.ss7.tcap.api.TCListener)
	 */
	@Override
	public void addTCListener(TCListener arg0) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.api.TCAPProvider#
	 * getComponentPrimitiveFactory()
	 */
	@Override
	public ComponentPrimitiveFactory getComponentPrimitiveFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getComponentPrimitiveFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.api.TCAPProvider#getDialogPrimitiveFactory
	 * ()
	 */
	@Override
	public DialogPrimitiveFactory getDialogPrimitiveFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getDialogPrimitiveFactory();
	}

	@Override
	public Dialog getNewDialog(SccpAddress localAddress, SccpAddress remoteAddress, Long trId) throws TCAPException {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		Dialog wrappedDialog = this.wrappedProvider.getNewDialog(localAddress, remoteAddress, trId);

		TCAPDialogActivityHandle activityHanlde = new TCAPDialogActivityHandle(wrappedDialog.getLocalDialogId());
		TCAPDialogWrapper dialogWrapper = new TCAPDialogWrapper(activityHanlde, this.ra, wrappedDialog);

		try {
			this.ra.startSuspendedActivity(dialogWrapper);
		} catch (Exception e) {
			throw new TCAPException(e);
		}
		
		wrappedDialog.setUserObject(dialogWrapper);
		return dialogWrapper;
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mobicents.protocols.ss7.tcap.api.TCAPProvider#getNewDialog(org.mobicents
     * .protocols.ss7.sccp.parameter.SccpAddress,
     * org.mobicents.protocols.ss7.sccp.parameter.SccpAddress)
     */
    @Override
    public Dialog getNewDialog(SccpAddress localAddress, SccpAddress remoteAddress) throws TCAPException {
        return getNewDialog(localAddress, remoteAddress, null);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.api.TCAPProvider#getNewUnstructuredDialog
	 * (org.mobicents.protocols.ss7.sccp.parameter.SccpAddress,
	 * org.mobicents.protocols.ss7.sccp.parameter.SccpAddress)
	 */
	@Override
	public Dialog getNewUnstructuredDialog(SccpAddress localAddress, SccpAddress remoteAddress) throws TCAPException {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		Dialog wrappedDialog = this.wrappedProvider.getNewUnstructuredDialog(localAddress, remoteAddress);
		
		TCAPDialogActivityHandle activityHanlde = new TCAPDialogActivityHandle(wrappedDialog.getLocalDialogId());
		TCAPDialogWrapper dialogWrapper = new TCAPDialogWrapper(activityHanlde, this.ra, wrappedDialog);

		try {
			this.ra.startSuspendedActivity(dialogWrapper);
		} catch (Exception e) {
			throw new TCAPException(e);
		}
		
		wrappedDialog.setUserObject(dialogWrapper);
		return dialogWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.api.TCAPProvider#removeTCListener(org
	 * .mobicents.protocols.ss7.tcap.api.TCListener)
	 */
	@Override
	public void removeTCListener(TCListener arg0) {
		throw new UnsupportedOperationException();
	}

	public void setWrappedProvider(TCAPProvider wrappedProvider) {
		this.wrappedProvider = wrappedProvider;
	}

	@Override
	public boolean getPreviewMode() {
		return this.wrappedProvider.getPreviewMode();
	}

}
