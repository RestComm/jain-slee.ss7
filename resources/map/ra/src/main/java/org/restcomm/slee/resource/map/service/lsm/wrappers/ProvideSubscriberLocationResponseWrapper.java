/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
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

package org.restcomm.slee.resource.map.service.lsm.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;

/**
 * @author baranowb
 * @author amit bhayani
 */
public class ProvideSubscriberLocationResponseWrapper extends LsmMessageWrapper<ProvideSubscriberLocationResponse>
		implements ProvideSubscriberLocationResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.lsm.PROVIDE_SUBSCRIBER_LOCATION_RESPONSE";

	/**
	 * @param mapDialogWrapper
	 * @param provideSubscriberLocationResponse
	 */
	public ProvideSubscriberLocationResponseWrapper(MAPDialogLsmWrapper mAPDialog,
			ProvideSubscriberLocationResponse provideSubscriberLocationResponse) {
		super(mAPDialog, EVENT_TYPE_NAME, provideSubscriberLocationResponse);
	}

	public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
		return this.wrappedEvent.getAccuracyFulfilmentIndicator();
	}

	public AddGeographicalInformation getAdditionalLocationEstimate() {
		return this.wrappedEvent.getAdditionalLocationEstimate();
	}

	public Integer getAgeOfLocationEstimate() {
		return this.wrappedEvent.getAgeOfLocationEstimate();
	}

	public boolean getDeferredMTLRResponseIndicator() {
		return this.wrappedEvent.getDeferredMTLRResponseIndicator();
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public PositioningDataInformation getGeranPositioningData() {
		return this.wrappedEvent.getGeranPositioningData();
	}

	public ExtGeographicalInformation getLocationEstimate() {
		return this.wrappedEvent.getLocationEstimate();
	}

	public boolean getSaiPresent() {
		return this.wrappedEvent.getSaiPresent();
	}

	public UtranPositioningDataInfo getUtranPositioningData() {
		return this.wrappedEvent.getUtranPositioningData();
	}

	public CellGlobalIdOrServiceAreaIdOrLAI getCellIdOrSai() {
		return this.wrappedEvent.getCellIdOrSai();
	}

	public GeranGANSSpositioningData getGeranGANSSpositioningData() {
		return this.wrappedEvent.getGeranGANSSpositioningData();
	}

	public boolean getMoLrShortCircuitIndicator() {
		return this.wrappedEvent.getMoLrShortCircuitIndicator();
	}

	public ServingNodeAddress getTargetServingNodeForHandover() {
		return this.wrappedEvent.getTargetServingNodeForHandover();
	}

	public UtranGANSSpositioningData getUtranGANSSpositioningData() {
		return this.wrappedEvent.getUtranGANSSpositioningData();
	}

	public VelocityEstimate getVelocityEstimate() {
		return this.wrappedEvent.getVelocityEstimate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProvideSubscriberLocationResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
