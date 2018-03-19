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

package org.restcomm.slee.resource.map.service.callhandling.wrappers;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.service.callhandling.AllowedServices;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CCBSIndicators;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CUGCheckInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.RoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationResponse;
import org.restcomm.protocols.ss7.map.api.service.callhandling.UnavailabilityCause;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class SendRoutingInformationResponseWrapper extends CallHandlingMessageWrapper<SendRoutingInformationResponse> implements SendRoutingInformationResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.callhandling.SEND_ROUTING_INFORMATION_RESPONSE";

	/**
	 * @param mAPDialog
	 */
	public SendRoutingInformationResponseWrapper(MAPDialogCallHandlingWrapper mAPDialog, SendRoutingInformationResponse req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public IMSI getIMSI() {
		return this.wrappedEvent.getIMSI();
	}

	public ExtendedRoutingInfo getExtendedRoutingInfo() {
		return this.wrappedEvent.getExtendedRoutingInfo();
	}

	public CUGCheckInfo getCUGCheckInfo() {
		return this.wrappedEvent.getCUGCheckInfo();
	}

	public boolean getCUGSubscriptionFlag() {
		return this.wrappedEvent.getCUGSubscriptionFlag();
	}

	public SubscriberInfo getSubscriberInfo() {
		return this.wrappedEvent.getSubscriberInfo();
	}

	public ArrayList<SSCode> getSSList() {
		return this.wrappedEvent.getSSList();
	}

	public ExtBasicServiceCode getBasicService() {
		return this.wrappedEvent.getBasicService();
	}

	public boolean getForwardingInterrogationRequired() {
		return this.wrappedEvent.getForwardingInterrogationRequired();
	}

	public ISDNAddressString getVmscAddress() {
		return this.wrappedEvent.getVmscAddress();
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public NAEAPreferredCI getNaeaPreferredCI() {
		return this.wrappedEvent.getNaeaPreferredCI();
	}

	public CCBSIndicators getCCBSIndicators() {
		return this.wrappedEvent.getCCBSIndicators();
	}

	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	public NumberPortabilityStatus getNumberPortabilityStatus() {
		return this.wrappedEvent.getNumberPortabilityStatus();
	}

	public Integer getISTAlertTimer() {
		return this.wrappedEvent.getISTAlertTimer();
	}

	public SupportedCamelPhases getSupportedCamelPhasesInVMSC() {
		return this.wrappedEvent.getSupportedCamelPhasesInVMSC();
	}

	public OfferedCamel4CSIs getOfferedCamel4CSIsInVMSC() {
		return this.wrappedEvent.getOfferedCamel4CSIsInVMSC();
	}

	public RoutingInfo getRoutingInfo2() {
		return this.wrappedEvent.getRoutingInfo2();
	}

	public ArrayList<SSCode> getSSList2() {
		return this.wrappedEvent.getSSList2();
	}

	public ExtBasicServiceCode getBasicService2() {
		return this.wrappedEvent.getBasicService2();
	}

	public AllowedServices getAllowedServices() {
		return this.wrappedEvent.getAllowedServices();
	}

	public UnavailabilityCause getUnavailabilityCause() {
		return this.wrappedEvent.getUnavailabilityCause();
	}

	public boolean getReleaseResourcesSupported() {
		return this.wrappedEvent.getReleaseResourcesSupported();
	}

	public ExternalSignalInfo getGsmBearerCapability() {
		return this.wrappedEvent.getGsmBearerCapability();
	}

	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "SendRoutingInformationResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}
}
