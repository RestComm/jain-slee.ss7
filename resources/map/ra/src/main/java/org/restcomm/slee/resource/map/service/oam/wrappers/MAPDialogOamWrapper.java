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

package org.restcomm.slee.resource.map.service.oam.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.MAPResourceAdaptor;
import org.restcomm.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MAPDialogOamWrapper extends MAPDialogWrapper<MAPDialogOam> implements MAPDialogOam {

	public MAPDialogOamWrapper(MAPDialogOam wrappedDialog, MAPDialogActivityHandle activityHandle,
			MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public MAPDialogOam getWrappedDialog() {
		return this.wrappedDialog;
	}


	@Override
    public Long addSendImsiRequest(ISDNAddressString msisdn) throws MAPException {
        return this.wrappedDialog.addSendImsiRequest(msisdn);
    }

    @Override
    public Long addSendImsiRequest(int customInvokeTimeout, ISDNAddressString msisdn) throws MAPException {
        return this.wrappedDialog.addSendImsiRequest(customInvokeTimeout, msisdn);
    }

    @Override
    public void addSendImsiResponse(long invokeId, IMSI imsi) throws MAPException {
        this.wrappedDialog.addSendImsiResponse(invokeId, imsi);
    }

    @Override
    public Long addActivateTraceModeRequest(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {
        return this.wrappedDialog.addActivateTraceModeRequest(imsi, traceReference, traceType, omcId, extensionContainer, traceReference2, traceDepthList,
                traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
    }

    @Override
    public Long addActivateTraceModeRequest(int customInvokeTimeout, IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration)
            throws MAPException {
        return this.wrappedDialog.addActivateTraceModeRequest(customInvokeTimeout, imsi, traceReference, traceType, omcId, extensionContainer, traceReference2,
                traceDepthList, traceNeTypeList, traceInterfaceList, traceEventList, traceCollectionEntity, mdtConfiguration);
    }

    @Override
    public void addActivateTraceModeResponse(long invokeId, MAPExtensionContainer extensionContainer, boolean traceSupportIndicator) throws MAPException {
        this.wrappedDialog.addActivateTraceModeResponse(invokeId, extensionContainer, traceSupportIndicator);
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
