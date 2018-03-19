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

package org.restcomm.slee.resource.map.service.mobility.locationManagement.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancellationType;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.IMSIWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.TypeOfUpdate;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CancelLocationRequestWrapper extends MobilityMessageWrapper<CancelLocationRequest> implements CancelLocationRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.locationManagement.CANCEL_LOCATION_REQUEST";

	/**
	 * @param mapDialog
	 */
	public CancelLocationRequestWrapper(MAPDialogMobilityWrapper mapDialog, CancelLocationRequest req) {
		super(mapDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	@Override
	public IMSIWithLMSI getImsiWithLmsi() {
		return this.wrappedEvent.getImsiWithLmsi();
	}

	@Override
	public CancellationType getCancellationType() {
		return this.wrappedEvent.getCancellationType();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public TypeOfUpdate getTypeOfUpdate() {
		return this.wrappedEvent.getTypeOfUpdate();
	}

	@Override
	public boolean getMtrfSupportedAndAuthorized() {
		return this.wrappedEvent.getMtrfSupportedAndAuthorized();
	}

	@Override
	public boolean getMtrfSupportedAndNotAuthorized() {
		return this.wrappedEvent.getMtrfSupportedAndNotAuthorized();
	}

	@Override
	public ISDNAddressString getNewMSCNumber() {
		return this.wrappedEvent.getNewMSCNumber();
	}

	@Override
	public ISDNAddressString getNewVLRNumber() {
		return this.wrappedEvent.getNewVLRNumber();
	}

	@Override
	public LMSI getNewLmsi() {
		return this.wrappedEvent.getNewLmsi();
	}

	@Override
	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "CancelLocationRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
