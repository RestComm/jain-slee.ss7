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

import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.EstablishTemporaryConnectionRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class EstablishTemporaryConnectionRequestWrapper extends CircuitSwitchedCallMessageWrapper<EstablishTemporaryConnectionRequest> implements
		EstablishTemporaryConnectionRequest {

	private static final String EVENT_TYPE_NAME = "ss7.cap.service.circuitSwitchedCall.ESTABLISH_TEMPORARY_CONNECTION_REQUEST";

	public EstablishTemporaryConnectionRequestWrapper(CAPDialogCircuitSwitchedCallWrapper capDialog, EstablishTemporaryConnectionRequest req) {
		super(capDialog, EVENT_TYPE_NAME, req);
	}

	public Digits getAssistingSSPIPRoutingAddress() {
		return this.wrappedEvent.getAssistingSSPIPRoutingAddress();
	}

	public Integer getCallSegmentID() {
		return this.wrappedEvent.getCallSegmentID();
	}

	public CallingPartyNumberCap getCallingPartyNumber() {
		return this.wrappedEvent.getCallingPartyNumber();
	}

	public Carrier getCarrier() {
		return this.wrappedEvent.getCarrier();
	}

	public LocationNumberCap getChargeNumber() {
		return this.wrappedEvent.getChargeNumber();
	}

	public Digits getCorrelationID() {
		return this.wrappedEvent.getCorrelationID();
	}

	public CAPExtensions getExtensions() {
		return this.wrappedEvent.getExtensions();
	}

	public NAOliInfo getNAOliInfo() {
		return this.wrappedEvent.getNAOliInfo();
	}

	public OriginalCalledNumberCap getOriginalCalledPartyID() {
		return this.wrappedEvent.getOriginalCalledPartyID();
	}

	public ScfID getScfID() {
		return this.wrappedEvent.getScfID();
	}

	public ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo() {
		return this.wrappedEvent.getServiceInteractionIndicatorsTwo();
	}

	@Override
	public String toString() {
		return "EstablishTemporaryConnectionRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
