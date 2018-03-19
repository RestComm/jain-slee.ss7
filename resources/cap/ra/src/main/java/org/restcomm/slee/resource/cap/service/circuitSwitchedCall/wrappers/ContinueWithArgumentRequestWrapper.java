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

import java.util.List;

import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ContinueWithArgumentRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ContinueWithArgumentArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ContinueWithArgumentRequestWrapper extends CircuitSwitchedCallMessageWrapper<ContinueWithArgumentRequest> implements ContinueWithArgumentRequest {

    private static final String EVENT_TYPE_NAME = "ss7.cap.service.circuitSwitchedCall.CONTINUE_WITH_ARGUMENT_REQUEST";

    public ContinueWithArgumentRequestWrapper(CAPDialogCircuitSwitchedCallWrapper capDialog, ContinueWithArgumentRequest req) {
        super(capDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public AlertingPatternCap getAlertingPattern() {
        return this.wrappedEvent.getAlertingPattern();
    }

    @Override
    public CAPExtensions getExtensions() {
        return this.wrappedEvent.getExtensions();
    }

    @Override
    public ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo() {
        return this.wrappedEvent.getServiceInteractionIndicatorsTwo();
    }

    @Override
    public CallingPartysCategoryInap getCallingPartysCategory() {
        return this.wrappedEvent.getCallingPartysCategory();
    }

    @Override
    public List<GenericNumberCap> getGenericNumbers() {
        return this.wrappedEvent.getGenericNumbers();
    }

    @Override
    public CUGInterlock getCugInterlock() {
        return this.wrappedEvent.getCugInterlock();
    }

    @Override
    public boolean getCugOutgoingAccess() {
        return this.wrappedEvent.getCugOutgoingAccess();
    }

    @Override
    public LocationNumberCap getChargeNumber() {
        return this.wrappedEvent.getChargeNumber();
    }

    @Override
    public Carrier getCarrier() {
        return this.wrappedEvent.getCarrier();
    }

    @Override
    public boolean getSuppressionOfAnnouncement() {
        return this.wrappedEvent.getSuppressionOfAnnouncement();
    }

    @Override
    public NAOliInfo getNaOliInfo() {
        return this.wrappedEvent.getNaOliInfo();
    }

    @Override
    public boolean getBorInterrogationRequested() {
        return this.wrappedEvent.getBorInterrogationRequested();
    }

    @Override
    public boolean getSuppressOCsi() {
        return this.wrappedEvent.getSuppressOCsi();
    }

    @Override
    public ContinueWithArgumentArgExtension getContinueWithArgumentArgExtension() {
        return this.wrappedEvent.getContinueWithArgumentArgExtension();
    }

    @Override
    public String toString() {
        return "ContinueWithArgumentRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
