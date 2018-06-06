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

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.gap.GapCriteria;
import org.restcomm.protocols.ss7.cap.api.gap.GapIndicators;
import org.restcomm.protocols.ss7.cap.api.gap.GapTreatment;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.RedirectingPartyIDCap;
import org.restcomm.protocols.ss7.cap.api.primitives.AChChargingAddress;
import org.restcomm.protocols.ss7.cap.api.primitives.BCSMEvent;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.CalledPartyBCDNumber;
import org.restcomm.protocols.ss7.cap.api.primitives.EventTypeBCSM;
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CAPDialogCircuitSwitchedCall;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.BearerCapability;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAMELAChBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CGEncountered;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CallSegmentToCancel;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ContinueWithArgumentArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ControlType;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSM;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.FCIBCCCAMELsequence1;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.IPSSPCapabilities;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InitialDPArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformation;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformationType;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.SCIBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.TimeDurationChargingResult;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGIndex;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4Functionalities;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.CAPResourceAdaptor;
import org.restcomm.slee.resource.cap.wrappers.CAPDialogWrapper;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPDialogCircuitSwitchedCallWrapper extends CAPDialogWrapper<CAPDialogCircuitSwitchedCall> implements CAPDialogCircuitSwitchedCall {

	public CAPDialogCircuitSwitchedCallWrapper(CAPDialogCircuitSwitchedCall wrappedDialog, CAPDialogActivityHandle activityHandle, CAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public CAPDialogCircuitSwitchedCall getWrappedDialog() {
		return this.wrappedDialog;
	}

	@Override
	public Long addInitialDPRequest(int serviceKey, CalledPartyNumberCap calledPartyNumber, CallingPartyNumberCap callingPartyNumber,
			CallingPartysCategoryInap callingPartysCategory, CGEncountered CGEncountered, IPSSPCapabilities IPSSPCapabilities,
			LocationNumberCap locationNumber, OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions,
			HighLayerCompatibilityInap highLayerCompatibility, Digits additionalCallingPartyNumber, BearerCapability bearerCapability,
			EventTypeBCSM eventTypeBCSM, RedirectingPartyIDCap redirectingPartyID, RedirectionInformationInap redirectionInformation, CauseCap cause,
			ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Carrier carrier, CUGIndex cugIndex, CUGInterlock cugInterlock,
			boolean cugOutgoingAccess, IMSI imsi, SubscriberState subscriberState, LocationInformation locationInformation,
			ExtBasicServiceCode extBasicServiceCode, CallReferenceNumber callReferenceNumber, ISDNAddressString mscAddress,
			CalledPartyBCDNumber calledPartyBCDNumber, TimeAndTimezone timeAndTimezone, boolean callForwardingSSPending,
			InitialDPArgExtension initialDPArgExtension) throws CAPException {
		return this.wrappedDialog.addInitialDPRequest(serviceKey, calledPartyNumber, callingPartyNumber, callingPartysCategory, CGEncountered,
				IPSSPCapabilities, locationNumber, originalCalledPartyID, extensions, highLayerCompatibility, additionalCallingPartyNumber, bearerCapability,
				eventTypeBCSM, redirectingPartyID, redirectionInformation, cause, serviceInteractionIndicatorsTwo, carrier, cugIndex, cugInterlock,
				cugOutgoingAccess, imsi, subscriberState, locationInformation, extBasicServiceCode, callReferenceNumber, mscAddress, calledPartyBCDNumber,
				timeAndTimezone, callForwardingSSPending, initialDPArgExtension);
	}

	@Override
	public Long addInitialDPRequest(int customInvokeTimeout, int serviceKey, CalledPartyNumberCap calledPartyNumber, CallingPartyNumberCap callingPartyNumber,
			CallingPartysCategoryInap callingPartysCategory, CGEncountered CGEncountered, IPSSPCapabilities IPSSPCapabilities,
			LocationNumberCap locationNumber, OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions,
			HighLayerCompatibilityInap highLayerCompatibility, Digits additionalCallingPartyNumber, BearerCapability bearerCapability,
			EventTypeBCSM eventTypeBCSM, RedirectingPartyIDCap redirectingPartyID, RedirectionInformationInap redirectionInformation, CauseCap cause,
			ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Carrier carrier, CUGIndex cugIndex, CUGInterlock cugInterlock,
			boolean cugOutgoingAccess, IMSI imsi, SubscriberState subscriberState, LocationInformation locationInformation,
			ExtBasicServiceCode extBasicServiceCode, CallReferenceNumber callReferenceNumber, ISDNAddressString mscAddress,
			CalledPartyBCDNumber calledPartyBCDNumber, TimeAndTimezone timeAndTimezone, boolean callForwardingSSPending,
			InitialDPArgExtension initialDPArgExtension) throws CAPException {
		return this.wrappedDialog.addInitialDPRequest(customInvokeTimeout, serviceKey, calledPartyNumber, callingPartyNumber, callingPartysCategory,
				CGEncountered, IPSSPCapabilities, locationNumber, originalCalledPartyID, extensions, highLayerCompatibility, additionalCallingPartyNumber,
				bearerCapability, eventTypeBCSM, redirectingPartyID, redirectionInformation, cause, serviceInteractionIndicatorsTwo, carrier, cugIndex,
				cugInterlock, cugOutgoingAccess, imsi, subscriberState, locationInformation, extBasicServiceCode, callReferenceNumber, mscAddress,
				calledPartyBCDNumber, timeAndTimezone, callForwardingSSPending, initialDPArgExtension);
	}

	@Override
	public Long addApplyChargingReportRequest(TimeDurationChargingResult timeDurationChargingResult) throws CAPException {
		return this.wrappedDialog.addApplyChargingReportRequest(timeDurationChargingResult);
	}

	@Override
	public Long addApplyChargingReportRequest(int customInvokeTimeout, TimeDurationChargingResult timeDurationChargingResult) throws CAPException {
		return this.wrappedDialog.addApplyChargingReportRequest(customInvokeTimeout, timeDurationChargingResult);
	}

	@Override
	public Long addApplyChargingRequest(CAMELAChBillingChargingCharacteristics aChBillingChargingCharacteristics, SendingSideID partyToCharge,
			CAPExtensions extensions, AChChargingAddress aChChargingAddress) throws CAPException {
		return this.wrappedDialog.addApplyChargingRequest(aChBillingChargingCharacteristics, partyToCharge, extensions, aChChargingAddress);
	}

	@Override
	public Long addApplyChargingRequest(int customInvokeTimeout, CAMELAChBillingChargingCharacteristics aChBillingChargingCharacteristics,
			SendingSideID partyToCharge, CAPExtensions extensions, AChChargingAddress aChChargingAddress) throws CAPException {
		return this.wrappedDialog
				.addApplyChargingRequest(customInvokeTimeout, aChBillingChargingCharacteristics, partyToCharge, extensions, aChChargingAddress);
	}

	@Override
	public Long addCallInformationReportRequest(ArrayList<RequestedInformation> requestedInformationList, CAPExtensions extensions, ReceivingSideID legID)
			throws CAPException {
		return this.wrappedDialog.addCallInformationReportRequest(requestedInformationList, extensions, legID);
	}

	@Override
	public Long addCallInformationReportRequest(int customInvokeTimeout, ArrayList<RequestedInformation> requestedInformationList, CAPExtensions extensions,
			ReceivingSideID legID) throws CAPException {
		return this.wrappedDialog.addCallInformationReportRequest(customInvokeTimeout, requestedInformationList, extensions, legID);
	}

	@Override
	public Long addCallInformationRequestRequest(ArrayList<RequestedInformationType> requestedInformationTypeList, CAPExtensions extensions, SendingSideID legID)
			throws CAPException {
		return this.wrappedDialog.addCallInformationRequestRequest(requestedInformationTypeList, extensions, legID);
	}

	@Override
	public Long addCallInformationRequestRequest(int customInvokeTimeout, ArrayList<RequestedInformationType> requestedInformationTypeList,
			CAPExtensions extensions, SendingSideID legID) throws CAPException {
		return this.wrappedDialog.addCallInformationRequestRequest(customInvokeTimeout, requestedInformationTypeList, extensions, legID);
	}

	@Override
	public Long addConnectRequest(DestinationRoutingAddress destinationRoutingAddress, AlertingPatternCap alertingPattern,
			OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions, Carrier carrier, CallingPartysCategoryInap callingPartysCategory,
			RedirectingPartyIDCap redirectingPartyID, RedirectionInformationInap redirectionInformation, ArrayList<GenericNumberCap> genericNumbers,
			ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, LocationNumberCap chargeNumber, LegID legToBeConnected, CUGInterlock cugInterlock,
			boolean cugOutgoingAccess, boolean suppressionOfAnnouncement, boolean ocsIApplicable, NAOliInfo naoliInfo, boolean borInterrogationRequested, boolean suppressNCSI)
			throws CAPException {
		return this.wrappedDialog.addConnectRequest(destinationRoutingAddress, alertingPattern, originalCalledPartyID, extensions, carrier,
				callingPartysCategory, redirectingPartyID, redirectionInformation, genericNumbers, serviceInteractionIndicatorsTwo, chargeNumber,
				legToBeConnected, cugInterlock, cugOutgoingAccess, suppressionOfAnnouncement, ocsIApplicable, naoliInfo, borInterrogationRequested, suppressNCSI);
	}

	@Override
	public Long addConnectRequest(int customInvokeTimeout, DestinationRoutingAddress destinationRoutingAddress, AlertingPatternCap alertingPattern,
			OriginalCalledNumberCap originalCalledPartyID, CAPExtensions extensions, Carrier carrier, CallingPartysCategoryInap callingPartysCategory,
			RedirectingPartyIDCap redirectingPartyID, RedirectionInformationInap redirectionInformation, ArrayList<GenericNumberCap> genericNumbers,
			ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, LocationNumberCap chargeNumber, LegID legToBeConnected, CUGInterlock cugInterlock,
			boolean cugOutgoingAccess, boolean suppressionOfAnnouncement, boolean ocsIApplicable, NAOliInfo naoliInfo, boolean borInterrogationRequested, boolean suppressNCSI)
			throws CAPException {
		return this.wrappedDialog.addConnectRequest(customInvokeTimeout, destinationRoutingAddress, alertingPattern, originalCalledPartyID, extensions,
				carrier, callingPartysCategory, redirectingPartyID, redirectionInformation, genericNumbers, serviceInteractionIndicatorsTwo, chargeNumber,
				legToBeConnected, cugInterlock, cugOutgoingAccess, suppressionOfAnnouncement, ocsIApplicable, naoliInfo, borInterrogationRequested, suppressNCSI);
	}

	@Override
	public Long addContinueRequest() throws CAPException {
		return this.wrappedDialog.addContinueRequest();
	}

	@Override
	public Long addContinueRequest(int customInvokeTimeout) throws CAPException {
		return this.wrappedDialog.addContinueRequest(customInvokeTimeout);
	}

	@Override
	public Long addEventReportBCSMRequest(EventTypeBCSM eventTypeBCSM, EventSpecificInformationBCSM eventSpecificInformationBCSM, ReceivingSideID legID,
			MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addEventReportBCSMRequest(eventTypeBCSM, eventSpecificInformationBCSM, legID, miscCallInfo, extensions);
	}

	@Override
	public Long addEventReportBCSMRequest(int customInvokeTimeout, EventTypeBCSM eventTypeBCSM, EventSpecificInformationBCSM eventSpecificInformationBCSM,
			ReceivingSideID legID, MiscCallInfo miscCallInfo, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addEventReportBCSMRequest(customInvokeTimeout, eventTypeBCSM, eventSpecificInformationBCSM, legID, miscCallInfo, extensions);
	}

	@Override
	public Long addRequestReportBCSMEventRequest(ArrayList<BCSMEvent> bcsmEventList, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addRequestReportBCSMEventRequest(bcsmEventList, extensions);
	}

	@Override
	public Long addRequestReportBCSMEventRequest(int customInvokeTimeout, ArrayList<BCSMEvent> bcsmEventList, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addRequestReportBCSMEventRequest(customInvokeTimeout, bcsmEventList, extensions);
	}

	@Override
	public Long addReleaseCallRequest(CauseCap cause) throws CAPException {
		return this.wrappedDialog.addReleaseCallRequest(cause);
	}

	@Override
	public Long addReleaseCallRequest(int customInvokeTimeout, CauseCap cause) throws CAPException {
		return this.wrappedDialog.addReleaseCallRequest(customInvokeTimeout, cause);
	}

	@Override
	public Long addActivityTestRequest() throws CAPException {
		return this.wrappedDialog.addActivityTestRequest();
	}

	@Override
	public Long addActivityTestRequest(int customInvokeTimeout) throws CAPException {
		return this.wrappedDialog.addActivityTestRequest(customInvokeTimeout);
	}

	@Override
	public void addActivityTestResponse(long invokeId) throws CAPException {
		this.wrappedDialog.addActivityTestResponse(invokeId);
	}

	@Override
	public Long addAssistRequestInstructionsRequest(Digits correlationID, IPSSPCapabilities ipSSPCapabilities, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addAssistRequestInstructionsRequest(correlationID, ipSSPCapabilities, extensions);
	}

	@Override
	public Long addAssistRequestInstructionsRequest(int customInvokeTimeout, Digits correlationID, IPSSPCapabilities ipSSPCapabilities, CAPExtensions extensions)
			throws CAPException {
		return this.wrappedDialog.addAssistRequestInstructionsRequest(customInvokeTimeout, correlationID, ipSSPCapabilities, extensions);
	}

	@Override
	public Long addEstablishTemporaryConnectionRequest(Digits assistingSSPIPRoutingAddress, Digits correlationID, ScfID scfID, CAPExtensions extensions,
			Carrier carrier, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID, NAOliInfo naOliInfo,
			LocationNumberCap chargeNumber, OriginalCalledNumberCap originalCalledPartyID, CallingPartyNumberCap callingPartyNumber) throws CAPException {
		return this.wrappedDialog.addEstablishTemporaryConnectionRequest(assistingSSPIPRoutingAddress, correlationID, scfID, extensions, carrier,
				serviceInteractionIndicatorsTwo, callSegmentID, naOliInfo, chargeNumber, originalCalledPartyID, callingPartyNumber);
	}

	@Override
	public Long addEstablishTemporaryConnectionRequest(int customInvokeTimeout, Digits assistingSSPIPRoutingAddress, Digits correlationID, ScfID scfID,
			CAPExtensions extensions, Carrier carrier, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID,
			NAOliInfo naOliInfo, LocationNumberCap chargeNumber, OriginalCalledNumberCap originalCalledPartyID, CallingPartyNumberCap callingPartyNumber)
			throws CAPException {
		return this.wrappedDialog.addEstablishTemporaryConnectionRequest(customInvokeTimeout, assistingSSPIPRoutingAddress, correlationID, scfID, extensions,
				carrier, serviceInteractionIndicatorsTwo, callSegmentID, naOliInfo, chargeNumber, originalCalledPartyID, callingPartyNumber);
	}

	@Override
	public Long addDisconnectForwardConnectionRequest() throws CAPException {
		return this.wrappedDialog.addDisconnectForwardConnectionRequest();
	}

	@Override
	public Long addDisconnectForwardConnectionRequest(int customInvokeTimeout) throws CAPException {
		return this.wrappedDialog.addDisconnectForwardConnectionRequest(customInvokeTimeout);
	}

	@Override
	public Long addConnectToResourceRequest(CalledPartyNumberCap resourceAddress_IPRoutingAddress, boolean resourceAddress_Null, CAPExtensions extensions,
			ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID) throws CAPException {
		return this.wrappedDialog.addConnectToResourceRequest(resourceAddress_IPRoutingAddress, resourceAddress_Null, extensions,
				serviceInteractionIndicatorsTwo, callSegmentID);
	}

	@Override
	public Long addConnectToResourceRequest(int customInvokeTimeout, CalledPartyNumberCap resourceAddress_IPRoutingAddress, boolean resourceAddress_Null,
			CAPExtensions extensions, ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, Integer callSegmentID) throws CAPException {
		return this.wrappedDialog.addConnectToResourceRequest(customInvokeTimeout, resourceAddress_IPRoutingAddress, resourceAddress_Null, extensions,
				serviceInteractionIndicatorsTwo, callSegmentID);
	}

	@Override
	public Long addResetTimerRequest(TimerID timerID, int timerValue, CAPExtensions extensions, Integer callSegmentID) throws CAPException {
		return this.wrappedDialog.addResetTimerRequest(timerID, timerValue, extensions, callSegmentID);
	}

	@Override
	public Long addResetTimerRequest(int customInvokeTimeout, TimerID timerID, int timerValue, CAPExtensions extensions, Integer callSegmentID)
			throws CAPException {
		return this.wrappedDialog.addResetTimerRequest(customInvokeTimeout, timerID, timerValue, extensions, callSegmentID);
	}

	@Override
	public Long addFurnishChargingInformationRequest(FCIBCCCAMELsequence1 FCIBCCCAMELsequence1) throws CAPException {
		return this.wrappedDialog.addFurnishChargingInformationRequest(FCIBCCCAMELsequence1);
	}

	@Override
	public Long addFurnishChargingInformationRequest(int customInvokeTimeout, FCIBCCCAMELsequence1 FCIBCCCAMELsequence1) throws CAPException {
		return this.wrappedDialog.addFurnishChargingInformationRequest(customInvokeTimeout, FCIBCCCAMELsequence1);
	}

	@Override
	public Long addSendChargingInformationRequest(SCIBillingChargingCharacteristics sciBillingChargingCharacteristics, SendingSideID partyToCharge,
			CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addSendChargingInformationRequest(sciBillingChargingCharacteristics, partyToCharge, extensions);
	}

	@Override
	public Long addSendChargingInformationRequest(int customInvokeTimeout, SCIBillingChargingCharacteristics sciBillingChargingCharacteristics,
			SendingSideID partyToCharge, CAPExtensions extensions) throws CAPException {
		return this.wrappedDialog.addSendChargingInformationRequest(customInvokeTimeout, sciBillingChargingCharacteristics, partyToCharge, extensions);
	}

	@Override
	public Long addSpecializedResourceReportRequest_CapV23(Long linkedId) throws CAPException {
		return this.wrappedDialog.addSpecializedResourceReportRequest_CapV23(linkedId);
	}

	@Override
	public Long addSpecializedResourceReportRequest_CapV4(Long linkedId, boolean isAllAnnouncementsComplete, boolean isFirstAnnouncementStarted) throws CAPException {
		return this.wrappedDialog.addSpecializedResourceReportRequest_CapV4(linkedId, isAllAnnouncementsComplete, isFirstAnnouncementStarted);
	}

	@Override
	public Long addSpecializedResourceReportRequest_CapV23(Long linkedId, int customInvokeTimeout) throws CAPException {
		return this.wrappedDialog.addSpecializedResourceReportRequest_CapV23(linkedId, customInvokeTimeout);
	}

	@Override
	public Long addSpecializedResourceReportRequest_CapV4(Long linkedId, int customInvokeTimeout, boolean isAllAnnouncementsComplete, boolean isFirstAnnouncementStarted)
			throws CAPException {
		return this.wrappedDialog.addSpecializedResourceReportRequest_CapV4(linkedId, customInvokeTimeout, isAllAnnouncementsComplete, isFirstAnnouncementStarted);
	}

	@Override
	public Long addPlayAnnouncementRequest(InformationToSend informationToSend, Boolean disconnectFromIPForbidden,
			Boolean requestAnnouncementCompleteNotification, CAPExtensions extensions, Integer callSegmentID, Boolean requestAnnouncementStartedNotification)
			throws CAPException {
		return this.wrappedDialog.addPlayAnnouncementRequest(informationToSend, disconnectFromIPForbidden, requestAnnouncementCompleteNotification, extensions,
				callSegmentID, requestAnnouncementStartedNotification);
	}

	@Override
	public Long addPlayAnnouncementRequest(int customInvokeTimeout, InformationToSend informationToSend, Boolean disconnectFromIPForbidden,
			Boolean requestAnnouncementCompleteNotification, CAPExtensions extensions, Integer callSegmentID, Boolean requestAnnouncementStartedNotification)
			throws CAPException {
		return this.wrappedDialog.addPlayAnnouncementRequest(customInvokeTimeout, informationToSend, disconnectFromIPForbidden,
				requestAnnouncementCompleteNotification, extensions, callSegmentID, requestAnnouncementStartedNotification);
	}

	@Override
	public Long addPromptAndCollectUserInformationRequest(CollectedInfo collectedInfo, Boolean disconnectFromIPForbidden, InformationToSend informationToSend,
			CAPExtensions extensions, Integer callSegmentID, Boolean requestAnnouncementStartedNotification) throws CAPException {
		return this.wrappedDialog.addPromptAndCollectUserInformationRequest(collectedInfo, disconnectFromIPForbidden, informationToSend, extensions,
				callSegmentID, requestAnnouncementStartedNotification);
	}

	@Override
	public Long addPromptAndCollectUserInformationRequest(int customInvokeTimeout, CollectedInfo collectedInfo, Boolean disconnectFromIPForbidden,
			InformationToSend informationToSend, CAPExtensions extensions, Integer callSegmentID, Boolean requestAnnouncementStartedNotification)
			throws CAPException {
		return this.wrappedDialog.addPromptAndCollectUserInformationRequest(customInvokeTimeout, collectedInfo, disconnectFromIPForbidden, informationToSend,
				extensions, callSegmentID, requestAnnouncementStartedNotification);
	}

	@Override
	public void addPromptAndCollectUserInformationResponse_DigitsResponse(long invokeId, Digits digitsResponse) throws CAPException {
		this.wrappedDialog.addPromptAndCollectUserInformationResponse_DigitsResponse(invokeId, digitsResponse);
	}

	@Override
	public Long addCancelRequest_InvokeId(Integer invokeID) throws CAPException {
		return this.wrappedDialog.addCancelRequest_InvokeId(invokeID);
	}

	@Override
	public Long addCancelRequest_AllRequests() throws CAPException {
		return this.wrappedDialog.addCancelRequest_AllRequests();
	}

	@Override
	public Long addCancelRequest_CallSegmentToCancel(CallSegmentToCancel callSegmentToCancel) throws CAPException {
		return this.wrappedDialog.addCancelRequest_CallSegmentToCancel(callSegmentToCancel);
	}

	@Override
	public Long addCancelRequest_InvokeId(int customInvokeTimeout, Integer invokeID) throws CAPException {
		return this.wrappedDialog.addCancelRequest_InvokeId(customInvokeTimeout, invokeID);
	}

	@Override
	public Long addCancelRequest_AllRequests(int customInvokeTimeout) throws CAPException {
		return this.wrappedDialog.addCancelRequest_AllRequests(customInvokeTimeout);
	}

	@Override
	public Long addCancelRequest_CallSegmentToCancel(int customInvokeTimeout, CallSegmentToCancel callSegmentToCancel) throws CAPException {
		return this.wrappedDialog.addCancelRequest_CallSegmentToCancel(customInvokeTimeout, callSegmentToCancel);
	}

    @Override
    public Long addDisconnectForwardConnectionWithArgumentRequest(Integer callSegmentID, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addDisconnectForwardConnectionWithArgumentRequest(callSegmentID, extensions);
    }

    @Override
    public Long addDisconnectForwardConnectionWithArgumentRequest(int customInvokeTimeout, Integer callSegmentID, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addDisconnectForwardConnectionWithArgumentRequest(customInvokeTimeout, callSegmentID, extensions);
    }

    @Override
    public Long addDisconnectLegRequest(LegID logToBeReleased, CauseCap releaseCause, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addDisconnectLegRequest(logToBeReleased, releaseCause, extensions);
    }

    @Override
    public Long addDisconnectLegRequest(int customInvokeTimeout, LegID logToBeReleased, CauseCap releaseCause, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addDisconnectLegRequest(customInvokeTimeout, logToBeReleased, releaseCause, extensions);
    }

    @Override
    public void addDisconnectLegResponse(long invokeId) throws CAPException {
        this.wrappedDialog.addDisconnectLegResponse(invokeId);
    }

    @Override
    public Long addInitiateCallAttemptRequest(DestinationRoutingAddress destinationRoutingAddress, CAPExtensions extensions, LegID legToBeCreated,
            Integer newCallSegment, CallingPartyNumberCap callingPartyNumber, CallReferenceNumber callReferenceNumber, ISDNAddressString gsmSCFAddress,
            boolean suppressTCsi) throws CAPException {
        return this.wrappedDialog.addInitiateCallAttemptRequest(destinationRoutingAddress, extensions, legToBeCreated, newCallSegment, callingPartyNumber,
                callReferenceNumber, gsmSCFAddress, suppressTCsi);
    }

    @Override
    public Long addInitiateCallAttemptRequest(int customInvokeTimeout, DestinationRoutingAddress destinationRoutingAddress, CAPExtensions extensions,
            LegID legToBeCreated, Integer newCallSegment, CallingPartyNumberCap callingPartyNumber, CallReferenceNumber callReferenceNumber,
            ISDNAddressString gsmSCFAddress, boolean suppressTCsi) throws CAPException {
        return this.wrappedDialog.addInitiateCallAttemptRequest(customInvokeTimeout, destinationRoutingAddress, extensions, legToBeCreated, newCallSegment,
                callingPartyNumber, callReferenceNumber, gsmSCFAddress, suppressTCsi);
    }

    @Override
    public void addInitiateCallAttemptResponse(long invokeId, SupportedCamelPhases supportedCamelPhases,
            OfferedCamel4Functionalities offeredCamel4Functionalities, CAPExtensions extensions, boolean releaseCallArgExtensionAllowed) throws CAPException {
        this.wrappedDialog.addInitiateCallAttemptResponse(invokeId, supportedCamelPhases, offeredCamel4Functionalities, extensions,
                releaseCallArgExtensionAllowed);
    }

    @Override
    public Long addContinueWithArgumentRequest(AlertingPatternCap alertingPattern, CAPExtensions extensions,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, CallingPartysCategoryInap callingPartysCategory,
            ArrayList<GenericNumberCap> genericNumbers, CUGInterlock cugInterlock, boolean cugOutgoingAccess, LocationNumberCap chargeNumber, Carrier carrier,
            boolean suppressionOfAnnouncement, NAOliInfo naOliInfo, boolean borInterrogationRequested, boolean suppressOCsi,
            ContinueWithArgumentArgExtension continueWithArgumentArgExtension) throws CAPException {
        return this.wrappedDialog.addContinueWithArgumentRequest(alertingPattern, extensions, serviceInteractionIndicatorsTwo, callingPartysCategory,
                genericNumbers, cugInterlock, cugOutgoingAccess, chargeNumber, carrier, suppressionOfAnnouncement, naOliInfo, borInterrogationRequested,
                suppressOCsi, continueWithArgumentArgExtension);
    }

    @Override
    public Long addContinueWithArgumentRequest(int customInvokeTimeout, AlertingPatternCap alertingPattern, CAPExtensions extensions,
            ServiceInteractionIndicatorsTwo serviceInteractionIndicatorsTwo, CallingPartysCategoryInap callingPartysCategory,
            ArrayList<GenericNumberCap> genericNumbers, CUGInterlock cugInterlock, boolean cugOutgoingAccess, LocationNumberCap chargeNumber, Carrier carrier,
            boolean suppressionOfAnnouncement, NAOliInfo naOliInfo, boolean borInterrogationRequested, boolean suppressOCsi,
            ContinueWithArgumentArgExtension continueWithArgumentArgExtension) throws CAPException {
        return this.wrappedDialog.addContinueWithArgumentRequest(customInvokeTimeout, alertingPattern, extensions, serviceInteractionIndicatorsTwo,
                callingPartysCategory, genericNumbers, cugInterlock, cugOutgoingAccess, chargeNumber, carrier, suppressionOfAnnouncement, naOliInfo,
                borInterrogationRequested, suppressOCsi, continueWithArgumentArgExtension);
    }

    @Override
    public Long addMoveLegRequest(LegID logIDToMove, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addMoveLegRequest(logIDToMove, extensions);
    }

    @Override
    public Long addMoveLegRequest(int customInvokeTimeout, LegID logIDToMove, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addMoveLegRequest(customInvokeTimeout, logIDToMove, extensions);
    }

    @Override
    public void addMoveLegResponse(long invokeId) throws CAPException {
        this.wrappedDialog.addMoveLegResponse(invokeId);
    }

    @Override
    public Long addSplitLegRequest(LegID legIDToSplit, Integer newCallSegmentId, CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addSplitLegRequest(legIDToSplit, newCallSegmentId, extensions);
    }

    @Override
    public Long addSplitLegRequest(int customInvokeTimeout, LegID legIDToSplit, Integer newCallSegmentId,
            CAPExtensions extensions) throws CAPException {
        return this.wrappedDialog.addSplitLegRequest(customInvokeTimeout, legIDToSplit, newCallSegmentId, extensions);
    }

    @Override
    public void addSplitLegResponse(long invokeId) throws CAPException {
        this.wrappedDialog.addSplitLegResponse(invokeId);
    }

    @Override
    public Long addCollectInformationRequest() throws CAPException {
        return this.wrappedDialog.addCollectInformationRequest();
    }

    @Override
    public Long addCollectInformationRequest(int invokeId) throws CAPException {
        return this.wrappedDialog.addCollectInformationRequest(invokeId);
    }

    @Override
    public Long addCallGapRequest(GapCriteria gapCriteria, GapIndicators gapIndicators, ControlType controlType,
            GapTreatment gapTreatment, CAPExtensions capExtension) throws CAPException {
        return addCallGapRequest(gapCriteria, gapIndicators, controlType, gapTreatment, capExtension);
    }

    @Override
    public Long addCallGapRequest(int customInvokeTimeout, GapCriteria gapCriteria, GapIndicators gapIndicators,
            ControlType controlType, GapTreatment gapTreatment, CAPExtensions capExtension) throws CAPException {
        return addCallGapRequest(customInvokeTimeout, gapCriteria, gapIndicators, controlType, gapTreatment, capExtension);
    }

    @Override
    public String toString() {
        return "CAPDialogCircuitSwitchedCallWrapper [wrappedDialog=" + wrappedDialog + "]";
    }

}
