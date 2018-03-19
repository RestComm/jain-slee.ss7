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

package org.restcomm.slee.resource.map.service.mobility.subscriberManagement.wrappers;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSSubscriptionDataWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSSubscriptionDataWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAInformationWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SpecificCSIWithdraw;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ZoneCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class DeleteSubscriberDataRequestWrapper extends MobilityMessageWrapper<DeleteSubscriberDataRequest> implements DeleteSubscriberDataRequest {

    private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.subscribermanagement.DELETE_SUBSCRIBER_DATA_REQUEST";

    public DeleteSubscriberDataRequestWrapper(MAPDialogMobilityWrapper mAPDialog, DeleteSubscriberDataRequest req) {
        super(mAPDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public IMSI getImsi() {
        return this.wrappedEvent.getImsi();
    }

    @Override
    public ArrayList<ExtBasicServiceCode> getBasicServiceList() {
        return this.wrappedEvent.getBasicServiceList();
    }

    @Override
    public ArrayList<SSCode> getSsList() {
        return this.wrappedEvent.getSsList();
    }

    @Override
    public boolean getRoamingRestrictionDueToUnsupportedFeature() {
        return this.wrappedEvent.getRoamingRestrictionDueToUnsupportedFeature();
    }

    @Override
    public ZoneCode getRegionalSubscriptionIdentifier() {
        return this.wrappedEvent.getRegionalSubscriptionIdentifier();
    }

    @Override
    public boolean getVbsGroupIndication() {
        return this.wrappedEvent.getVbsGroupIndication();
    }

    @Override
    public boolean getVgcsGroupIndication() {
        return this.wrappedEvent.getVgcsGroupIndication();
    }

    @Override
    public boolean getCamelSubscriptionInfoWithdraw() {
        return this.wrappedEvent.getCamelSubscriptionInfoWithdraw();
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.wrappedEvent.getExtensionContainer();
    }

    @Override
    public GPRSSubscriptionDataWithdraw getGPRSSubscriptionDataWithdraw() {
        return this.wrappedEvent.getGPRSSubscriptionDataWithdraw();
    }

    @Override
    public boolean getRoamingRestrictedInSgsnDueToUnsuppportedFeature() {
        return this.wrappedEvent.getRoamingRestrictedInSgsnDueToUnsuppportedFeature();
    }

    @Override
    public LSAInformationWithdraw getLSAInformationWithdraw() {
        return this.wrappedEvent.getLSAInformationWithdraw();
    }

    @Override
    public boolean getGmlcListWithdraw() {
        return this.wrappedEvent.getGmlcListWithdraw();
    }

    @Override
    public boolean getIstInformationWithdraw() {
        return this.wrappedEvent.getIstInformationWithdraw();
    }

    @Override
    public SpecificCSIWithdraw getSpecificCSIWithdraw() {
        return this.wrappedEvent.getSpecificCSIWithdraw();
    }

    @Override
    public boolean getChargingCharacteristicsWithdraw() {
        return this.wrappedEvent.getChargingCharacteristicsWithdraw();
    }

    @Override
    public boolean getStnSrWithdraw() {
        return this.wrappedEvent.getStnSrWithdraw();
    }

    @Override
    public EPSSubscriptionDataWithdraw getEPSSubscriptionDataWithdraw() {
        return this.wrappedEvent.getEPSSubscriptionDataWithdraw();
    }

    @Override
    public boolean getApnOiReplacementWithdraw() {
        return this.wrappedEvent.getApnOiReplacementWithdraw();
    }

    @Override
    public boolean getCsgSubscriptionDeleted() {
        return this.wrappedEvent.getCsgSubscriptionDeleted();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DeleteSubscriberDataRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
