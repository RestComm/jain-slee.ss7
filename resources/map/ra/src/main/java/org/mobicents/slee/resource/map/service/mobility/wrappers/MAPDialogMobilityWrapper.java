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
package org.mobicents.slee.resource.map.service.mobility.wrappers;

import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.primitives.GSNAddress;
import org.mobicents.protocols.ss7.map.api.primitives.IMEI;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.LMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.primitives.PlmnId;
import org.mobicents.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.mobicents.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.AuthenticationSetList;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.EpsAuthenticationSetList;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.ReSynchronisationInfo;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.RequestingNodeType;
import org.mobicents.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.mobicents.protocols.ss7.map.api.service.mobility.imei.RequestedEquipmentInfo;
import org.mobicents.protocols.ss7.map.api.service.mobility.imei.UESBIIu;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.ADDInfo;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.CancellationType;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.IMSIWithLMSI;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.PagingArea;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.TypeOfUpdate;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.VLRCapability;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.mobicents.slee.resource.map.MAPDialogActivityHandle;
import org.mobicents.slee.resource.map.MAPResourceAdaptor;
import org.mobicents.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author amit bhayani
 * 
 */
public class MAPDialogMobilityWrapper extends MAPDialogWrapper<MAPDialogMobility> implements MAPDialogMobility {

