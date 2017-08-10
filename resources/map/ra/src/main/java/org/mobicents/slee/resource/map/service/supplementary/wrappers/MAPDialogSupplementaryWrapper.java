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

package org.mobicents.slee.resource.map.service.supplementary.wrappers;

import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.mobicents.protocols.ss7.map.api.primitives.AddressString;
import org.mobicents.protocols.ss7.map.api.primitives.AlertingPattern;
import org.mobicents.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.USSDString;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.mobicents.protocols.ss7.map.api.service.supplementary.GenericServiceInfo;
import org.mobicents.protocols.ss7.map.api.service.supplementary.GuidanceInfo;
import org.mobicents.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.mobicents.protocols.ss7.map.api.service.supplementary.Password;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSCode;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSForBSCode;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSInfo;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.mobicents.slee.resource.map.MAPDialogActivityHandle;
import org.mobicents.slee.resource.map.MAPResourceAdaptor;
import org.mobicents.slee.resource.map.wrappers.MAPDialogWrapper;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "MAPDialogSupplementaryWrapper [wrappedDialog=" + getWrappedDialog() + "]";
    }

	public Long addProcessUnstructuredSSRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.getWrappedDialog().addProcessUnstructuredSSRequest(arg0, arg1, arg2, arg3);
	}

	public Long addProcessUnstructuredSSRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.getWrappedDialog().addProcessUnstructuredSSRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addProcessUnstructuredSSResponse(long arg0, CBSDataCodingScheme arg1, USSDString arg2) throws MAPException {
		this.getWrappedDialog().addProcessUnstructuredSSResponse(arg0, arg1, arg2);
	}

	public Long addUnstructuredSSNotifyRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.getWrappedDialog().addUnstructuredSSNotifyRequest(arg0, arg1, arg2, arg3);
	}

	public Long addUnstructuredSSNotifyRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.getWrappedDialog().addUnstructuredSSNotifyRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addUnstructuredSSNotifyResponse(long invokeId) throws MAPException{
		this.getWrappedDialog().addUnstructuredSSNotifyResponse(invokeId);
	}

	public Long addUnstructuredSSRequest(CBSDataCodingScheme arg0, USSDString arg1, AlertingPattern arg2, ISDNAddressString arg3)
			throws MAPException {
		return this.getWrappedDialog().addUnstructuredSSRequest(arg0, arg1, arg2, arg3);
	}

	public Long addUnstructuredSSRequest(int arg0, CBSDataCodingScheme arg1, USSDString arg2, AlertingPattern arg3,
			ISDNAddressString arg4) throws MAPException {
		return this.getWrappedDialog().addUnstructuredSSRequest(arg0, arg1, arg2, arg3, arg4);
	}

	public void addUnstructuredSSResponse(long arg0, CBSDataCodingScheme arg1, USSDString arg2) throws MAPException {
		this.getWrappedDialog().addUnstructuredSSResponse(arg0, arg1, arg2);
	}

    @Override
    public Long addRegisterSSRequest(SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber, ISDNAddressString forwardedToSubaddress,
            Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser, ISDNAddressString longFTNSupported) throws MAPException {
        return this.getWrappedDialog().addRegisterSSRequest(ssCode, basicService, forwardedToNumber, forwardedToSubaddress, noReplyConditionTime, defaultPriority,
                nbrUser, longFTNSupported);
    }

    @Override
    public Long addRegisterSSRequest(int customInvokeTimeout, SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber,
            ISDNAddressString forwardedToSubaddress, Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser,
            ISDNAddressString longFTNSupported) throws MAPException {
        return this.getWrappedDialog().addRegisterSSRequest(customInvokeTimeout, ssCode, basicService, forwardedToNumber, forwardedToSubaddress,
                noReplyConditionTime, defaultPriority, nbrUser, longFTNSupported);
    }

    @Override
    public void addRegisterSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.getWrappedDialog().addRegisterSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addEraseSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addEraseSSRequest(ssForBSCode);
    }

    @Override
    public Long addEraseSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addEraseSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addEraseSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.getWrappedDialog().addEraseSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addActivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addActivateSSRequest(ssForBSCode);
    }

    @Override
    public Long addActivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addActivateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addActivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.getWrappedDialog().addActivateSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addDeactivateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addDeactivateSSRequest(ssForBSCode);
    }

    @Override
    public Long addDeactivateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addDeactivateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addDeactivateSSResponse(long invokeId, SSInfo ssInfo) throws MAPException {
        this.getWrappedDialog().addDeactivateSSResponse(invokeId, ssInfo);
    }

    @Override
    public Long addInterrogateSSRequest(SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addInterrogateSSRequest(ssForBSCode);
    }

    @Override
    public Long addInterrogateSSRequest(int customInvokeTimeout, SSForBSCode ssForBSCode) throws MAPException {
        return this.getWrappedDialog().addInterrogateSSRequest(customInvokeTimeout, ssForBSCode);
    }

    @Override
    public void addInterrogateSSResponse_SSStatus(long invokeId, SSStatus ssStatus) throws MAPException {
        this.getWrappedDialog().addInterrogateSSResponse_SSStatus(invokeId, ssStatus);
    }

    @Override
    public void addInterrogateSSResponse_BasicServiceGroupList(long invokeId, ArrayList<BasicServiceCode> basicServiceGroupList) throws MAPException {
        this.getWrappedDialog().addInterrogateSSResponse_BasicServiceGroupList(invokeId, basicServiceGroupList);
    }

    @Override
    public void addInterrogateSSResponse_ForwardingFeatureList(long invokeId, ArrayList<ForwardingFeature> forwardingFeatureList) throws MAPException {
        this.getWrappedDialog().addInterrogateSSResponse_ForwardingFeatureList(invokeId, forwardingFeatureList);
    }

    @Override
    public void addInterrogateSSResponse_GenericServiceInfo(long invokeId, GenericServiceInfo genericServiceInfo) throws MAPException {
        this.getWrappedDialog().addInterrogateSSResponse_GenericServiceInfo(invokeId, genericServiceInfo);
    }

    @Override
    public Long addGetPasswordRequest(Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        return this.getWrappedDialog().addGetPasswordRequest(linkedId, guidanceInfo);
    }

    @Override
    public Long addGetPasswordRequest(int customInvokeTimeout, Long linkedId, GuidanceInfo guidanceInfo) throws MAPException {
        return this.getWrappedDialog().addGetPasswordRequest(customInvokeTimeout, linkedId, guidanceInfo);
    }

    @Override
    public void addGetPasswordResponse(long invokeId, Password password) throws MAPException {
        this.getWrappedDialog().addGetPasswordResponse(invokeId, password);
    }

    @Override
    public Long addRegisterPasswordRequest(SSCode ssCode) throws MAPException {
        return this.getWrappedDialog().addRegisterPasswordRequest(ssCode);
    }

    @Override
    public Long addRegisterPasswordRequest(int customInvokeTimeout, SSCode ssCode) throws MAPException {
        return this.getWrappedDialog().addRegisterPasswordRequest(customInvokeTimeout, ssCode);
    }

    @Override
    public void addRegisterPasswordResponse(long invokeId, Password password) throws MAPException {
        this.getWrappedDialog().addRegisterPasswordResponse(invokeId, password);
    }

}
