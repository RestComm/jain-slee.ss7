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

package org.restcomm.slee.resource.map.service.sms.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertReason;
import org.restcomm.protocols.ss7.map.api.service.sms.CorrelationID;
import org.restcomm.protocols.ss7.map.api.service.sms.IpSmGwGuidance;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MWStatus;
import org.restcomm.protocols.ss7.map.api.service.sms.SMDeliveryNotIntended;
import org.restcomm.protocols.ss7.map.api.service.sms.SMDeliveryOutcome;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_MTI;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_SMEA;
import org.restcomm.protocols.ss7.map.api.service.sms.SmsSignalInfo;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.MAPResourceAdaptor;
import org.restcomm.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class MAPDialogSmsWrapper extends MAPDialogWrapper<MAPDialogSms> implements MAPDialogSms {

	public MAPDialogSmsWrapper(MAPDialogSms wrappedDialog, MAPDialogActivityHandle activityHandle, MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

    @Override
    public MAPDialogSms getWrappedDialog() {
        return this.wrappedDialog;
    }

    @Override
    public String toString() {
        return "MAPDialogSmsWrapper [wrappedDialog=" + wrappedDialog + "]";
    }


    public Long addAlertServiceCentreRequest(ISDNAddressString arg0, AddressString arg1) throws MAPException {
		return this.wrappedDialog.addAlertServiceCentreRequest(arg0, arg1);
	}

	public Long addAlertServiceCentreRequest(int arg0, ISDNAddressString arg1, AddressString arg2) throws MAPException {
		return this.wrappedDialog.addAlertServiceCentreRequest(arg0, arg1, arg2);
	}

	public void addAlertServiceCentreResponse(long arg0) throws MAPException {
		this.wrappedDialog.addAlertServiceCentreResponse(arg0);
	}

	public Long addForwardShortMessageRequest(SM_RP_DA arg0, SM_RP_OA arg1, SmsSignalInfo arg2, boolean arg3)
			throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addForwardShortMessageRequest(arg0, arg1, arg2, arg3);
	}

	public Long addForwardShortMessageRequest(int arg0, SM_RP_DA arg1, SM_RP_OA arg2, SmsSignalInfo arg3, boolean arg4)
			throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addForwardShortMessageRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addForwardShortMessageResponse(long arg0) throws MAPException {
		this.wrappedDialog.addForwardShortMessageResponse(arg0);
	}

	public Long addInformServiceCentreRequest(ISDNAddressString arg0, MWStatus arg1, MAPExtensionContainer arg2,
			Integer arg3, Integer arg4) throws MAPException {
		return this.wrappedDialog.addInformServiceCentreRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public Long addInformServiceCentreRequest(int arg0, ISDNAddressString arg1, MWStatus arg2,
			MAPExtensionContainer arg3, Integer arg4, Integer arg5) throws MAPException {
		return this.wrappedDialog.addInformServiceCentreRequest(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public Long addMoForwardShortMessageRequest(SM_RP_DA arg0, SM_RP_OA arg1, SmsSignalInfo arg2,
			MAPExtensionContainer arg3, IMSI arg4) throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addMoForwardShortMessageRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public Long addMoForwardShortMessageRequest(int arg0, SM_RP_DA arg1, SM_RP_OA arg2, SmsSignalInfo arg3,
			MAPExtensionContainer arg4, IMSI arg5) throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addMoForwardShortMessageRequest(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public void addMoForwardShortMessageResponse(long arg0, SmsSignalInfo arg1, MAPExtensionContainer arg2)
			throws MAPException {
		this.wrappedDialog.addMoForwardShortMessageResponse(arg0, arg1, arg2);
	}

	public Long addMtForwardShortMessageRequest(SM_RP_DA arg0, SM_RP_OA arg1, SmsSignalInfo arg2, boolean arg3,
			MAPExtensionContainer arg4) throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addMtForwardShortMessageRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public Long addMtForwardShortMessageRequest(int arg0, SM_RP_DA arg1, SM_RP_OA arg2, SmsSignalInfo arg3,
			boolean arg4, MAPExtensionContainer arg5) throws MAPException {
		this.ra.getDefaultUsageParameters().incrementMessages(1L);
		return this.wrappedDialog.addMtForwardShortMessageRequest(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public void addMtForwardShortMessageResponse(long arg0, SmsSignalInfo arg1, MAPExtensionContainer arg2)
			throws MAPException {
		this.wrappedDialog.addMtForwardShortMessageResponse(arg0, arg1, arg2);
	}

	public Long addReportSMDeliveryStatusRequest(ISDNAddressString arg0, AddressString arg1, SMDeliveryOutcome arg2,
			Integer arg3, MAPExtensionContainer arg4, boolean arg5, boolean arg6, SMDeliveryOutcome arg7, Integer arg8)
			throws MAPException {
		return this.wrappedDialog
				.addReportSMDeliveryStatusRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	public Long addReportSMDeliveryStatusRequest(int arg0, ISDNAddressString arg1, AddressString arg2,
			SMDeliveryOutcome arg3, Integer arg4, MAPExtensionContainer arg5, boolean arg6, boolean arg7,
			SMDeliveryOutcome arg8, Integer arg9) throws MAPException {
		return this.wrappedDialog.addReportSMDeliveryStatusRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7,
				arg8, arg9);
	}

	public void addReportSMDeliveryStatusResponse(long arg0, ISDNAddressString arg1, MAPExtensionContainer arg2)
			throws MAPException {
		this.wrappedDialog.addReportSMDeliveryStatusResponse(arg0, arg1, arg2);
	}

    @Override
    public Long addSendRoutingInfoForSMRequest(ISDNAddressString msisdn, boolean sm_RP_PRI, AddressString serviceCentreAddress,
            MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator, SM_RP_MTI sM_RP_MTI, SM_RP_SMEA sM_RP_SMEA,
            SMDeliveryNotIntended smDeliveryNotIntended, boolean ipSmGwGuidanceIndicator, IMSI imsi,
            boolean t4TriggerIndicator, boolean singleAttemptDelivery, TeleserviceCode teleservice, CorrelationID correlationID)
            throws MAPException {
        return this.wrappedDialog.addSendRoutingInfoForSMRequest(msisdn, sm_RP_PRI, serviceCentreAddress, extensionContainer,
                gprsSupportIndicator, sM_RP_MTI, sM_RP_SMEA, smDeliveryNotIntended, ipSmGwGuidanceIndicator, imsi,
                t4TriggerIndicator, singleAttemptDelivery, teleservice, correlationID);
    }

    @Override
    public Long addSendRoutingInfoForSMRequest(int customInvokeTimeout, ISDNAddressString msisdn, boolean sm_RP_PRI,
            AddressString serviceCentreAddress, MAPExtensionContainer extensionContainer, boolean gprsSupportIndicator,
            SM_RP_MTI sM_RP_MTI, SM_RP_SMEA sM_RP_SMEA, SMDeliveryNotIntended smDeliveryNotIntended,
            boolean ipSmGwGuidanceIndicator, IMSI imsi, boolean t4TriggerIndicator, boolean singleAttemptDelivery,
            TeleserviceCode teleservice, CorrelationID correlationID) throws MAPException {
        return this.wrappedDialog.addSendRoutingInfoForSMRequest(customInvokeTimeout, msisdn, sm_RP_PRI, serviceCentreAddress,
                extensionContainer, gprsSupportIndicator, sM_RP_MTI, sM_RP_SMEA, smDeliveryNotIntended,
                ipSmGwGuidanceIndicator, imsi, t4TriggerIndicator, singleAttemptDelivery, teleservice, correlationID);
    }

    @Override
    public void addSendRoutingInfoForSMResponse(long invokeId, IMSI imsi, LocationInfoWithLMSI locationInfoWithLMSI,
            MAPExtensionContainer extensionContainer, Boolean mwdSet, IpSmGwGuidance ipSmGwGuidance) throws MAPException {
        this.wrappedDialog.addSendRoutingInfoForSMResponse(invokeId, imsi, locationInfoWithLMSI, extensionContainer, mwdSet,
                ipSmGwGuidance);
    }

    @Override
    public Long addReadyForSMRequest(IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator, MAPExtensionContainer extensionContainer,
            boolean additionalAlertReasonIndicator) throws MAPException {
        return this.wrappedDialog.addReadyForSMRequest(imsi, alertReason, alertReasonIndicator, extensionContainer, additionalAlertReasonIndicator);
    }

    @Override
    public Long addReadyForSMRequest(int customInvokeTimeout, IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator,
            MAPExtensionContainer extensionContainer, boolean additionalAlertReasonIndicator) throws MAPException {
        return this.wrappedDialog.addReadyForSMRequest(customInvokeTimeout, imsi, alertReason, alertReasonIndicator, extensionContainer,
                additionalAlertReasonIndicator);
    }

    @Override
    public void addReadyForSMResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException {
        this.wrappedDialog.addReadyForSMResponse(invokeId, extensionContainer);
    }

    @Override
    public Long addNoteSubscriberPresentRequest(IMSI imsi) throws MAPException {
        return this.wrappedDialog.addNoteSubscriberPresentRequest(imsi);
    }

    @Override
    public Long addNoteSubscriberPresentRequest(int customInvokeTimeout, IMSI imsi) throws MAPException {
        return this.wrappedDialog.addNoteSubscriberPresentRequest(customInvokeTimeout, imsi);
    }

    @Override
    public Boolean isDoNotSendProtcolVersion() {
        return this.wrappedDialog.isDoNotSendProtcolVersion();
    }

    @Override
    public void setDoNotSendProtocolVersion(Boolean isSendProtocolVersion) {
        this.wrappedDialog.setDoNotSendProtocolVersion(isSendProtocolVersion);
    }

}
