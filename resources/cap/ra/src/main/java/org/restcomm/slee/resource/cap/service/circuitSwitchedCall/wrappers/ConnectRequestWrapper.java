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

import java.util.ArrayList;
import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.RedirectingPartyIDCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ConnectRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ConnectRequestWrapper extends CircuitSwitchedCallMessageWrapper<ConnectRequest> implements ConnectRequest {

	private static final String EVENT_TYPE_NAME = "ss7.cap.service.circuitSwitchedCall.CONNECT_REQUEST";

	public ConnectRequestWrapper(CAPDialogCircuitSwitchedCallWrapper capDialog, ConnectRequest req) {
		super(capDialog, EVENT_TYPE_NAME, req);
	}

	public AlertingPatternCap getAlertingPattern() {
		return this.wrappedEvent.getAlertingPattern();
	}

	public boolean getBorInterrogationRequested() {
		return this.wrappedEvent.getBorInterrogationRequested();
	}

	public CUGInterlock getCUGInterlock() {
		return this.wrappedEvent.getCUGInterlock();
	}

	public CallingPartysCategoryInap getCallingPartysCategory() {
		return this.wrappedEvent.getCallingPartysCategory();
	}

	public Carrier getCarrier() {
		return this.wrappedEvent.getCarrier();
	}

	public LocationNumberCap getChargeNumber() {
		return this.wrappedEvent.getChargeNumber();
	}

	public boolean getCugOutgoingAccess() {
		return this.wrappedEvent.getCugOutgoingAccess();
	}

	public DestinationRoutingAddress getDestinationRoutingAddress() {
		return this.wrappedEvent.getDestinationRoutingAddress();
	}

	public CAPExtensions getExtensions() {
		return this.wrappedEvent.getExtensions();
	}

	public ArrayList<GenericNumberCap> getGenericNumbers() {
		return this.wrappedEvent.getGenericNumbers();
	}

	public LegID getLegToBeConnected() {
		return this.wrappedEvent.getLegToBeConnected();
	}

	public NAOliInfo getNAOliInfo() {
		return this.wrappedEvent.getNAOliInfo();
	}

	public boolean getOCSIApplicable() {
		return this.wrappedEvent.getOCSIApplicable();
	}

	public OriginalCalledNumberCap getOriginalCalledPartyID() {
		return this.wrappedEvent.getOriginalCalledPartyID();
	}

	public RedirectingPartyIDCap getRedirectingPartyID() {
		return this.wrappedEvent.getRedirectingPartyID();
	}

	public RedirectionInformationInap getRedirectionInformation() {
		return this.wrappedEvent.getRedirectionInformation();
	}

	public ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo() {
		return this.wrappedEvent.getServiceInteractionIndicatorsTwo();
	}

	public boolean getSuppressionOfAnnouncement() {
		return this.wrappedEvent.getSuppressionOfAnnouncement();
	}

    @Override
    public boolean getSuppressNCSI() {
        return this.wrappedEvent.getSuppressNCSI();
    }

	public String toString() {
		return "ConnectRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
