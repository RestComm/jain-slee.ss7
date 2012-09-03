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

import org.mobicents.protocols.ss7.cap.api.CAPDialog;
import org.mobicents.protocols.ss7.cap.api.CAPDialogListener;
import org.mobicents.protocols.ss7.cap.api.CAPParameterFactory;
import org.mobicents.protocols.ss7.cap.api.CAPProvider;
import org.mobicents.protocols.ss7.cap.api.errors.CAPErrorMessageFactory;
import org.mobicents.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCall;
import org.mobicents.protocols.ss7.cap.api.service.gprs.CAPServiceGprs;
import org.mobicents.protocols.ss7.cap.api.service.sms.CAPServiceSms;
import org.mobicents.protocols.ss7.inap.api.INAPParameterFactory;
import org.mobicents.protocols.ss7.isup.ISUPParameterFactory;
import org.mobicents.protocols.ss7.map.api.MAPParameterFactory;
import org.mobicents.slee.resource.cap.CAPDialogActivityHandle;
import org.mobicents.slee.resource.cap.CAPResourceAdaptor;
import org.mobicents.slee.resource.cap.service.circuitSwitchedCall.wrappers.CAPServiceCircuitSwitchedCallWrapper;
import org.mobicents.slee.resource.cap.service.gprs.wrappers.CAPServiceGprsWrapper;
import org.mobicents.slee.resource.cap.service.sms.wrappers.CAPServiceSmsWrapper;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPProviderWrapper implements CAPProvider {

	// //////////////////////////////
	// Wrappers for CAP specifics //
	// //////////////////////////////
	private CAPProvider wrappedProvider;
	
	private CAPServiceCircuitSwitchedCallWrapper wrappedCAPServiceCircuitSwitchedCall;
	private CAPServiceGprsWrapper wrappedCAPServiceGprs;
	private CAPServiceSmsWrapper wrappedCAPServiceSms;

	private final CAPResourceAdaptor ra;

	public CAPProviderWrapper(CAPResourceAdaptor ra) {
		super();

		this.ra = ra;

		// now create service wrappers

	}

	public void addCAPDialogListener(CAPDialogListener arg0) {
		throw new UnsupportedOperationException();
	}

	public void removeCAPDialogListener(CAPDialogListener arg0) {
		throw new UnsupportedOperationException();
	}

	public CAPParameterFactory getCAPParameterFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getCAPParameterFactory();
	}

	public INAPParameterFactory getINAPParameterFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getINAPParameterFactory();
	}

	public ISUPParameterFactory getISUPParameterFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getISUPParameterFactory();
	}

	public MAPParameterFactory getMAPParameterFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getMAPParameterFactory();
	}

	public CAPErrorMessageFactory getCAPErrorMessageFactory() {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		return this.wrappedProvider.getCAPErrorMessageFactory();
	}

	public CAPDialog getCAPDialog(Long dialogId) {
		if (this.wrappedProvider == null) {
			throw new IllegalStateException("RA is has not been activated.");
		}
		CAPDialogActivityHandle ah = new CAPDialogActivityHandle(dialogId);
		return (CAPDialog) this.ra.getActivity(ah);
	}

	public CAPServiceCircuitSwitchedCall getCAPServiceCircuitSwitchedCall() {
		return wrappedCAPServiceCircuitSwitchedCall;
	}

	public CAPServiceGprs getCAPServiceGprs() {
		return wrappedCAPServiceGprs;
	}

	public CAPServiceSms getCAPServiceSms() {
		return wrappedCAPServiceSms;
	}

	public void setWrappedProvider(CAPProvider wrappedProvider) {
		this.wrappedProvider = wrappedProvider;

		this.wrappedCAPServiceCircuitSwitchedCall = new CAPServiceCircuitSwitchedCallWrapper(this, wrappedProvider.getCAPServiceCircuitSwitchedCall());
		this.wrappedCAPServiceGprs = new CAPServiceGprsWrapper(this, wrappedProvider.getCAPServiceGprs());
		this.wrappedCAPServiceSms = new CAPServiceSmsWrapper(this, wrappedProvider.getCAPServiceSms());
	}

	public CAPResourceAdaptor getRa() {
		return ra;
	}
}

