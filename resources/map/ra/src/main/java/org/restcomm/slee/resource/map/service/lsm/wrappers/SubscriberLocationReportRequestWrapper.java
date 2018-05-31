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
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.SLRArgExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;

/**
 * @author baranowb
 * @author amit bhayani
 */
public class SubscriberLocationReportRequestWrapper extends LsmMessageWrapper<SubscriberLocationReportRequest>
		implements SubscriberLocationReportRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.lsm.SUBSCRIBER_LOCATION_REPORT_REQUEST";

	/**
	 * @param mapDialogWrapper
	 * @param subscriberLocationReportRequest
	 */
	public SubscriberLocationReportRequestWrapper(MAPDialogLsmWrapper mAPDialog,
			SubscriberLocationReportRequest subscriberLocationReportRequest) {
		super(mAPDialog, EVENT_TYPE_NAME, subscriberLocationReportRequest);
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

	public DeferredmtlrData getDeferredmtlrData() {
		return this.wrappedEvent.getDeferredmtlrData();
	}

	public PositioningDataInformation getGeranPositioningData() {
		return this.wrappedEvent.getGeranPositioningData();
	}

	public GSNAddress getHGMLCAddress() {
		return this.wrappedEvent.getHGMLCAddress();
	}

	public IMEI getIMEI() {
		return this.wrappedEvent.getIMEI();
	}

	public IMSI getIMSI() {
		return this.wrappedEvent.getIMSI();
	}

	public long getInvokeId() {
		return this.wrappedEvent.getInvokeId();
	}

	public LCSClientID getLCSClientID() {
		return this.wrappedEvent.getLCSClientID();
	}

	public LCSEvent getLCSEvent() {
		return this.wrappedEvent.getLCSEvent();
	}

	public LCSLocationInfo getLCSLocationInfo() {
		return this.wrappedEvent.getLCSLocationInfo();
	}

	public Integer getLCSReferenceNumber() {
		return this.wrappedEvent.getLCSReferenceNumber();
	}

	public Integer getLCSServiceTypeID() {
		return this.wrappedEvent.getLCSServiceTypeID();
	}

	public ExtGeographicalInformation getLocationEstimate() {
		return this.wrappedEvent.getLocationEstimate();
	}

	public ISDNAddressString getMSISDN() {
		return this.wrappedEvent.getMSISDN();
	}

	public ISDNAddressString getNaESRD() {
		return this.wrappedEvent.getNaESRD();
	}

	public ISDNAddressString getNaESRK() {
		return this.wrappedEvent.getNaESRK();
	}

	public boolean getPseudonymIndicator() {
		return this.wrappedEvent.getPseudonymIndicator();
	}

	public SLRArgExtensionContainer getSLRArgExtensionContainer() {
		return this.wrappedEvent.getSLRArgExtensionContainer();
	}

	public boolean getSaiPresent() {
		return this.wrappedEvent.getSaiPresent();
	}

	public UtranPositioningDataInfo getUtranPositioningData() {
		return this.wrappedEvent.getUtranPositioningData();
	}

	public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
		return this.wrappedEvent.getCellGlobalIdOrServiceAreaIdOrLAI();
	}

	public GeranGANSSpositioningData getGeranGANSSpositioningData() {
		return this.wrappedEvent.getGeranGANSSpositioningData();
	}

	public boolean getMoLrShortCircuitIndicator() {
		return this.wrappedEvent.getMoLrShortCircuitIndicator();
	}

	public PeriodicLDRInfo getPeriodicLDRInfo() {
		return this.wrappedEvent.getPeriodicLDRInfo();
	}

	public Integer getSequenceNumber() {
		return this.wrappedEvent.getSequenceNumber();
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
		return "SubscriberLocationReportRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

	@Override
	public LMSI getLMSI() {
		return this.wrappedEvent.getLMSI();
	}

	@Override
	public ReportingPLMNList getReportingPLMNList() {
		return this.wrappedEvent.getReportingPLMNList();
	}

}
