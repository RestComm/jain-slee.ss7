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

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ADDInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.VLRCapability;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class UpdateLocationRequestWrapper extends MobilityMessageWrapper<UpdateLocationRequest> implements UpdateLocationRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.locationManagement.UPDATE_LOCATION_REQUEST";

	/**
	 * @param mAPDialog
	 */
	public UpdateLocationRequestWrapper(MAPDialogMobilityWrapper mAPDialog, UpdateLocationRequest req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	public ISDNAddressString getMscNumber() {
		return this.wrappedEvent.getMscNumber();
	}

	public ISDNAddressString getRoamingNumber() {
		return this.wrappedEvent.getRoamingNumber();
	}

	public ISDNAddressString getVlrNumber() {
		return this.wrappedEvent.getVlrNumber();
	}

	public LMSI getLmsi() {
		return this.wrappedEvent.getLmsi();
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public VLRCapability getVlrCapability() {
		return this.wrappedEvent.getVlrCapability();
	}

	public boolean getInformPreviousNetworkEntity() {
		return this.wrappedEvent.getInformPreviousNetworkEntity();
	}

	public boolean getCsLCSNotSupportedByUE() {
		return this.wrappedEvent.getCsLCSNotSupportedByUE();
	}

	public GSNAddress getVGmlcAddress() {
		return this.wrappedEvent.getVGmlcAddress();
	}

	public ADDInfo getADDInfo() {
		return this.wrappedEvent.getADDInfo();
	}

	public PagingArea getPagingArea() {
		return this.wrappedEvent.getPagingArea();
	}

	public boolean getSkipSubscriberDataUpdate() {
		return this.wrappedEvent.getSkipSubscriberDataUpdate();
	}

	public boolean getRestorationIndicator() {
		return this.wrappedEvent.getRestorationIndicator();
	}

	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "UpdateLocationRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}
}

