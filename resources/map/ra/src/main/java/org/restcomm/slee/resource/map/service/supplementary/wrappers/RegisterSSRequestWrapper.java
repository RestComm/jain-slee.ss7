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

package org.restcomm.slee.resource.map.service.supplementary.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.RegisterSSRequest;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 * 
 * @author sergey vetyutnev
 *
 */
public class RegisterSSRequestWrapper extends SupplementaryMessageWrapper<RegisterSSRequest> implements RegisterSSRequest {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.suplementary.REGISTER_SS_REQUEST";

    public RegisterSSRequestWrapper(MAPDialogSupplementaryWrapper mAPDialog, RegisterSSRequest req) {
        super(mAPDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public SSCode getSsCode() {
        return this.wrappedEvent.getSsCode();
    }

    @Override
    public BasicServiceCode getBasicService() {
        return this.wrappedEvent.getBasicService();
    }

    @Override
    public AddressString getForwardedToNumber() {
        return this.wrappedEvent.getForwardedToNumber();
    }

    @Override
    public ISDNAddressString getForwardedToSubaddress() {
        return this.wrappedEvent.getForwardedToSubaddress();
    }

    @Override
    public Integer getNoReplyConditionTime() {
        return this.wrappedEvent.getNoReplyConditionTime();
    }

    @Override
    public EMLPPPriority getDefaultPriority() {
        return this.wrappedEvent.getDefaultPriority();
    }

    @Override
    public Integer getNbrUser() {
        return this.wrappedEvent.getNbrUser();
    }

    @Override
    public ISDNAddressString getLongFTNSupported() {
        return this.wrappedEvent.getLongFTNSupported();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegisterSSRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
