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

package org.restcomm.slee.resource.map.service.pdpContextActivation.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivationListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.wrappers.MAPProviderWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MAPServicePdpContextActivationWrapper implements MAPServicePdpContextActivation {

	protected MAPServicePdpContextActivation wrappedPdpContextActivation;
	protected MAPProviderWrapper mapProviderWrapper;

	/**
	 * @param mapServicePdpContextActivation
	 */
	public MAPServicePdpContextActivationWrapper(MAPProviderWrapper mapProviderWrapper, MAPServicePdpContextActivation mapPdpContextActivation) {
		this.wrappedPdpContextActivation = mapPdpContextActivation;
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
		return this.wrappedPdpContextActivation.isServingService(dialogApplicationContext);
	}

	public boolean isActivated() {
		return this.wrappedPdpContextActivation.isActivated();
	}

	public void addMAPServiceListener(MAPServicePdpContextActivationListener arg0) {
		throw new UnsupportedOperationException();
	}

    public MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference,
            SccpAddress destAddress, AddressString destReference, Long localTrId) throws MAPException {

        MAPDialogPdpContextActivation mapDialog = this.wrappedPdpContextActivation.createNewDialog(appCntx, origAddress, origReference, destAddress,
                destReference, localTrId);
        MAPDialogActivityHandle activityHandle = new MAPDialogActivityHandle(mapDialog.getLocalDialogId());
        MAPDialogPdpContextActivationWrapper dw = new MAPDialogPdpContextActivationWrapper(mapDialog, activityHandle, this.mapProviderWrapper.getRa());
        mapDialog.setUserObject(dw);

        try {
            this.mapProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new MAPException(e);
        }

        return dw;
    }

	public MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference,
			SccpAddress destAddress, AddressString destReference) throws MAPException {
        return this.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, null);
	}

	public void removeMAPServiceListener(MAPServicePdpContextActivationListener arg0) {
		throw new UnsupportedOperationException();
	}

}
