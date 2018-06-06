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

package org.restcomm.slee.resource.cap.service.gprs.wrappers;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPDialogGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELFCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingResult;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPInitiationType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.SGSNCapabilities;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.slee.resource.cap.CAPDialogActivityHandle;
import org.restcomm.slee.resource.cap.CAPResourceAdaptor;
import org.restcomm.slee.resource.cap.wrappers.CAPDialogWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPDialogGprsWrapper extends CAPDialogWrapper<CAPDialogGprs> implements CAPDialogGprs {

	public CAPDialogGprsWrapper(CAPDialogGprs wrappedDialog, CAPDialogActivityHandle activityHandle, CAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public CAPDialogGprs getWrappedDialog() {
		return this.wrappedDialog;
	}

	@Override
	public String toString() {
		return "CAPDialogGprsWrapper [wrappedDialog=" + wrappedDialog + "]";
	}

	@Override
	public Long addInitialDpGprsRequest(int serviceKey,
			GPRSEventType gprsEventType, ISDNAddressString msisdn, IMSI imsi,
			TimeAndTimezone timeAndTimezone, GPRSMSClass gprsMSClass,
			EndUserAddress endUserAddress, QualityOfService qualityOfService,
			AccessPointName accessPointName, RAIdentity routeingAreaIdentity,
			GPRSChargingID chargingID, SGSNCapabilities sgsnCapabilities,
			LocationInformationGPRS locationInformationGPRS,
			PDPInitiationType pdpInitiationType, CAPExtensions extensions,
			GSNAddress gsnAddress, boolean secondaryPDPContext, IMEI imei)
			throws CAPException {
		return this.wrappedDialog.addInitialDpGprsRequest(serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone, gprsMSClass, endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID, sgsnCapabilities, locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);
		
	}

	@Override
	public Long addInitialDpGprsRequest(int customInvokeTimeout,
			int serviceKey, GPRSEventType gprsEventType,
			ISDNAddressString msisdn, IMSI imsi,
			TimeAndTimezone timeAndTimezone, GPRSMSClass gprsMSClass,
			EndUserAddress endUserAddress, QualityOfService qualityOfService,
			AccessPointName accessPointName, RAIdentity routeingAreaIdentity,
			GPRSChargingID chargingID, SGSNCapabilities sgsnCapabilities,
			LocationInformationGPRS locationInformationGPRS,
			PDPInitiationType pdpInitiationType, CAPExtensions extensions,
			GSNAddress gsnAddress, boolean secondaryPDPContext, IMEI imei)
			throws CAPException {
		
		return this.wrappedDialog.addInitialDpGprsRequest(customInvokeTimeout, serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone, gprsMSClass, endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID, sgsnCapabilities, locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);
	}

	@Override
	public Long addRequestReportGPRSEventRequest(
			ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addRequestReportGPRSEventRequest(gprsEvent, pdpID);
	}

	@Override
	public Long addRequestReportGPRSEventRequest(int customInvokeTimeout,
			ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addRequestReportGPRSEventRequest(customInvokeTimeout, gprsEvent, pdpID);
	}

	@Override
	public Long addApplyChargingGPRSRequest(
			ChargingCharacteristics chargingCharacteristics,
			Integer tariffSwitchInterval, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addApplyChargingGPRSRequest(chargingCharacteristics, tariffSwitchInterval, pdpID);
	}

	@Override
	public Long addApplyChargingGPRSRequest(int customInvokeTimeout,
			ChargingCharacteristics chargingCharacteristics,
			Integer tariffSwitchInterval, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addApplyChargingGPRSRequest(customInvokeTimeout, chargingCharacteristics, tariffSwitchInterval, pdpID);
	}

	@Override
	public Long addEntityReleasedGPRSRequest(GPRSCause gprsCause, PDPID pdpID)
			throws CAPException {
		
		return this.wrappedDialog.addEntityReleasedGPRSRequest(gprsCause, pdpID);
	}

	@Override
	public Long addEntityReleasedGPRSRequest(int customInvokeTimeout,
			GPRSCause gprsCause, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addEntityReleasedGPRSRequest(customInvokeTimeout, gprsCause, pdpID);
	}

	@Override
	public void addEntityReleasedGPRSResponse(long invokeId)
			throws CAPException {
		
		this.wrappedDialog.addEntityReleasedGPRSResponse(invokeId);
		
	}

	@Override
	public Long addConnectGPRSRequest(AccessPointName accessPointName,
			PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addConnectGPRSRequest(accessPointName, pdpID);
	}

	@Override
	public Long addConnectGPRSRequest(int customInvokeTimeout,
			AccessPointName accessPointName, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addConnectGPRSRequest(customInvokeTimeout, accessPointName, pdpID);
	}

	@Override
	public Long addContinueGPRSRequest(PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addContinueGPRSRequest(pdpID);
	}

	@Override
	public Long addContinueGPRSRequest(int customInvokeTimeout, PDPID pdpID)
			throws CAPException {
		
		return this.wrappedDialog.addContinueGPRSRequest(customInvokeTimeout, pdpID);
	}

	@Override
	public Long addReleaseGPRSRequest(GPRSCause gprsCause, PDPID pdpID)
			throws CAPException {
		
		return this.wrappedDialog.addReleaseGPRSRequest(gprsCause, pdpID);
	}

	@Override
	public Long addReleaseGPRSRequest(int customInvokeTimeout,
			GPRSCause gprsCause, PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addReleaseGPRSRequest(customInvokeTimeout, gprsCause, pdpID);
	}

	@Override
	public Long addResetTimerGPRSRequest(TimerID timerID, int timerValue)
			throws CAPException {
		
		return this.wrappedDialog.addResetTimerGPRSRequest(timerID, timerValue);
	}

	@Override
	public Long addResetTimerGPRSRequest(int customInvokeTimeout,
			TimerID timerID, int timerValue) throws CAPException {
		
		return this.wrappedDialog.addResetTimerGPRSRequest(customInvokeTimeout, timerID, timerValue);
	}

	@Override
	public Long addFurnishChargingInformationGPRSRequest(
			CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.wrappedDialog.addFurnishChargingInformationGPRSRequest(fciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addFurnishChargingInformationGPRSRequest(
			int customInvokeTimeout,
			CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.wrappedDialog.addFurnishChargingInformationGPRSRequest(customInvokeTimeout, fciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addCancelGPRSRequest(PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addCancelGPRSRequest(pdpID);
	}

	@Override
	public Long addCancelGPRSRequest(int customInvokeTimeout, PDPID pdpID)
			throws CAPException {
		
		return this.wrappedDialog.addCancelGPRSRequest(customInvokeTimeout, pdpID);
	}

	@Override
	public Long addSendChargingInformationGPRSRequest(
			CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.wrappedDialog.addSendChargingInformationGPRSRequest(sciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addSendChargingInformationGPRSRequest(
			int customInvokeTimeout,
			CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.wrappedDialog.addSendChargingInformationGPRSRequest(customInvokeTimeout, sciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addApplyChargingReportGPRSRequest(
			ChargingResult chargingResult, QualityOfService qualityOfService,
			boolean active, PDPID pdpID, ChargingRollOver chargingRollOver)
			throws CAPException {
		
		return this.wrappedDialog.addApplyChargingReportGPRSRequest(chargingResult, qualityOfService, active, pdpID, chargingRollOver);
	}

	@Override
	public Long addApplyChargingReportGPRSRequest(int customInvokeTimeout,
			ChargingResult chargingResult, QualityOfService qualityOfService,
			boolean active, PDPID pdpID, ChargingRollOver chargingRollOver)
			throws CAPException {
		
		return this.wrappedDialog.addApplyChargingReportGPRSRequest(customInvokeTimeout, chargingResult, qualityOfService, active, pdpID, chargingRollOver);
	}

	@Override
	public void addApplyChargingReportGPRSResponse(long invokeId)
			throws CAPException {
		
		this.wrappedDialog.addApplyChargingReportGPRSResponse(invokeId);
	}

	@Override
	public Long addEventReportGPRSRequest(GPRSEventType gprsEventType,
			MiscCallInfo miscGPRSInfo,
			GPRSEventSpecificInformation gprsEventSpecificInformation,
			PDPID pdpID) throws CAPException {
		
		return this.wrappedDialog.addEventReportGPRSRequest(gprsEventType, miscGPRSInfo, gprsEventSpecificInformation, pdpID);
	}

	@Override
	public Long addEventReportGPRSRequest(int customInvokeTimeout,
			GPRSEventType gprsEventType, MiscCallInfo miscGPRSInfo,
			GPRSEventSpecificInformation gprsEventSpecificInformation,
			PDPID pdpID) throws CAPException { 
		
		return this.wrappedDialog.addEventReportGPRSRequest(customInvokeTimeout, gprsEventType, miscGPRSInfo, gprsEventSpecificInformation, pdpID);
	}

	@Override
	public void addEventReportGPRSResponse(long invokeId) throws CAPException {
		
		this.wrappedDialog.addEventReportGPRSResponse(invokeId);
	}

	@Override
	public Long addActivityTestGPRSRequest() throws CAPException {
		return this.wrappedDialog.addActivityTestGPRSRequest();
	}

	@Override
	public Long addActivityTestGPRSRequest(int customInvokeTimeout)
			throws CAPException {
		return this.wrappedDialog.addActivityTestGPRSRequest(customInvokeTimeout);
	}

	@Override
	public void addActivityTestGPRSResponse(long invokeId) throws CAPException {
		this.wrappedDialog.addActivityTestGPRSResponse(invokeId);
	}

}
