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

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.InitiateCallAttemptResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class InitiateCallAttemptResponseWrapper extends CircuitSwitchedCallMessageWrapper<InitiateCallAttemptResponse> implements InitiateCallAttemptResponse {

    private static final String EVENT_TYPE_NAME = "ss7.cap.service.circuitSwitchedCall.INITIATE_CALL_ATTEMPT_RESPONSE";

    public InitiateCallAttemptResponseWrapper(CAPDialogCircuitSwitchedCallWrapper capDialog, InitiateCallAttemptResponse req) {
        super(capDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public SupportedCamelPhases getSupportedCamelPhases() {
        return this.wrappedEvent.getSupportedCamelPhases();
    }

    @Override
    public OfferedCamel4Functionalities getOfferedCamel4Functionalities() {
        return this.wrappedEvent.getOfferedCamel4Functionalities();
    }

    @Override
    public CAPExtensions getExtensions() {
        return this.wrappedEvent.getExtensions();
    }

    @Override
    public boolean getReleaseCallArgExtensionAllowed() {
        return this.wrappedEvent.getReleaseCallArgExtensionAllowed();
    }

    @Override
    public String toString() {
        return "InitiateCallAttemptResponseWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