	public MAPDialogMobilityWrapper(MAPDialogMobility wrappedDialog, MAPDialogActivityHandle activityHandle,
			MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public MAPDialogMobility getWrappedDialog() {
		return this.wrappedDialog;
	}

	@Override
	public Long addSendAuthenticationInfoRequest(IMSI imsi, int numberOfRequestedVectors,
			boolean segmentationProhibited, boolean immediateResponsePreferred,
			ReSynchronisationInfo reSynchronisationInfo, MAPExtensionContainer extensionContainer,
			RequestingNodeType requestingNodeType, PlmnId requestingPlmnId, Integer numberOfRequestedAdditionalVectors,
			boolean additionalVectorsAreForEPS) throws MAPException {
		return this.wrappedDialog.addSendAuthenticationInfoRequest(imsi, numberOfRequestedVectors,
				segmentationProhibited, immediateResponsePreferred, reSynchronisationInfo, extensionContainer,
				requestingNodeType, requestingPlmnId, numberOfRequestedAdditionalVectors, additionalVectorsAreForEPS);
	}

	@Override
	public Long addSendAuthenticationInfoRequest(int customInvokeTimeout, IMSI imsi, int numberOfRequestedVectors,
			boolean segmentationProhibited, boolean immediateResponsePreferred,
			ReSynchronisationInfo reSynchronisationInfo, MAPExtensionContainer extensionContainer,
			RequestingNodeType requestingNodeType, PlmnId requestingPlmnId, Integer numberOfRequestedAdditionalVectors,
			boolean additionalVectorsAreForEPS) throws MAPException {
		return this.wrappedDialog.addSendAuthenticationInfoRequest(customInvokeTimeout, imsi, numberOfRequestedVectors,
				segmentationProhibited, immediateResponsePreferred, reSynchronisationInfo, extensionContainer,
				requestingNodeType, requestingPlmnId, numberOfRequestedAdditionalVectors, additionalVectorsAreForEPS);
	}

	@Override
	public void addSendAuthenticationInfoResponse(long invokeId, AuthenticationSetList authenticationSetList,
			MAPExtensionContainer extensionContainer, EpsAuthenticationSetList epsAuthenticationSetList)
			throws MAPException {
		this.wrappedDialog.addSendAuthenticationInfoResponse(invokeId, authenticationSetList, extensionContainer,
				epsAuthenticationSetList);
	}

	@Override
	public long addAnyTimeInterrogationRequest(SubscriberIdentity subscriberIdentity, RequestedInfo requestedInfo,
			ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer) throws MAPException {
		return this.wrappedDialog.addAnyTimeInterrogationRequest(subscriberIdentity, requestedInfo, gsmSCFAddress,
				extensionContainer);
	}

	@Override
	public long addAnyTimeInterrogationRequest(long customInvokeTimeout, SubscriberIdentity subscriberIdentity,
			RequestedInfo requestedInfo, ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer)
			throws MAPException {
		return this.wrappedDialog.addAnyTimeInterrogationRequest(customInvokeTimeout, subscriberIdentity,
				requestedInfo, gsmSCFAddress, extensionContainer);
	}

	@Override
	public void addAnyTimeInterrogationResponse(long invokeId, SubscriberInfo subscriberInfo,
			MAPExtensionContainer extensionContainer) throws MAPException {
		this.addAnyTimeInterrogationResponse(invokeId, subscriberInfo, extensionContainer);
	}

	@Override
	public Long addUpdateLocationRequest(IMSI imsi, ISDNAddressString mscNumber, ISDNAddressString roamingNumber,
			ISDNAddressString vlrNumber, LMSI lmsi, MAPExtensionContainer extensionContainer,
			VLRCapability vlrCapability, boolean informPreviousNetworkEntity, boolean csLCSNotSupportedByUE,
			GSNAddress vGmlcAddress, ADDInfo addInfo, PagingArea pagingArea, boolean skipSubscriberDataUpdate,
			boolean restorationIndicator) throws MAPException {
		return this.wrappedDialog.addUpdateLocationRequest(imsi, mscNumber, roamingNumber, vlrNumber, lmsi,
				extensionContainer, vlrCapability, informPreviousNetworkEntity, csLCSNotSupportedByUE, vGmlcAddress,
				addInfo, pagingArea, skipSubscriberDataUpdate, restorationIndicator);
	}

	@Override
	public Long addUpdateLocationRequest(int customInvokeTimeout, IMSI imsi, ISDNAddressString mscNumber,
			ISDNAddressString roamingNumber, ISDNAddressString vlrNumber, LMSI lmsi,
			MAPExtensionContainer extensionContainer, VLRCapability vlrCapability, boolean informPreviousNetworkEntity,
			boolean csLCSNotSupportedByUE, GSNAddress vGmlcAddress, ADDInfo addInfo, PagingArea pagingArea,
			boolean skipSubscriberDataUpdate, boolean restorationIndicator) throws MAPException {
		return this.wrappedDialog.addUpdateLocationRequest(customInvokeTimeout, imsi, mscNumber, roamingNumber,
				vlrNumber, lmsi, extensionContainer, vlrCapability, informPreviousNetworkEntity, csLCSNotSupportedByUE,
				vGmlcAddress, addInfo, pagingArea, skipSubscriberDataUpdate, restorationIndicator);
	}

	@Override
	public void addUpdateLocationResponse(long invokeId, ISDNAddressString hlrNumber,
			MAPExtensionContainer extensionContainer, boolean addCapability, boolean pagingAreaCapability)
			throws MAPException {
		this.wrappedDialog.addUpdateLocationResponse(invokeId, hlrNumber, extensionContainer, addCapability,
				pagingAreaCapability);
	}

	@Override
	public Long addCheckImeiRequest(IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
			MAPExtensionContainer extensionContainer) throws MAPException {
		return this.wrappedDialog.addCheckImeiRequest(imei, requestedEquipmentInfo, extensionContainer);
	}

	@Override
	public Long addCheckImeiRequest(long customInvokeTimeout, IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
			MAPExtensionContainer extensionContainer) throws MAPException {
		return this.wrappedDialog.addCheckImeiRequest(customInvokeTimeout, imei, requestedEquipmentInfo,
				extensionContainer);
	}

	@Override
	public void addCheckImeiResponse(long invokeId, EquipmentStatus equipmentStatus, UESBIIu bmuef,
			MAPExtensionContainer extensionContainer) throws MAPException {
		this.wrappedDialog.addCheckImeiResponse(invokeId, equipmentStatus, bmuef, extensionContainer);
	}

	@Override
	public Long addCheckImeiRequest_Huawei(IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo, 
			MAPExtensionContainer extensionContainer, IMSI imsi)
			throws MAPException {
		return this.wrappedDialog.addCheckImeiRequest_Huawei(imei, requestedEquipmentInfo, extensionContainer, imsi);
	}

	@Override
	public Long addCheckImeiRequest_Huawei(long arg0, IMEI arg1, RequestedEquipmentInfo arg2,
			MAPExtensionContainer arg3, IMSI arg4) throws MAPException {
		return this.wrappedDialog.addCheckImeiRequest_Huawei(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public Long addCancelLocationRequest(int customInvokeTimeout, IMSI imsi, IMSIWithLMSI imsiWithLmsi, CancellationType cancellationType,
			MAPExtensionContainer extensionContainer, TypeOfUpdate typeOfUpdate, boolean mtrfSupportedAndAuthorized, boolean mtrfSupportedAndNotAuthorized,
			ISDNAddressString newMSCNumber, ISDNAddressString newVLRNumber, LMSI newLmsi) throws MAPException {
		return this.wrappedDialog.addCancelLocationRequest(customInvokeTimeout, imsi, imsiWithLmsi, cancellationType, extensionContainer, typeOfUpdate,
				mtrfSupportedAndAuthorized, mtrfSupportedAndNotAuthorized, newMSCNumber, newVLRNumber, newLmsi);
	}

	@Override
	public Long addCancelLocationRequest(IMSI imsi, IMSIWithLMSI imsiWithLmsi, CancellationType cancellationType, MAPExtensionContainer extensionContainer,
			TypeOfUpdate typeOfUpdate, boolean mtrfSupportedAndAuthorized, boolean mtrfSupportedAndNotAuthorized, ISDNAddressString newMSCNumber,
			ISDNAddressString newVLRNumber, LMSI newLmsi) throws MAPException {
		return this.wrappedDialog.addCancelLocationRequest(imsi, imsiWithLmsi, cancellationType, extensionContainer, typeOfUpdate,
				mtrfSupportedAndAuthorized, mtrfSupportedAndNotAuthorized, newMSCNumber, newVLRNumber, newLmsi);
	}

	@Override
	public void addCancelLocationResponse(long invokeId, MAPExtensionContainer extensionContainer) throws MAPException {
		this.wrappedDialog.addCancelLocationResponse(invokeId, extensionContainer);
	}

}
