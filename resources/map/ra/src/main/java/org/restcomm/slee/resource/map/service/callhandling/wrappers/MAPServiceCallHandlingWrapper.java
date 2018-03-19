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

package org.restcomm.slee.resource.map.service.callhandling.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandlingListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.wrappers.MAPProviderWrapper;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MAPServiceCallHandlingWrapper implements MAPServiceCallHandling {

	protected MAPServiceCallHandling wrappedCallHandling;
	protected MAPProviderWrapper mapProviderWrapper;

	/**
	 * @param mapServiceCallHandling
	 */
	public MAPServiceCallHandlingWrapper(MAPProviderWrapper mapProviderWrapper, MAPServiceCallHandling mapCallHandling) {
		this.wrappedCallHandling = mapCallHandling;
		this.mapProviderWrapper = mapProviderWrapper;
	}

	public void acivate() {
		throw new UnsupportedOperationException();
	}

	public void deactivate() {
		throw new UnsupportedOperationException();
	}

	public MAPProvider getMAPProvider() {
		return this.mapProviderWrapper;
	}

	public ServingCheckData isServingService(MAPApplicationContext dialogApplicationContext) {
		return this.wrappedCallHandling.isServingService(dialogApplicationContext);
	}

	public boolean isActivated() {
		return this.wrappedCallHandling.isActivated();
	}

    @Override
    public MAPDialogCallHandling createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        MAPDialogCallHandling mapDialog = this.wrappedCallHandling.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, localTrId);
        MAPDialogActivityHandle activityHandle = new MAPDialogActivityHandle(mapDialog.getLocalDialogId());
        MAPDialogCallHandlingWrapper dw = new MAPDialogCallHandlingWrapper(mapDialog, activityHandle, this.mapProviderWrapper.getRa());
        mapDialog.setUserObject(dw);

        try {
            this.mapProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new MAPException(e);
        }

        return dw;
    }

    public MAPDialogCallHandling createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference) throws MAPException {
        return this.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, null);
    }

	public void addMAPServiceListener(MAPServiceCallHandlingListener mapServiceListener) {
		throw new UnsupportedOperationException();
	}

	public void removeMAPServiceListener(MAPServiceCallHandlingListener mapServiceListener) {
		throw new UnsupportedOperationException();
	}

}
