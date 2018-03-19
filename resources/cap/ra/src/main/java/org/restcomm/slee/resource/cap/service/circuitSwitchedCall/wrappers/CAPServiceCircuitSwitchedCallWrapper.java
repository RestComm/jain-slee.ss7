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

package org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPProvider;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPDialogCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPServiceCircuitSwitchedCallListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.wrappers.CAPProviderWrapper;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPServiceCircuitSwitchedCallWrapper implements CAPServiceCircuitSwitchedCall {

	protected CAPServiceCircuitSwitchedCall wrappedCircuitSwitchedCall;
	protected CAPProviderWrapper capProviderWrapper;

	/**
	 * @param CAPServiceCircuitSwitchedCall
	 */
	public CAPServiceCircuitSwitchedCallWrapper(CAPProviderWrapper capProviderWrapper, CAPServiceCircuitSwitchedCall capServiceCircuitSwitchedCall) {
		this.wrappedCircuitSwitchedCall = capServiceCircuitSwitchedCall;
		this.capProviderWrapper = capProviderWrapper;
	}

	public void acivate() {
		throw new UnsupportedOperationException();
	}

	public void deactivate() {
		throw new UnsupportedOperationException();
	}

	public CAPProvider getCAPProvider() {
		return this.capProviderWrapper;
	}

	public ServingCheckData isServingService(CAPApplicationContext dialogApplicationContext) {
		return this.wrappedCircuitSwitchedCall.isServingService(dialogApplicationContext);
	}

	public boolean isActivated() {
		return this.wrappedCircuitSwitchedCall.isActivated();
	}

    @Override
    public CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext appCntx, SccpAddress origAddress, SccpAddress destAddress, Long localTrId)
            throws CAPException {

    	this.capProviderWrapper.getRa().getDefaultUsageParameters().incrementCalls(1L);
        CAPDialogCircuitSwitchedCall capDialog = this.wrappedCircuitSwitchedCall.createNewDialog(appCntx, origAddress, destAddress, localTrId);
        CAPDialogActivityHandle activityHandle = new CAPDialogActivityHandle(capDialog.getLocalDialogId());

        CAPDialogCircuitSwitchedCallWrapper dw = new CAPDialogCircuitSwitchedCallWrapper(capDialog, activityHandle, this.capProviderWrapper.getRa());
        capDialog.setUserObject(dw);

        try {
            this.capProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new CAPException(e);
        }

        return dw;
    }

	public CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext appCntx, SccpAddress origAddress, SccpAddress destAddress) throws CAPException {
        return this.createNewDialog(appCntx, origAddress, destAddress, null);
	}

	public void addCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceListener) {
		throw new UnsupportedOperationException();
	}

	public void removeCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceListener) {
		throw new UnsupportedOperationException();
	}
	
}
