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

package org.mobicents.slee.resource.cap.service.gprs.wrappers;

import org.mobicents.protocols.ss7.cap.api.CAPException;
import org.mobicents.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.mobicents.protocols.ss7.cap.api.primitives.TimeAndTimezone;
import org.mobicents.protocols.ss7.cap.api.primitives.TimerID;
import org.mobicents.protocols.ss7.cap.api.service.gprs.CAPDialogGprs;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.CAMELFCIGPRSBillingChargingCharacteristics;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.ChargingCharacteristics;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.ChargingResult;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.ChargingRollOver;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventSpecificInformation;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.PDPInitiationType;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.QualityOfService;
import org.mobicents.protocols.ss7.cap.api.service.gprs.primitive.SGSNCapabilities;
import org.mobicents.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.mobicents.protocols.ss7.map.api.primitives.GSNAddress;
import org.mobicents.protocols.ss7.map.api.primitives.IMEI;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSChargingID;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.mobicents.slee.resource.cap.CAPDialogActivityHandle;
import org.mobicents.slee.resource.cap.CAPResourceAdaptor;
import org.mobicents.slee.resource.cap.wrappers.CAPDialogWrapper;

import java.util.ArrayList;

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
	public String toString() {
		return "CAPDialogGprsWrapper [dialogId=" + dialogId+ "]";
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
		return this.getWrappedDialog().addInitialDpGprsRequest(serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone, gprsMSClass, endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID, sgsnCapabilities, locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);
		
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
		
		return this.getWrappedDialog().addInitialDpGprsRequest(customInvokeTimeout, serviceKey, gprsEventType, msisdn, imsi, timeAndTimezone, gprsMSClass, endUserAddress, qualityOfService, accessPointName, routeingAreaIdentity, chargingID, sgsnCapabilities, locationInformationGPRS, pdpInitiationType, extensions, gsnAddress, secondaryPDPContext, imei);
	}

	@Override
	public Long addRequestReportGPRSEventRequest(
			ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addRequestReportGPRSEventRequest(gprsEvent, pdpID);
	}

	@Override
	public Long addRequestReportGPRSEventRequest(int customInvokeTimeout,
			ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addRequestReportGPRSEventRequest(customInvokeTimeout, gprsEvent, pdpID);
	}

	@Override
	public Long addApplyChargingGPRSRequest(
			ChargingCharacteristics chargingCharacteristics,
			Integer tariffSwitchInterval, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addApplyChargingGPRSRequest(chargingCharacteristics, tariffSwitchInterval, pdpID);
	}

	@Override
	public Long addApplyChargingGPRSRequest(int customInvokeTimeout,
			ChargingCharacteristics chargingCharacteristics,
			Integer tariffSwitchInterval, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addApplyChargingGPRSRequest(customInvokeTimeout, chargingCharacteristics, tariffSwitchInterval, pdpID);
	}

	@Override
	public Long addEntityReleasedGPRSRequest(GPRSCause gprsCause, PDPID pdpID)
			throws CAPException {
		
		return this.getWrappedDialog().addEntityReleasedGPRSRequest(gprsCause, pdpID);
	}

	@Override
	public Long addEntityReleasedGPRSRequest(int customInvokeTimeout,
			GPRSCause gprsCause, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addEntityReleasedGPRSRequest(customInvokeTimeout, gprsCause, pdpID);
	}

	@Override
	public void addEntityReleasedGPRSResponse(long invokeId)
			throws CAPException {
		
		this.getWrappedDialog().addEntityReleasedGPRSResponse(invokeId);
		
	}

	@Override
	public Long addConnectGPRSRequest(AccessPointName accessPointName,
			PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addConnectGPRSRequest(accessPointName, pdpID);
	}

	@Override
	public Long addConnectGPRSRequest(int customInvokeTimeout,
			AccessPointName accessPointName, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addConnectGPRSRequest(customInvokeTimeout, accessPointName, pdpID);
	}

	@Override
	public Long addContinueGPRSRequest(PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addContinueGPRSRequest(pdpID);
	}

	@Override
	public Long addContinueGPRSRequest(int customInvokeTimeout, PDPID pdpID)
			throws CAPException {
		
		return this.getWrappedDialog().addContinueGPRSRequest(customInvokeTimeout, pdpID);
	}

	@Override
	public Long addReleaseGPRSRequest(GPRSCause gprsCause, PDPID pdpID)
			throws CAPException {
		
		return this.getWrappedDialog().addReleaseGPRSRequest(gprsCause, pdpID);
	}

	@Override
	public Long addReleaseGPRSRequest(int customInvokeTimeout,
			GPRSCause gprsCause, PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addReleaseGPRSRequest(customInvokeTimeout, gprsCause, pdpID);
	}

	@Override
	public Long addResetTimerGPRSRequest(TimerID timerID, int timerValue)
			throws CAPException {
		
		return this.getWrappedDialog().addResetTimerGPRSRequest(timerID, timerValue);
	}

	@Override
	public Long addResetTimerGPRSRequest(int customInvokeTimeout,
			TimerID timerID, int timerValue) throws CAPException {
		
		return this.getWrappedDialog().addResetTimerGPRSRequest(customInvokeTimeout, timerID, timerValue);
	}

	@Override
	public Long addFurnishChargingInformationGPRSRequest(
			CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.getWrappedDialog().addFurnishChargingInformationGPRSRequest(fciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addFurnishChargingInformationGPRSRequest(
			int customInvokeTimeout,
			CAMELFCIGPRSBillingChargingCharacteristics fciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.getWrappedDialog().addFurnishChargingInformationGPRSRequest(customInvokeTimeout, fciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addCancelGPRSRequest(PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addCancelGPRSRequest(pdpID);
	}

	@Override
	public Long addCancelGPRSRequest(int customInvokeTimeout, PDPID pdpID)
			throws CAPException {
		
		return this.getWrappedDialog().addCancelGPRSRequest(customInvokeTimeout, pdpID);
	}

	@Override
	public Long addSendChargingInformationGPRSRequest(
			CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.getWrappedDialog().addSendChargingInformationGPRSRequest(sciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addSendChargingInformationGPRSRequest(
			int customInvokeTimeout,
			CAMELSCIGPRSBillingChargingCharacteristics sciGPRSBillingChargingCharacteristics)
			throws CAPException {
		
		return this.getWrappedDialog().addSendChargingInformationGPRSRequest(customInvokeTimeout, sciGPRSBillingChargingCharacteristics);
	}

	@Override
	public Long addApplyChargingReportGPRSRequest(
			ChargingResult chargingResult, QualityOfService qualityOfService,
			boolean active, PDPID pdpID, ChargingRollOver chargingRollOver)
			throws CAPException {
		
		return this.getWrappedDialog().addApplyChargingReportGPRSRequest(chargingResult, qualityOfService, active, pdpID, chargingRollOver);
	}

	@Override
	public Long addApplyChargingReportGPRSRequest(int customInvokeTimeout,
			ChargingResult chargingResult, QualityOfService qualityOfService,
			boolean active, PDPID pdpID, ChargingRollOver chargingRollOver)
			throws CAPException {
		
		return this.getWrappedDialog().addApplyChargingReportGPRSRequest(customInvokeTimeout, chargingResult, qualityOfService, active, pdpID, chargingRollOver);
	}

	@Override
	public void addApplyChargingReportGPRSResponse(long invokeId)
			throws CAPException {
		
		this.getWrappedDialog().addApplyChargingReportGPRSResponse(invokeId);
	}

	@Override
	public Long addEventReportGPRSRequest(GPRSEventType gprsEventType,
			MiscCallInfo miscGPRSInfo,
			GPRSEventSpecificInformation gprsEventSpecificInformation,
			PDPID pdpID) throws CAPException {
		
		return this.getWrappedDialog().addEventReportGPRSRequest(gprsEventType, miscGPRSInfo, gprsEventSpecificInformation, pdpID);
	}

	@Override
	public Long addEventReportGPRSRequest(int customInvokeTimeout,
			GPRSEventType gprsEventType, MiscCallInfo miscGPRSInfo,
			GPRSEventSpecificInformation gprsEventSpecificInformation,
			PDPID pdpID) throws CAPException { 
		
		return this.getWrappedDialog().addEventReportGPRSRequest(customInvokeTimeout, gprsEventType, miscGPRSInfo, gprsEventSpecificInformation, pdpID);
	}

	@Override
	public void addEventReportGPRSResponse(long invokeId) throws CAPException {
		
		this.getWrappedDialog().addEventReportGPRSResponse(invokeId);
	}

	@Override
	public Long addActivityTestGPRSRequest() throws CAPException {
		return this.getWrappedDialog().addActivityTestGPRSRequest();
	}

	@Override
	public Long addActivityTestGPRSRequest(int customInvokeTimeout)
			throws CAPException {
		return this.getWrappedDialog().addActivityTestGPRSRequest(customInvokeTimeout);
	}

	@Override
	public void addActivityTestGPRSResponse(long invokeId) throws CAPException {
		this.getWrappedDialog().addActivityTestGPRSResponse(invokeId);
	}



}
