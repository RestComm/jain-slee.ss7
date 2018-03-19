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

package org.restcomm.slee.resource.map.service.mobility.authentication.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AccessType;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.FailureCause;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 *
 */
public class AuthenticationFailureReportRequestWrapper extends MobilityMessageWrapper<AuthenticationFailureReportRequest> implements
        AuthenticationFailureReportRequest {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.authentication.AUTHENTICATION_FAILURE_REPORT_REQUEST";

    public AuthenticationFailureReportRequestWrapper(MAPDialogMobilityWrapper mAPDialog, AuthenticationFailureReportRequest req) {
        super(mAPDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public AccessType getAccessType() {
        return this.wrappedEvent.getAccessType();
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.wrappedEvent.getExtensionContainer();
    }

    @Override
    public FailureCause getFailureCause() {
        return this.wrappedEvent.getFailureCause();
    }

    @Override
    public IMSI getImsi() {
        return this.wrappedEvent.getImsi();
    }

    @Override
    public byte[] getRand() {
        return this.wrappedEvent.getRand();
    }

    @Override
    public Boolean getReAttempt() {
        return this.wrappedEvent.getReAttempt();
    }

    @Override
    public ISDNAddressString getSgsnNumber() {
        return this.wrappedEvent.getSgsnNumber();
    }

    @Override
    public ISDNAddressString getVlrNumber() {
        return this.wrappedEvent.getVlrNumber();
    }

    @Override
    public String toString() {
        return "AuthenticationFailureReportRequestWrapper [wrappedEvent=" + wrappedEvent + "]";
    }

}
