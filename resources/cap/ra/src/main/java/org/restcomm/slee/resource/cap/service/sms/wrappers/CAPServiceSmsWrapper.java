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

package org.restcomm.slee.resource.cap.service.sms.wrappers;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPProvider;
import org.restcomm.protocols.ss7.cap.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSmsListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.wrappers.CAPProviderWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPServiceSmsWrapper implements CAPServiceSms {

	protected CAPServiceSms wrappedSms;
	protected CAPProviderWrapper capProviderWrapper;

	/**
	 * @param CAPServiceCircuitSwitchedCall
	 */
	public CAPServiceSmsWrapper(CAPProviderWrapper capProviderWrapper, CAPServiceSms capServiceSms) {
		this.wrappedSms = capServiceSms;
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
		return this.wrappedSms.isServingService(dialogApplicationContext);
	}

	public boolean isActivated() {
		return this.wrappedSms.isActivated();
	}

    public CAPDialogSms createNewDialog(CAPApplicationContext appCntx, SccpAddress origAddress, SccpAddress destAddress, Long localTrId) throws CAPException {

    	capProviderWrapper.getRa().getDefaultUsageParameters().incrementMessages(1L);
        CAPDialogSms capDialog = this.wrappedSms.createNewDialog(appCntx, origAddress, destAddress, localTrId);
        CAPDialogActivityHandle activityHandle = new CAPDialogActivityHandle(capDialog.getLocalDialogId());

        CAPDialogSmsWrapper dw = new CAPDialogSmsWrapper(capDialog, activityHandle, this.capProviderWrapper.getRa());
        capDialog.setUserObject(dw);

        try {
            this.capProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new CAPException(e);
        }

        return dw;
    }

	public CAPDialogSms createNewDialog(CAPApplicationContext appCntx, SccpAddress origAddress, SccpAddress destAddress) throws CAPException {
        return this.createNewDialog(appCntx, origAddress, destAddress, null);
	}

	public void addCAPServiceListener(CAPServiceSmsListener capServiceListener) {
		throw new UnsupportedOperationException();
	}

	public void removeCAPServiceListener(CAPServiceSmsListener capServiceListener) {
		throw new UnsupportedOperationException();
	}

}
