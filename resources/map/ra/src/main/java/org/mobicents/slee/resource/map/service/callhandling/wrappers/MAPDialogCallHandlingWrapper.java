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

package org.mobicents.slee.resource.map.service.callhandling.wrappers;

import java.util.ArrayList;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.primitives.AlertingPattern;
import org.mobicents.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.mobicents.protocols.ss7.map.api.primitives.ExtExternalSignalInfo;
import org.mobicents.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.LMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.mobicents.protocols.ss7.map.api.service.callhandling.AllowedServices;
import org.mobicents.protocols.ss7.map.api.service.callhandling.CCBSIndicators;
import org.mobicents.protocols.ss7.map.api.service.callhandling.CUGCheckInfo;
import org.mobicents.protocols.ss7.map.api.service.callhandling.CallDiversionTreatmentIndicator;
import org.mobicents.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;
import org.mobicents.protocols.ss7.map.api.service.callhandling.CamelInfo;
import org.mobicents.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.mobicents.protocols.ss7.map.api.service.callhandling.InterrogationType;
import org.mobicents.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.mobicents.protocols.ss7.map.api.service.callhandling.RoutingInfo;
import org.mobicents.protocols.ss7.map.api.service.callhandling.SuppressMTSS;
import org.mobicents.protocols.ss7.map.api.service.callhandling.UnavailabilityCause;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.ISTSupportIndicator;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ForwardingReason;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSCode;
import org.mobicents.slee.resource.map.MAPDialogActivityHandle;
import org.mobicents.slee.resource.map.MAPResourceAdaptor;
import org.mobicents.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MAPDialogCallHandlingWrapper extends MAPDialogWrapper<MAPDialogCallHandling> implements MAPDialogCallHandling {

	public MAPDialogCallHandlingWrapper(MAPDialogCallHandling wrappedDialog, MAPDialogActivityHandle activityHandle,
			MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public MAPDialogCallHandling getWrappedDialog() {
		return this.wrappedDialog;
	}

	@Override
	public Long addProvideRoamingNumberRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString mscNumber, ISDNAddressString msisdn, LMSI lmsi,
			ExternalSignalInfo gsmBearerCapability, ExternalSignalInfo networkSignalInfo, boolean suppressionOfAnnouncement, ISDNAddressString gmscAddress,
			CallReferenceNumber callReferenceNumber, boolean orInterrogation, MAPExtensionContainer extensionContainer, AlertingPattern alertingPattern,
			boolean ccbsCall, SupportedCamelPhases supportedCamelPhasesInInterrogatingNode, ExtExternalSignalInfo additionalSignalInfo,
			boolean orNotSupportedInGMSC, boolean prePagingSupported, boolean longFTNSupported, boolean suppressVtCsi,
			OfferedCamel4CSIs offeredCamel4CSIsInInterrogatingNode, boolean mtRoamingRetrySupported, PagingArea pagingArea, EMLPPPriority callPriority,
			boolean mtrfIndicator, ISDNAddressString oldMSCNumber) throws MAPException {
		return this.wrappedDialog.addProvideRoamingNumberRequest(customInvokeTimeout, imsi, mscNumber, msisdn, lmsi, gsmBearerCapability, networkSignalInfo,
				suppressionOfAnnouncement, gmscAddress, callReferenceNumber, orInterrogation, extensionContainer, alertingPattern, ccbsCall,
				supportedCamelPhasesInInterrogatingNode, additionalSignalInfo, orNotSupportedInGMSC, prePagingSupported, longFTNSupported, suppressVtCsi,
				offeredCamel4CSIsInInterrogatingNode, mtRoamingRetrySupported, pagingArea, callPriority, mtrfIndicator, oldMSCNumber);
	}

	@Override
	public void addProvideRoamingNumberResponse(long invokeId, ISDNAddressString roamingNumber, MAPExtensionContainer extensionContainer,
			boolean releaseResourcesSupported, ISDNAddressString vmscAddress) throws MAPException {
		this.wrappedDialog.addProvideRoamingNumberResponse(invokeId, roamingNumber, extensionContainer, releaseResourcesSupported, vmscAddress);
	}

	@Override
	public Long addProvideRoamingNumberRequest(IMSI arg0, ISDNAddressString arg1, ISDNAddressString arg2, LMSI arg3, ExternalSignalInfo arg4,
			ExternalSignalInfo arg5, boolean arg6, ISDNAddressString arg7, CallReferenceNumber arg8, boolean arg9, MAPExtensionContainer arg10,
			AlertingPattern arg11, boolean arg12, SupportedCamelPhases arg13, ExtExternalSignalInfo arg14, boolean arg15, boolean arg16, boolean arg17,
			boolean arg18, OfferedCamel4CSIs arg19, boolean arg20, PagingArea arg21, EMLPPPriority arg22, boolean arg23, ISDNAddressString arg24)
			throws MAPException {
		return this.wrappedDialog.addProvideRoamingNumberRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14,
				arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24);
	}

	@Override
	public Long addSendRoutingInformationRequest(ISDNAddressString arg0, CUGCheckInfo arg1, Integer arg2, ExternalSignalInfo arg3) throws MAPException {
		return this.wrappedDialog.addSendRoutingInformationRequest(arg0, arg1, arg2, arg3);
	}

	@Override
	public Long addSendRoutingInformationRequest(int arg0, ISDNAddressString arg1, CUGCheckInfo arg2, Integer arg3, ExternalSignalInfo arg4)
			throws MAPException {
		return this.wrappedDialog.addSendRoutingInformationRequest(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public Long addSendRoutingInformationRequest(ISDNAddressString arg0, CUGCheckInfo arg1, Integer arg2, InterrogationType arg3, boolean arg4, Integer arg5,
			ISDNAddressString arg6, CallReferenceNumber arg7, ForwardingReason arg8, ExtBasicServiceCode arg9, ExternalSignalInfo arg10, CamelInfo arg11,
			boolean arg12, MAPExtensionContainer arg13, AlertingPattern arg14, boolean arg15, Integer arg16, ExtExternalSignalInfo arg17,
			ISTSupportIndicator arg18, boolean arg19, CallDiversionTreatmentIndicator arg20, boolean arg21, boolean arg22, boolean arg23, boolean arg24,
			ExtBasicServiceCode arg25, ExternalSignalInfo arg26, SuppressMTSS arg27, boolean arg28, EMLPPPriority arg29) throws MAPException {
		return this.wrappedDialog.addSendRoutingInformationRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29);
	}

	@Override
	public Long addSendRoutingInformationRequest(int arg0, ISDNAddressString arg1, CUGCheckInfo arg2, Integer arg3, InterrogationType arg4, boolean arg5,
			Integer arg6, ISDNAddressString arg7, CallReferenceNumber arg8, ForwardingReason arg9, ExtBasicServiceCode arg10, ExternalSignalInfo arg11,
			CamelInfo arg12, boolean arg13, MAPExtensionContainer arg14, AlertingPattern arg15, boolean arg16, Integer arg17, ExtExternalSignalInfo arg18,
			ISTSupportIndicator arg19, boolean arg20, CallDiversionTreatmentIndicator arg21, boolean arg22, boolean arg23, boolean arg24, boolean arg25,
			ExtBasicServiceCode arg26, ExternalSignalInfo arg27, SuppressMTSS arg28, boolean arg29, EMLPPPriority arg30) throws MAPException {
		return this.wrappedDialog.addSendRoutingInformationRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29, arg30);
	}

	@Override
	public void addSendRoutingInformationResponse(long arg0, IMSI arg1, CUGCheckInfo arg2, RoutingInfo arg3) throws MAPException {
		this.wrappedDialog.addSendRoutingInformationResponse(arg0, arg1, arg2, arg3);
	}

	@Override
	public void addSendRoutingInformationResponse(long arg0, IMSI arg1, ExtendedRoutingInfo arg2, CUGCheckInfo arg3, boolean arg4, SubscriberInfo arg5,
			ArrayList<SSCode> arg6, ExtBasicServiceCode arg7, boolean arg8, ISDNAddressString arg9, MAPExtensionContainer arg10, NAEAPreferredCI arg11,
			CCBSIndicators arg12, ISDNAddressString arg13, NumberPortabilityStatus arg14, Integer arg15, SupportedCamelPhases arg16, OfferedCamel4CSIs arg17,
			RoutingInfo arg18, ArrayList<SSCode> arg19, ExtBasicServiceCode arg20, AllowedServices arg21, UnavailabilityCause arg22, boolean arg23,
			ExternalSignalInfo arg24) throws MAPException {
		this.wrappedDialog.addSendRoutingInformationResponse(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14,
				arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24);
	}

}
