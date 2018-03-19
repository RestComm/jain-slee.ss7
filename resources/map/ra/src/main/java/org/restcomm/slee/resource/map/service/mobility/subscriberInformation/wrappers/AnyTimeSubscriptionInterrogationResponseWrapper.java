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

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallBarringData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallForwardingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallHoldData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallWaitingData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClipData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ClirData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EctData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSISDNBS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ODBInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class AnyTimeSubscriptionInterrogationResponseWrapper extends
        MobilityMessageWrapper<AnyTimeSubscriptionInterrogationResponse> implements AnyTimeSubscriptionInterrogationResponse {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.subscriberinfo.ANY_TIME_SUBSCRIPTION_INTERROGATION_RESPONSE";

    public AnyTimeSubscriptionInterrogationResponseWrapper(MAPDialogMobilityWrapper mapDialogWrapper,
            AnyTimeSubscriptionInterrogationResponse wrappedEvent) {
        super(mapDialogWrapper, EVENT_TYPE_NAME, wrappedEvent);
    }

    @Override
    public CallForwardingData getCallForwardingData() {
        return this.wrappedEvent.getCallForwardingData();
    }

    @Override
    public CallBarringData getCallBarringData() {
        return this.wrappedEvent.getCallBarringData();
    }

    @Override
    public ODBInfo getOdbInfo() {
        return this.wrappedEvent.getOdbInfo();
    }

    @Override
    public CAMELSubscriptionInfo getCamelSubscriptionInfo() {
        return this.wrappedEvent.getCamelSubscriptionInfo();
    }

    @Override
    public SupportedCamelPhases getsupportedVlrCamelPhases() {
        return this.wrappedEvent.getsupportedVlrCamelPhases();
    }

    @Override
    public SupportedCamelPhases getsupportedSgsnCamelPhases() {
        return this.wrappedEvent.getsupportedSgsnCamelPhases();
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.wrappedEvent.getExtensionContainer();
    }

    @Override
    public OfferedCamel4CSIs getOfferedCamel4CSIsInVlr() {
        return this.wrappedEvent.getOfferedCamel4CSIsInVlr();
    }

    @Override
    public OfferedCamel4CSIs getOfferedCamel4CSIsInSgsn() {
        return this.wrappedEvent.getOfferedCamel4CSIsInSgsn();
    }

    @Override
    public ArrayList<MSISDNBS> getMsisdnBsList() {
        return this.wrappedEvent.getMsisdnBsList();
    }

    @Override
    public ArrayList<CSGSubscriptionData> getCsgSubscriptionDataList() {
        return this.wrappedEvent.getCsgSubscriptionDataList();
    }

    @Override
    public CallWaitingData getCwData() {
        return this.wrappedEvent.getCwData();
    }

    @Override
    public CallHoldData getChData() {
        return this.wrappedEvent.getChData();
    }

    @Override
    public ClipData getClipData() {
        return this.wrappedEvent.getClipData();
    }

    @Override
    public ClirData getClirData() {
        return this.wrappedEvent.getClirData();
    }

    @Override
    public EctData getEctData() {
        return this.wrappedEvent.getEctData();
    }

    @Override
    public String toString() {
        return "AnyTimeSubscriptionInterrogationResponseWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
