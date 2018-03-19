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
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ProvideRoamingNumberRequestWrapper extends CallHandlingMessageWrapper<ProvideRoamingNumberRequest> implements ProvideRoamingNumberRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.callhandling.PROVIDE_ROAMING_NUMBER_REQUEST";

	/**
	 * @param mapDialog
	 */
	public ProvideRoamingNumberRequestWrapper(MAPDialogCallHandlingWrapper mapDialog, ProvideRoamingNumberRequest req) {
		super(mapDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	@Override
	public ISDNAddressString getMscNumber() {
		return this.wrappedEvent.getMscNumber();
	}

	@Override
	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	@Override
	public LMSI getLmsi() {
		return this.wrappedEvent.getLmsi();
	}

	@Override
	public ExternalSignalInfo getGsmBearerCapability() {
		return this.wrappedEvent.getGsmBearerCapability();
	}

	@Override
	public ExternalSignalInfo getNetworkSignalInfo() {
		return this.wrappedEvent.getNetworkSignalInfo();
	}

	@Override
	public boolean getSuppressionOfAnnouncement() {
		return this.wrappedEvent.getSuppressionOfAnnouncement();
	}

	@Override
	public ISDNAddressString getGmscAddress() {
		return this.wrappedEvent.getGmscAddress();
	}

	@Override
	public CallReferenceNumber getCallReferenceNumber() {
		return this.wrappedEvent.getCallReferenceNumber();
	}

	@Override
	public boolean getOrInterrogation() {
		return this.wrappedEvent.getOrInterrogation();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public AlertingPattern getAlertingPattern() {
		return this.wrappedEvent.getAlertingPattern();
	}

	@Override
	public boolean getCcbsCall() {
		return this.wrappedEvent.getCcbsCall();
	}

	@Override
	public SupportedCamelPhases getSupportedCamelPhasesInInterrogatingNode() {
		return this.wrappedEvent.getSupportedCamelPhasesInInterrogatingNode();
	}

	@Override
	public ExtExternalSignalInfo getAdditionalSignalInfo() {
		return this.wrappedEvent.getAdditionalSignalInfo();
	}

	@Override
	public boolean getOrNotSupportedInGMSC() {
		return this.wrappedEvent.getOrNotSupportedInGMSC();
	}

	@Override
	public boolean getPrePagingSupported() {
		return this.wrappedEvent.getPrePagingSupported();
	}

	@Override
	public boolean getLongFTNSupported() {
		return this.wrappedEvent.getLongFTNSupported();
	}

	@Override
	public boolean getSuppressVtCsi() {
		return this.wrappedEvent.getSuppressVtCsi();
	}

	@Override
	public OfferedCamel4CSIs getOfferedCamel4CSIsInInterrogatingNode() {
		return this.wrappedEvent.getOfferedCamel4CSIsInInterrogatingNode();
	}

	@Override
	public boolean getMtRoamingRetrySupported() {
		return this.wrappedEvent.getMtRoamingRetrySupported();
	}

	@Override
	public PagingArea getPagingArea() {
		return this.wrappedEvent.getPagingArea();
	}

	@Override
	public EMLPPPriority getCallPriority() {
		return this.wrappedEvent.getCallPriority();
	}

	@Override
	public boolean getMtrfIndicator() {
		return this.wrappedEvent.getMtrfIndicator();
	}

	@Override
	public ISDNAddressString getOldMSCNumber() {
		return this.wrappedEvent.getOldMSCNumber();
	}

	@Override
	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "ProvideRoamingNumberRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
