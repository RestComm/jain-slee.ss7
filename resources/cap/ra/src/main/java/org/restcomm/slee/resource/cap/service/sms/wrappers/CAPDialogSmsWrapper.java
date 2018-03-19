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

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventSpecificInformationSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.EventTypeSMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.FCIBCCCAMELsequence1SMS;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.RPCause;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSAddressString;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSEvent;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPDataCodingScheme;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPProtocolIdentifier;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPShortMessageSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPValidityPeriod;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.CAPResourceAdaptor;
import org.restcomm.slee.resource.cap.wrappers.CAPDialogWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPDialogSmsWrapper extends CAPDialogWrapper<CAPDialogSms> implements CAPDialogSms {

	public CAPDialogSmsWrapper(CAPDialogSms wrappedDialog, CAPDialogActivityHandle activityHandle, CAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public CAPDialogSms getWrappedDialog() {
		return this.wrappedDialog;
	}

    @Override
    public Long addConnectSMSRequest(SMSAddressString callingPartysNumber, CalledPartyBCDNumber destinationSubscriberNumber, ISDNAddressString smscAddress,
            CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addConnectSMSRequest(callingPartysNumber, destinationSubscriberNumber, smscAddress, extensions);
    }

    @Override
    public Long addConnectSMSRequest(int customInvokeTimeout, SMSAddressString callingPartysNumber, CalledPartyBCDNumber destinationSubscriberNumber,
            ISDNAddressString smscAddress, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addConnectSMSRequest(customInvokeTimeout, callingPartysNumber, destinationSubscriberNumber, smscAddress, extensions);
    }

    @Override
    public Long addEventReportSMSRequest(EventTypeSMS eventTypeSMS, EventSpecificInformationSMS eventSpecificInformationSMS, MiscCallInfo miscCallInfo,
            CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addEventReportSMSRequest(eventTypeSMS, eventSpecificInformationSMS, miscCallInfo, extensions);
    }

    @Override
    public Long addEventReportSMSRequest(int customInvokeTimeout, EventTypeSMS eventTypeSMS, EventSpecificInformationSMS eventSpecificInformationSMS,
            MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addEventReportSMSRequest(customInvokeTimeout, eventTypeSMS, eventSpecificInformationSMS, miscCallInfo, extensions);
    }

    @Override
    public Long addFurnishChargingInformationSMSRequest(FCIBCCCAMELsequence1SMS fciBCCCAMELsequence1) throws CAPException {
        return this.wrappedDialog.addFurnishChargingInformationSMSRequest(fciBCCCAMELsequence1);
    }

    @Override
    public Long addFurnishChargingInformationSMSRequest(int customInvokeTimeout, FCIBCCCAMELsequence1SMS fciBCCCAMELsequence1) throws CAPException {
        return this.wrappedDialog.addFurnishChargingInformationSMSRequest(customInvokeTimeout, fciBCCCAMELsequence1);
    }

    @Override
    public Long addInitialDPSMSRequest(int serviceKey, CalledPartyBCDNumber destinationSubscriberNumber, SMSAddressString callingPartyNumber,
            EventTypeSMS eventTypeSMS, IMSI imsi, LocationInformation locationInformationMSC, LocationInformationGPRS locationInformationGPRS,
            ISDNAddressString smscCAddress, TimeAndTimezone timeAndTimezone, TPShortMessageSpecificInfo tPShortMessageSpecificInfo,
            TPProtocolIdentifier tPProtocolIdentifier, TPDataCodingScheme tPDataCodingScheme, TPValidityPeriod tPValidityPeriod, CAPExtensions extensions,
            CallReferenceNumber smsReferenceNumber, ISDNAddressString mscAddress, ISDNAddressString sgsnNumber, MSClassmark2 mSClassmark2,
            GPRSMSClass gprsMSClass, IMEI imei, ISDNAddressString calledPartyNumber) throws CAPException {
        return this.wrappedDialog.addInitialDPSMSRequest(serviceKey, destinationSubscriberNumber, callingPartyNumber, eventTypeSMS, imsi,
                locationInformationMSC, locationInformationGPRS, smscCAddress, timeAndTimezone, tPShortMessageSpecificInfo, tPProtocolIdentifier,
                tPDataCodingScheme, tPValidityPeriod, extensions, smsReferenceNumber, mscAddress, sgsnNumber, mSClassmark2, gprsMSClass, imei,
                calledPartyNumber);
    }

    @Override
    public Long addInitialDPSMSRequest(int customInvokeTimeout, int serviceKey, CalledPartyBCDNumber destinationSubscriberNumber,
            SMSAddressString callingPartyNumber, EventTypeSMS eventTypeSMS, IMSI imsi, LocationInformation locationInformationMSC,
            LocationInformationGPRS locationInformationGPRS, ISDNAddressString smscCAddress, TimeAndTimezone timeAndTimezone,
            TPShortMessageSpecificInfo tPShortMessageSpecificInfo, TPProtocolIdentifier tPProtocolIdentifier, TPDataCodingScheme tPDataCodingScheme,
            TPValidityPeriod tPValidityPeriod, CAPExtensions extensions, CallReferenceNumber smsReferenceNumber, ISDNAddressString mscAddress,
            ISDNAddressString sgsnNumber, MSClassmark2 mSClassmark2, GPRSMSClass gprsMSClass, IMEI imei, ISDNAddressString calledPartyNumber)
            throws CAPException {
        return this.wrappedDialog.addInitialDPSMSRequest(customInvokeTimeout, serviceKey, destinationSubscriberNumber, callingPartyNumber, eventTypeSMS, imsi,
                locationInformationMSC, locationInformationGPRS, smscCAddress, timeAndTimezone, tPShortMessageSpecificInfo, tPProtocolIdentifier,
                tPDataCodingScheme, tPValidityPeriod, extensions, smsReferenceNumber, mscAddress, sgsnNumber, mSClassmark2, gprsMSClass, imei,
                calledPartyNumber);
    }

    @Override
    public Long addReleaseSMSRequest(RPCause rpCause) throws CAPException {
        return this.wrappedDialog.addReleaseSMSRequest(rpCause);
    }

    @Override
    public Long addReleaseSMSRequest(int customInvokeTimeout, RPCause rpCause) throws CAPException {
        return this.wrappedDialog.addReleaseSMSRequest(customInvokeTimeout, rpCause);
    }

    @Override
    public Long addRequestReportSMSEventRequest(ArrayList<SMSEvent> smsEvents, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addRequestReportSMSEventRequest(smsEvents, extensions);
    }

    @Override
    public Long addRequestReportSMSEventRequest(int customInvokeTimeout, ArrayList<SMSEvent> smsEvents, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addRequestReportSMSEventRequest(customInvokeTimeout, smsEvents, extensions);
    }

    @Override
    public Long addResetTimerSMSRequest(TimerID timerID, int timerValue, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addResetTimerSMSRequest(timerID, timerValue, extensions);
    }

    @Override
    public Long addResetTimerSMSRequest(int customInvokeTimeout, TimerID timerID, int timerValue, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addResetTimerSMSRequest(customInvokeTimeout, timerID, timerValue, extensions);
    }

    @Override
    public Long addContinueSMSRequest() throws CAPException {
        return this.wrappedDialog.addContinueSMSRequest();
    }

    @Override
    public Long addContinueSMSRequest(int customInvokeTimeout) throws CAPException {
        return this.wrappedDialog.addContinueSMSRequest(customInvokeTimeout);
    }

    @Override
    public String toString() {
        return "CAPDialogSmsWrapper [wrappedDialog=" + wrappedDialog + "]";
    }

}
