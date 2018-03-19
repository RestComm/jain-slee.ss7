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

package org.restcomm.slee.resource.map.service.lsm.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.wrappers.MAPProviderWrapper;

/**
 * @author baranowb
 *
 */
public class MAPServiceLsmWrapper implements MAPServiceLsm {
	
	protected MAPServiceLsm wrappedLSM;
	protected MAPProviderWrapper mapProviderWrapper;

	/**
	 * @param mapServiceSupplementary
	 */
	public MAPServiceLsmWrapper(MAPProviderWrapper mapProviderWrapper, MAPServiceLsm mapServiceSupplementary) {
		this.wrappedLSM = mapServiceSupplementary;
		this.mapProviderWrapper = mapProviderWrapper;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#acivate()
	 */
	public void acivate() {
		throw new UnsupportedOperationException();

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#deactivate()
	 */
	public void deactivate() {
		throw new UnsupportedOperationException();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#getMAPProvider()
	 */
	public MAPProvider getMAPProvider() {
		return this.mapProviderWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#isActivated()
	 */
	public boolean isActivated() {
		return this.wrappedLSM.isActivated();
	}


	/* (non-Javadoc)
	 * @see org.restcomm.protocols.ss7.map.api.MAPServiceBase#isServingService(org.restcomm.protocols.ss7.map.api.MAPApplicationContext)
	 */
	public ServingCheckData isServingService(MAPApplicationContext mapapplicationcontext) {
		return this.wrappedLSM.isServingService(mapapplicationcontext);
	}

	/* (non-Javadoc)
	 * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm#addMAPServiceListener(org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener)
	 */
	public void addMAPServiceListener(MAPServiceLsmListener mapservicelsmlistener) {
		throw new UnsupportedOperationException();

	}

    public MAPDialogLsm createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        MAPDialogLsm mapDialogLsm = this.wrappedLSM.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, localTrId);
        MAPDialogActivityHandle activityHandle = new MAPDialogActivityHandle(mapDialogLsm.getLocalDialogId());
        MAPDialogLsmWrapper dw = new MAPDialogLsmWrapper(mapDialogLsm, activityHandle, this.mapProviderWrapper.getRa());
        mapDialogLsm.setUserObject(dw);
        
        try {
            this.mapProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new MAPException(e);
        }
        
        return dw;
    }

    public MAPDialogLsm createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference) throws MAPException {
        return this.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, null);
    }

	/* (non-Javadoc)
	 * @see org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm#removeMAPServiceListener(org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener)
	 */
	public void removeMAPServiceListener(MAPServiceLsmListener mapservicelsmlistener) {
		throw new UnsupportedOperationException();
	}

}
