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

package org.restcomm.slee.resource.map.service.mobility.subscriberInformation.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedSubscriptionInfo;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class AnyTimeSubscriptionInterrogationRequestWrapper extends
        MobilityMessageWrapper<AnyTimeSubscriptionInterrogationRequest> implements AnyTimeSubscriptionInterrogationRequest {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.subscriberinfo.ANY_TIME_SUBSCRIPTION_INTERROGATION_REQUEST";

    public AnyTimeSubscriptionInterrogationRequestWrapper(MAPDialogMobilityWrapper mapDialogWrapper,
            AnyTimeSubscriptionInterrogationRequest wrappedEvent) {
        super(mapDialogWrapper, EVENT_TYPE_NAME, wrappedEvent);
    }

    @Override
    public SubscriberIdentity getSubscriberIdentity() {
        return this.wrappedEvent.getSubscriberIdentity();
    }

    @Override
    public RequestedSubscriptionInfo getRequestedSubscriptionInfo() {
        return this.wrappedEvent.getRequestedSubscriptionInfo();
    }

    @Override
    public ISDNAddressString getGsmScfAddress() {
        return this.wrappedEvent.getGsmScfAddress();
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.wrappedEvent.getExtensionContainer();
    }

    @Override
    public boolean getLongFTNSupported() {
        return this.wrappedEvent.getLongFTNSupported();
    }

    @Override
    public String toString() {
        return "AnyTimeSubscriptionInterrogationRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
