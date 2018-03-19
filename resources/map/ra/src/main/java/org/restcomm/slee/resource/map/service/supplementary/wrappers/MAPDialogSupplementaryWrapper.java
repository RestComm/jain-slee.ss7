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

package org.restcomm.slee.resource.map.service.supplementary.wrappers;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GenericServiceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GuidanceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.Password;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.MAPResourceAdaptor;
import org.restcomm.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class MAPDialogSupplementaryWrapper extends MAPDialogWrapper<MAPDialogSupplementary> implements
		MAPDialogSupplementary {

	public MAPDialogSupplementaryWrapper(MAPDialogSupplementary wrappedDialog, MAPDialogActivityHandle activityHandle,
			MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

    public MAPDialogSupplementary getWrappedDialog() {
        return this.wrappedDialog;
    }

    @Override
    public String toString() {
        return "MAPDialogSupplementaryWrapper [wrappedDialog=" + wrappedDialog + "]";
    }

	public Long addProcessUnstructuredSSRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.wrappedDialog.addProcessUnstructuredSSRequest(arg0, arg1, arg2, arg3);
	}

	public Long addProcessUnstructuredSSRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.wrappedDialog.addProcessUnstructuredSSRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addProcessUnstructuredSSResponse(long arg0, CBSDataCodingScheme arg1, USSDString arg2) throws MAPException {
		this.wrappedDialog.addProcessUnstructuredSSResponse(arg0, arg1, arg2);
	}

	public Long addUnstructuredSSNotifyRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.wrappedDialog.addUnstructuredSSNotifyRequest(arg0, arg1, arg2, arg3);
	}

	public Long addUnstructuredSSNotifyRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.wrappedDialog.addUnstructuredSSNotifyRequest(arg0, arg1, arg2, arg3, arg4);
	}
	
	public void addUnstructuredSSNotifyResponse(long invokeId) throws MAPException{
		this.wrappedDialog.addUnstructuredSSNotifyResponse(invokeId);
	}

	public Long addUnstructuredSSRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.wrappedDialog.addUnstructuredSSRequest(arg0, arg1, arg2, arg3);
	}

	public Long addUnstructuredSSRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.wrappedDialog.addUnstructuredSSRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addUnstructuredSSResponse(long arg0, CBSDataCodingScheme arg1, USSDString arg2) throws MAPException {
		this.wrappedDialog.addUnstructuredSSResponse(arg0, arg1, arg2);
	}

    @Override
    public Long addRegisterSSRequest(SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber, ISDNAddressString forwardedToSubaddress,
            Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser, ISDNAddressString longFTNSupported) throws MAPException {
        return this.wrappedDialog.addRegisterSSRequest(ssCode, basicService, forwardedToNumber, forwardedToSubaddress, noReplyConditionTime, defaultPriority,
                nbrUser, longFTNSupported);
    }

    @Override
    public Long addRegisterSSRequest(int customInvokeTimeout, SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber,
            ISDNAddressString forwardedToSubaddress, Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser,
            ISDNAddressString longFTNSupported) throws MAPException {
        return this.wrappedDialog.addRegisterSSRequest(customInvokeTimeout, ssCode, basicService, forwardedToNumber, forwardedToSubaddress,
                noReplyConditionTime, defaultPriority, nbrUser, longFTNSupported);
    }

    @Override
    public void addRegisterSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.wrappedDialog.addRegisterSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addEraseSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addEraseSSRequest(ssForBSCode);
    }

    @Override
    public Long addEraseSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addEraseSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addEraseSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.wrappedDialog.addEraseSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addActivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addActivateSSRequest(ssForBSCode);
    }

    @Override
    public Long addActivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addActivateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addActivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.wrappedDialog.addActivateSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addDeactivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addDeactivateSSRequest(ssForBSCode);
    }

    @Override
    public Long addDeactivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addDeactivateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addDeactivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.wrappedDialog.addDeactivateSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addInterrogateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addInterrogateSSRequest(ssForBSCode);
    }

    @Override
    public Long addInterrogateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.wrappedDialog.addInterrogateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addInterrogateSSResponse_SSStatus(long invokeId, SSStatus ssStatus) throws MAPException {
        this.wrappedDialog.addInterrogateSSResponse_SSStatus(invokeId, ssStatus);
    }

    @Override
    public void addInterrogateSSResponse_BasicServiceGroupList(long invokeId, ArrayList<BasicServiceCode> basicServiceGroupList) throws MAPException {
        this.wrappedDialog.addInterrogateSSResponse_BasicServiceGroupList(invokeId, basicServiceGroupList);
    }

    @Override
    public void addInterrogateSSResponse_ForwardingFeatureList(long invokeId, ArrayList<ForwardingFeature> forwardingFeatureList) throws MAPException {
        this.wrappedDialog.addInterrogateSSResponse_ForwardingFeatureList(invokeId, forwardingFeatureList);
    }

    @Override
    public void addInterrogateSSResponse_GenericServiceInfo(long invokeId, GenericServiceInfo genericServiceInfo) throws MAPException {
        this.wrappedDialog.addInterrogateSSResponse_GenericServiceInfo(invokeId, genericServiceInfo);
    }

    @Override
    public Long addGetPasswordRequest(Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        return this.wrappedDialog.addGetPasswordRequest(linkedId, guidanceInfo);
    }

    @Override
    public Long addGetPasswordRequest(int customInvokeTimeout, Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        return this.wrappedDialog.addGetPasswordRequest(customInvokeTimeout, linkedId, guidanceInfo);
    }

    @Override
    public void addGetPasswordResponse(long invokeId, Password password) throws MAPException {
        this.wrappedDialog.addGetPasswordResponse(invokeId, password);
    }

    @Override
    public Long addRegisterPasswordRequest(SSCode ssCode) throws MAPException {
        return this.wrappedDialog.addRegisterPasswordRequest(ssCode);
    }

    @Override
    public Long addRegisterPasswordRequest(int customInvokeTimeout, SSCode ssCode) throws MAPException {
        return this.wrappedDialog.addRegisterPasswordRequest(customInvokeTimeout, ssCode);
    }

    @Override
    public void addRegisterPasswordResponse(long invokeId, Password password) throws MAPException {
        this.wrappedDialog.addRegisterPasswordResponse(invokeId, password);
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
