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

import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.AgeIndicator;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AccessRestrictionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSAllocationRetentionPriority;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Category;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ChargingCharacteristics;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EPSSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSSubscriptionData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LCSInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MCSSInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.NetworkAccessMode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SGSNCAMELSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SubscriberStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VlrCamelSubscriptionInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VoiceBroadcastData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VoiceGroupCallData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ZoneCode;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class InsertSubscriberDataRequestWrapper extends MobilityMessageWrapper<InsertSubscriberDataRequest> implements InsertSubscriberDataRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.subscribermanagement.INSERT_SUBSCRIBER_DATA_REQUEST";

	/**
	 * @param mAPDialog
	 */
	public InsertSubscriberDataRequestWrapper(MAPDialogMobilityWrapper mAPDialog, InsertSubscriberDataRequest req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public IMSI getImsi() {
		return this.wrappedEvent.getImsi();
	}

	@Override
	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	@Override
	public Category getCategory() {
		return this.wrappedEvent.getCategory();
	}

	@Override
	public SubscriberStatus getSubscriberStatus() {
		return this.wrappedEvent.getSubscriberStatus();
	}

	@Override
	public ArrayList<ExtBearerServiceCode> getBearerServiceList() {
		return this.wrappedEvent.getBearerServiceList();
	}

	@Override
	public ArrayList<ExtTeleserviceCode> getTeleserviceList() {
		return this.wrappedEvent.getTeleserviceList();
	}

	@Override
	public ArrayList<ExtSSInfo> getProvisionedSS() {
		return this.wrappedEvent.getProvisionedSS();
	}

	@Override
	public ODBData getODBData() {
		return this.wrappedEvent.getODBData();
	}

	@Override
	public boolean getRoamingRestrictionDueToUnsupportedFeature() {
		return this.wrappedEvent.getRoamingRestrictionDueToUnsupportedFeature();
	}

	@Override
	public ArrayList<ZoneCode> getRegionalSubscriptionData() {
		return this.wrappedEvent.getRegionalSubscriptionData();
	}

	@Override
	public ArrayList<VoiceBroadcastData> getVbsSubscriptionData() {
		return this.wrappedEvent.getVbsSubscriptionData();
	}

	@Override
	public ArrayList<VoiceGroupCallData> getVgcsSubscriptionData() {
		return this.wrappedEvent.getVgcsSubscriptionData();
	}

	@Override
	public VlrCamelSubscriptionInfo getVlrCamelSubscriptionInfo() {
		return this.wrappedEvent.getVlrCamelSubscriptionInfo();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public NAEAPreferredCI getNAEAPreferredCI() {
		return this.wrappedEvent.getNAEAPreferredCI();
	}

	@Override
	public GPRSSubscriptionData getGPRSSubscriptionData() {
		return this.wrappedEvent.getGPRSSubscriptionData();
	}

	@Override
	public boolean getRoamingRestrictedInSgsnDueToUnsupportedFeature() {
		return this.wrappedEvent.getRoamingRestrictedInSgsnDueToUnsupportedFeature();
	}

	@Override
	public NetworkAccessMode getNetworkAccessMode() {
		return this.wrappedEvent.getNetworkAccessMode();
	}

	@Override
	public LSAInformation getLSAInformation() {
		return this.wrappedEvent.getLSAInformation();
	}

	@Override
	public boolean getLmuIndicator() {
		return this.wrappedEvent.getLmuIndicator();
	}

	@Override
	public LCSInformation getLCSInformation() {
		return this.wrappedEvent.getLCSInformation();
	}

	@Override
	public Integer getIstAlertTimer() {
		return this.wrappedEvent.getIstAlertTimer();
	}

	@Override
	public AgeIndicator getSuperChargerSupportedInHLR() {
		return this.wrappedEvent.getSuperChargerSupportedInHLR();
	}

	@Override
	public MCSSInfo getMcSsInfo() {
		return this.wrappedEvent.getMcSsInfo();
	}

	@Override
	public CSAllocationRetentionPriority getCSAllocationRetentionPriority() {
		return this.wrappedEvent.getCSAllocationRetentionPriority();
	}

	@Override
	public SGSNCAMELSubscriptionInfo getSgsnCamelSubscriptionInfo() {
		return this.wrappedEvent.getSgsnCamelSubscriptionInfo();
	}

	@Override
	public ChargingCharacteristics getChargingCharacteristics() {
		return this.wrappedEvent.getChargingCharacteristics();
	}

	@Override
	public AccessRestrictionData getAccessRestrictionData() {
		return this.wrappedEvent.getAccessRestrictionData();
	}

	@Override
	public Boolean getIcsIndicator() {
		return this.wrappedEvent.getIcsIndicator();
	}

	@Override
	public EPSSubscriptionData getEpsSubscriptionData() {
		return this.wrappedEvent.getEpsSubscriptionData();
	}

	@Override
	public ArrayList<CSGSubscriptionData> getCsgSubscriptionDataList() {
		return this.wrappedEvent.getCsgSubscriptionDataList();
	}

	@Override
	public boolean getUeReachabilityRequestIndicator() {
		return this.wrappedEvent.getUeReachabilityRequestIndicator();
	}

	@Override
	public ISDNAddressString getSgsnNumber() {
		return this.wrappedEvent.getSgsnNumber();
	}

	@Override
	public DiameterIdentity getMmeName() {
		return this.wrappedEvent.getMmeName();
	}

	@Override
	public Long getSubscribedPeriodicRAUTAUtimer() {
		return this.wrappedEvent.getSubscribedPeriodicRAUTAUtimer();
	}

	@Override
	public boolean getVplmnLIPAAllowed() {
		return this.wrappedEvent.getVplmnLIPAAllowed();
	}

	@Override
	public Boolean getMdtUserConsent() {
		return this.wrappedEvent.getMdtUserConsent();
	}

	@Override
	public Long getSubscribedPeriodicLAUtimer() {
		return this.wrappedEvent.getSubscribedPeriodicLAUtimer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InsertSubscriberDataRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
