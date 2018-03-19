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

package org.restcomm.slee.resource.cap.service.gprs.wrappers;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.gprs.InitialDpGprsRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPInitiationType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.SGSNCapabilities;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;

/**
 * 
 * @author Lasith Waruna Perera
 * 
 */
public class InitialDpGprsRequestWrapper extends GprsMessageWrapper<InitialDpGprsRequest> 
implements  InitialDpGprsRequest{
	
	private static final String EVENT_TYPE_NAME = "ss7.cap.service.gprs.INITIAL_DP_GPRS_REQUEST";
	
	public InitialDpGprsRequestWrapper(CAPDialogGprsWrapper capDialog, InitialDpGprsRequest req) {
		super(capDialog, EVENT_TYPE_NAME, req);
	}
	
	@Override
	public AccessPointName getAccessPointName() {
		return this.wrappedEvent.getAccessPointName();
	}

	@Override
	public GPRSChargingID getChargingID() {
		return this.wrappedEvent.getChargingID();
	}

	@Override
	public EndUserAddress getEndUserAddress() {
		return this.wrappedEvent.getEndUserAddress();
	}

	@Override
	public CAPExtensions getExtensions() {
		return this.wrappedEvent.getExtensions();
	}

	@Override
	public GPRSEventType getGPRSEventType() {
		return this.wrappedEvent.getGPRSEventType();
	}

	@Override
	public GPRSMSClass getGPRSMSClass() {
		return this.wrappedEvent.getGPRSMSClass();
	}

	@Override
	public GSNAddress getGSNAddress() {
		return this.wrappedEvent.getGSNAddress();
	}

	@Override
	public IMEI getImei() {
		return this.wrappedEvent.getImei();
	}

	@Override
	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	@Override
	public LocationInformationGPRS getLocationInformationGPRS() {
		return this.wrappedEvent.getLocationInformationGPRS();
	}

	@Override
	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	@Override
	public PDPInitiationType getPDPInitiationType() {
		return this.wrappedEvent.getPDPInitiationType();
	}

	@Override
	public QualityOfService getQualityOfService() {
		return this.wrappedEvent.getQualityOfService();
	}

	@Override
	public RAIdentity getRouteingAreaIdentity() {
		return this.wrappedEvent.getRouteingAreaIdentity();
	}

	@Override
	public SGSNCapabilities getSGSNCapabilities() {
		return this.wrappedEvent.getSGSNCapabilities();
	}

	@Override
	public boolean getSecondaryPDPContext() {
		return this.wrappedEvent.getSecondaryPDPContext();
	}

	@Override
	public int getServiceKey() {
		return this.wrappedEvent.getServiceKey();
	}

	@Override
	public TimeAndTimezone getTimeAndTimezone() {
		return this.wrappedEvent.getTimeAndTimezone();
	}

	@Override
	public String toString() {
		return "InitialDpGprsRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}


}
