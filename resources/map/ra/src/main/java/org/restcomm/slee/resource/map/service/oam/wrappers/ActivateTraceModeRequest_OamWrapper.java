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

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.ActivateTraceModeRequest_Oam;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;

/**
 * 
 * @author sergey vetyutnev
 *
 */
public class ActivateTraceModeRequest_OamWrapper extends OamMessageWrapper<ActivateTraceModeRequest_Oam> implements ActivateTraceModeRequest_Oam {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.oam.ACTIVATE_TRACE_MODE_REQUEST_OAM";

    public ActivateTraceModeRequest_OamWrapper(MAPDialogOamWrapper mAPDialog, ActivateTraceModeRequest_Oam req) {
        super(mAPDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public IMSI getImsi() {
        return this.wrappedEvent.getImsi();
    }

    @Override
    public TraceReference getTraceReference() {
        return this.wrappedEvent.getTraceReference();
    }

    @Override
    public TraceType getTraceType() {
        return this.wrappedEvent.getTraceType();
    }

    @Override
    public AddressString getOmcId() {
        return this.wrappedEvent.getOmcId();
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.wrappedEvent.getExtensionContainer();
    }

    @Override
    public TraceReference2 getTraceReference2() {
        return this.wrappedEvent.getTraceReference2();
    }

    @Override
    public TraceDepthList getTraceDepthList() {
        return this.wrappedEvent.getTraceDepthList();
    }

    @Override
    public TraceNETypeList getTraceNeTypeList() {
        return this.wrappedEvent.getTraceNeTypeList();
    }

    @Override
    public TraceInterfaceList getTraceInterfaceList() {
        return this.wrappedEvent.getTraceInterfaceList();
    }

    @Override
    public TraceEventList getTraceEventList() {
        return this.wrappedEvent.getTraceEventList();
    }

    @Override
    public GSNAddress getTraceCollectionEntity() {
        return this.wrappedEvent.getTraceCollectionEntity();
    }

    @Override
    public MDTConfiguration getMdtConfiguration() {
        return this.wrappedEvent.getMdtConfiguration();
    }

    @Override
    public String toString() {
        return "ActivateTraceModeRequest_OamWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
