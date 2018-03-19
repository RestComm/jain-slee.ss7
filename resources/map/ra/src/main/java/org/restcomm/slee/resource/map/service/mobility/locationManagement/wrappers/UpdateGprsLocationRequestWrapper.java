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
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ADDInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.EPSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SGSNCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UESRVCCCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UsedRATType;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class UpdateGprsLocationRequestWrapper extends MobilityMessageWrapper<UpdateGprsLocationRequest> implements UpdateGprsLocationRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.locationManagement.UPDATE_GPRS_LOCATION_REQUEST";

	/**
	 * @param mapDialog
	 */
	public UpdateGprsLocationRequestWrapper(MAPDialogMobilityWrapper mapDialog, UpdateGprsLocationRequest req) {
		super(mapDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public ADDInfo getADDInfo() {
		return this.wrappedEvent.getADDInfo();
	}

	@Override
	public boolean getAreaRestricted() {
		return this.wrappedEvent.getAreaRestricted();
	}

	@Override
	public EPSInfo getEPSInfo() {
		return this.wrappedEvent.getEPSInfo();
	}

	@Override
	public boolean getEpsSubscriptionDataNotNeeded() {
		return this.wrappedEvent.getEpsSubscriptionDataNotNeeded();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public boolean getGprsSubscriptionDataNotNeeded() {
		return this.wrappedEvent.getGprsSubscriptionDataNotNeeded();
	}

	@Override
	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	@Override
	public boolean getInformPreviousNetworkEntity() {
		return this.wrappedEvent.getInformPreviousNetworkEntity();
	}

	@Override
	public boolean getNodeTypeIndicator() {
		return this.wrappedEvent.getNodeTypeIndicator();
	}

	@Override
	public boolean getPsLCSNotSupportedByUE() {
		return this.wrappedEvent.getPsLCSNotSupportedByUE();
	}

	@Override
	public SGSNCapability getSGSNCapability() {
		return this.wrappedEvent.getSGSNCapability();
	}

	@Override
	public boolean getServingNodeTypeIndicator() {
		return this.wrappedEvent.getServingNodeTypeIndicator();
	}

	@Override
	public GSNAddress getSgsnAddress() {
		return this.wrappedEvent.getSgsnAddress();
	}

	@Override
	public ISDNAddressString getSgsnNumber() {
		return this.wrappedEvent.getSgsnNumber();
	}

	@Override
	public boolean getSkipSubscriberDataUpdate() {
		return this.wrappedEvent.getSkipSubscriberDataUpdate();
	}

	@Override
	public UESRVCCCapability getUESRVCCCapability() {
		return this.wrappedEvent.getUESRVCCCapability();
	}

	@Override
	public boolean getUeReachableIndicator() {
		return this.wrappedEvent.getUeReachableIndicator();
	}

	@Override
	public UsedRATType getUsedRATType() {
		return this.wrappedEvent.getUsedRATType();
	}

	@Override
	public GSNAddress getVGmlcAddress() {
		return this.wrappedEvent.getVGmlcAddress();
	}

	@Override
	public String toString() {
		return "UpdateGprsLocationRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
