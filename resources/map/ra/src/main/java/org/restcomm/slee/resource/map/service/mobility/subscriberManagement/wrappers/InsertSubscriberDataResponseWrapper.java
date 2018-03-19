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

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedFeatures;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBGeneralData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.RegionalSubscriptionResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class InsertSubscriberDataResponseWrapper extends MobilityMessageWrapper<InsertSubscriberDataResponse> implements InsertSubscriberDataResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.subscribermanagement.INSERT_SUBSCRIBER_DATA_RESPONSE";

	/**
	 * @param mAPDialog
	 */
	public InsertSubscriberDataResponseWrapper(MAPDialogMobilityWrapper mAPDialog, InsertSubscriberDataResponse req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public ArrayList<ExtTeleserviceCode> getTeleserviceList() {
		return this.wrappedEvent.getTeleserviceList();
	}

	@Override
	public ArrayList<ExtBearerServiceCode> getBearerServiceList() {
		return this.wrappedEvent.getBearerServiceList();
	}

	@Override
	public ArrayList<SSCode> getSSList() {
		return this.wrappedEvent.getSSList();
	}

	@Override
	public ODBGeneralData getODBGeneralData() {
		return this.wrappedEvent.getODBGeneralData();
	}

	@Override
	public RegionalSubscriptionResponse getRegionalSubscriptionResponse() {
		return this.wrappedEvent.getRegionalSubscriptionResponse();
	}

	@Override
	public SupportedCamelPhases getSupportedCamelPhases() {
		return this.wrappedEvent.getSupportedCamelPhases();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public OfferedCamel4CSIs getOfferedCamel4CSIs() {
		return this.wrappedEvent.getOfferedCamel4CSIs();
	}

	@Override
	public SupportedFeatures getSupportedFeatures() {
		return this.wrappedEvent.getSupportedFeatures();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InsertSubscriberDataResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
