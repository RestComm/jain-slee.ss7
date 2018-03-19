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

package org.restcomm.slee.resource.cap.service.sms.wrappers;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.service.sms.InitialDPSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSAddressString;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPDataCodingScheme;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPProtocolIdentifier;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPShortMessageSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPValidityPeriod;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class InitialDPSMSRequestWrapper extends SmsMessageWrapper<InitialDPSMSRequest> implements InitialDPSMSRequest {

    private static final String EVENT_TYPE_NAME = "ss7.cap.service.sms.INITIAL_DP_SMS_REQUEST";

    public InitialDPSMSRequestWrapper(CAPDialogSmsWrapper capDialog, InitialDPSMSRequest req) {
        super(capDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public int getServiceKey() {
        return this.wrappedEvent.getServiceKey();
    }

    @Override
    public CalledPartyBCDNumber getDestinationSubscriberNumber() {
        return this.wrappedEvent.getDestinationSubscriberNumber();
    }

    @Override
    public SMSAddressString getCallingPartyNumber() {
        return this.wrappedEvent.getCallingPartyNumber();
    }

    @Override
    public EventTypeSMS getEventTypeSMS() {
        return this.wrappedEvent.getEventTypeSMS();
    }

    @Override
    public IMSI getImsi() {
        return this.wrappedEvent.getImsi();
    }

    @Override
    public LocationInformation getLocationInformationMSC() {
        return this.wrappedEvent.getLocationInformationMSC();
    }

    @Override
    public LocationInformationGPRS getLocationInformationGPRS() {
        return this.wrappedEvent.getLocationInformationGPRS();
    }

    @Override
    public ISDNAddressString getSMSCAddress() {
        return this.wrappedEvent.getSMSCAddress();
    }

    @Override
    public TimeAndTimezone getTimeAndTimezone() {
        return this.wrappedEvent.getTimeAndTimezone();
    }

    @Override
    public TPShortMessageSpecificInfo getTPShortMessageSpecificInfo() {
        return this.wrappedEvent.getTPShortMessageSpecificInfo();
    }

    @Override
    public TPProtocolIdentifier getTPProtocolIdentifier() {
        return this.wrappedEvent.getTPProtocolIdentifier();
    }

    @Override
    public TPDataCodingScheme getTPDataCodingScheme() {
        return this.wrappedEvent.getTPDataCodingScheme();
    }

    @Override
    public TPValidityPeriod getTPValidityPeriod() {
        return this.wrappedEvent.getTPValidityPeriod();
    }

    @Override
    public CAPExtensions getExtensions() {
        return this.wrappedEvent.getExtensions();
    }

    @Override
    public CallReferenceNumber getSmsReferenceNumber() {
        return this.wrappedEvent.getSmsReferenceNumber();
    }

    @Override
    public ISDNAddressString getMscAddress() {
        return this.wrappedEvent.getMscAddress();
    }

    @Override
    public ISDNAddressString getSgsnNumber() {
        return this.wrappedEvent.getSgsnNumber();
    }

    @Override
    public MSClassmark2 getMSClassmark2() {
        return this.wrappedEvent.getMSClassmark2();
    }

    @Override
    public GPRSMSClass getGPRSMSClass() {
        return this.wrappedEvent.getGPRSMSClass();
    }

    @Override
    public IMEI getImei() {
        return this.wrappedEvent.getImei();
    }

    @Override
    public ISDNAddressString getCalledPartyNumber() {
        return this.wrappedEvent.getCalledPartyNumber();
    }

    @Override
    public String toString() {
        return "InitialDPSMSRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
