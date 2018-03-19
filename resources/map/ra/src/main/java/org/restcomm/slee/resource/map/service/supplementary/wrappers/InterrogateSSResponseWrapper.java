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

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GenericServiceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.InterrogateSSResponse;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;

/**
 * 
 * @author sergey vetyutnev
 *
 */
public class InterrogateSSResponseWrapper extends SupplementaryMessageWrapper<InterrogateSSResponse> implements InterrogateSSResponse {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.suplementary.INTERROGATE_SS_RESPONSE";

    public InterrogateSSResponseWrapper(MAPDialogSupplementaryWrapper mAPDialog, InterrogateSSResponse req) {
        super(mAPDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public SSStatus getSsStatus() {
        return this.wrappedEvent.getSsStatus();
    }

    @Override
    public ArrayList<BasicServiceCode> getBasicServiceGroupList() {
        return this.wrappedEvent.getBasicServiceGroupList();
    }

    @Override
    public ArrayList<ForwardingFeature> getForwardingFeatureList() {
        return this.wrappedEvent.getForwardingFeatureList();
    }

    @Override
    public GenericServiceInfo getGenericServiceInfo() {
        return this.wrappedEvent.getGenericServiceInfo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InterrogateSSResponseWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
