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

import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ExtExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CUGCheckInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallDiversionTreatmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CamelInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.InterrogationType;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationRequest;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SuppressMTSS;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.ISTSupportIndicator;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingReason;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class SendRoutingInformationRequestWrapper extends CallHandlingMessageWrapper<SendRoutingInformationRequest> implements SendRoutingInformationRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.callhandling.SEND_ROUTING_INFORMATION_REQUEST";

	/**
	 * @param mAPDialog
	 */
	public SendRoutingInformationRequestWrapper(MAPDialogCallHandlingWrapper mAPDialog, SendRoutingInformationRequest req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	public CUGCheckInfo getCUGCheckInfo() {
		return this.wrappedEvent.getCUGCheckInfo();
	}

	public Integer getNumberOfForwarding() {
		return this.wrappedEvent.getNumberOfForwarding();
	}

	public InterrogationType getInterogationType() {
		return this.wrappedEvent.getInterogationType();
	}

	public boolean getORInterrogation() {
		return this.wrappedEvent.getORInterrogation();
	}

	public Integer getORCapability() {
		return this.wrappedEvent.getORCapability();
	}

	public ISDNAddressString getGmscOrGsmSCFAddress() {
		return this.wrappedEvent.getGmscOrGsmSCFAddress();
	}

	public CallReferenceNumber getCallReferenceNumber() {
		return this.wrappedEvent.getCallReferenceNumber();
	}

	public ForwardingReason getForwardingReason() {
		return this.wrappedEvent.getForwardingReason();
	}

	public ExtBasicServiceCode getBasicServiceGroup() {
		return this.wrappedEvent.getBasicServiceGroup();
	}

	public ExternalSignalInfo getNetworkSignalInfo() {
		return this.wrappedEvent.getNetworkSignalInfo();
	}

	public CamelInfo getCamelInfo() {
		return this.wrappedEvent.getCamelInfo();
	}

	public boolean getSuppressionOfAnnouncement() {
		return this.wrappedEvent.getSuppressionOfAnnouncement();
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public AlertingPattern getAlertingPattern() {
		return this.wrappedEvent.getAlertingPattern();
	}

	public boolean getCCBSCall() {
		return this.wrappedEvent.getCCBSCall();
	}

	public Integer getSupportedCCBSPhase() {
		return this.wrappedEvent.getSupportedCCBSPhase();
	}

	public ExtExternalSignalInfo getAdditionalSignalInfo() {
		return this.wrappedEvent.getAdditionalSignalInfo();
	}

	public ISTSupportIndicator getIstSupportIndicator() {
		return this.wrappedEvent.getIstSupportIndicator();
	}

	public boolean getPrePagingSupported() {
		return this.wrappedEvent.getPrePagingSupported();
	}

	public CallDiversionTreatmentIndicator getCallDiversionTreatmentIndicator() {
		return this.wrappedEvent.getCallDiversionTreatmentIndicator();
	}

	public boolean getLongFTNSupported() {
		return this.wrappedEvent.getLongFTNSupported();
	}

	public boolean getSuppressVtCSI() {
		return this.wrappedEvent.getSuppressVtCSI();
	}

	public boolean getSuppressIncomingCallBarring() {
		return this.wrappedEvent.getSuppressIncomingCallBarring();
	}

	public boolean getGsmSCFInitiatedCall() {
		return this.wrappedEvent.getGsmSCFInitiatedCall();
	}

	public ExtBasicServiceCode getBasicServiceGroup2() {
		return this.wrappedEvent.getBasicServiceGroup2();
	}

	public ExternalSignalInfo getNetworkSignalInfo2() {
		return this.wrappedEvent.getNetworkSignalInfo2();
	}

	public SuppressMTSS getSuppressMTSS() {
		return this.wrappedEvent.getSuppressMTSS();
	}

	public boolean getMTRoamingRetrySupported() {
		return this.wrappedEvent.getMTRoamingRetrySupported();
	}

	public EMLPPPriority getCallPriority() {
		return this.wrappedEvent.getCallPriority();
	}

	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "SendRoutingInformationRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}
}
