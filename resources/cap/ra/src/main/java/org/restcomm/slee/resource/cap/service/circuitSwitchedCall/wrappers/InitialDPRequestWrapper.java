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

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.RedirectingPartyIDCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.EventTypeBCSM;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.InitialDPRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.BearerCapability;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CGEncountered;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.IPSSPCapabilities;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InitialDPArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGIndex;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class InitialDPRequestWrapper extends CircuitSwitchedCallMessageWrapper<InitialDPRequest> implements InitialDPRequest {

	private static final String EVENT_TYPE_NAME = "ss7.cap.service.circuitSwitchedCall.INITIAL_DP_REQUEST";

	public InitialDPRequestWrapper(CAPDialogCircuitSwitchedCallWrapper capDialog, InitialDPRequest req) {
		super(capDialog, EVENT_TYPE_NAME, req);
	}

	public int getServiceKey() {
		return this.wrappedEvent.getServiceKey();
	}

	public CalledPartyNumberCap getCalledPartyNumber() {
		return this.wrappedEvent.getCalledPartyNumber();
	}

	public CallingPartyNumberCap getCallingPartyNumber() {
		return this.wrappedEvent.getCallingPartyNumber();
	}

	public CallingPartysCategoryInap getCallingPartysCategory() {
		return this.wrappedEvent.getCallingPartysCategory();
	}

	public CGEncountered getCGEncountered() {
		return this.wrappedEvent.getCGEncountered();
	}

	public IPSSPCapabilities getIPSSPCapabilities() {
		return this.wrappedEvent.getIPSSPCapabilities();
	}

	public LocationNumberCap getLocationNumber() {
		return this.wrappedEvent.getLocationNumber();
	}

	public OriginalCalledNumberCap getOriginalCalledPartyID() {
		return this.wrappedEvent.getOriginalCalledPartyID();
	}

	public CAPExtensions getExtensions() {
		return this.wrappedEvent.getExtensions();
	}

	public HighLayerCompatibilityInap getHighLayerCompatibility() {
		return this.wrappedEvent.getHighLayerCompatibility();
	}

	public Digits getAdditionalCallingPartyNumber() {
		return this.wrappedEvent.getAdditionalCallingPartyNumber();
	}

	public BearerCapability getBearerCapability() {
		return this.wrappedEvent.getBearerCapability();
	}

	public EventTypeBCSM getEventTypeBCSM() {
		return this.wrappedEvent.getEventTypeBCSM();
	}

	public RedirectingPartyIDCap getRedirectingPartyID() {
		return this.wrappedEvent.getRedirectingPartyID();
	}

	public RedirectionInformationInap getRedirectionInformation() {
		return this.wrappedEvent.getRedirectionInformation();
	}

	public CauseCap getCause() {
		return this.wrappedEvent.getCause();
	}

	public ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo() {
		return this.wrappedEvent.getServiceInteractionIndicatorsTwo();
	}

	public Carrier getCarrier() {
		return this.wrappedEvent.getCarrier();
	}

	public CUGIndex getCugIndex() {
		return this.wrappedEvent.getCugIndex();
	}

	public CUGInterlock getCugInterlock() {
		return this.wrappedEvent.getCugInterlock();
	}

	public boolean getCugOutgoingAccess() {
		return this.wrappedEvent.getCugOutgoingAccess();
	}

	public IMSI getIMSI() {
		return this.wrappedEvent.getIMSI();
	}

	public SubscriberState getSubscriberState() {
		return this.wrappedEvent.getSubscriberState();
	}

	public LocationInformation getLocationInformation() {
		return this.wrappedEvent.getLocationInformation();
	}

	public ExtBasicServiceCode getExtBasicServiceCode() {
		return this.wrappedEvent.getExtBasicServiceCode();
	}

	public CallReferenceNumber getCallReferenceNumber() {
		return this.wrappedEvent.getCallReferenceNumber();
	}

	public ISDNAddressString getMscAddress() {
		return this.wrappedEvent.getMscAddress();
	}

	public CalledPartyBCDNumber getCalledPartyBCDNumber() {
		return this.wrappedEvent.getCalledPartyBCDNumber();
	}

	public TimeAndTimezone getTimeAndTimezone() {
		return this.wrappedEvent.getTimeAndTimezone();
	}

	public boolean getCallForwardingSSPending() {
		return this.wrappedEvent.getCallForwardingSSPending();
	}

	public InitialDPArgExtension getInitialDPArgExtension() {
		return this.wrappedEvent.getInitialDPArgExtension();
	}

	@Override
	public String toString() {
		return "InitialDPRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
