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

package org.restcomm.slee.resource.map.service.sms.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.sms.IpSmGwGuidance;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;

/**
 * 
 * @author amit bhayani
 *
 */
public class SendRoutingInfoForSMResponseWrapper extends SmsMessageWrapper<SendRoutingInfoForSMResponse> implements
		SendRoutingInfoForSMResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.sms.SEND_ROUTING_INFO_FOR_SM_RESPONSE";

	public SendRoutingInfoForSMResponseWrapper(MAPDialogSmsWrapper mAPDialog, SendRoutingInfoForSMResponse req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public IMSI getIMSI() {
		return this.wrappedEvent.getIMSI();
	}

	public LocationInfoWithLMSI getLocationInfoWithLMSI() {
		return this.wrappedEvent.getLocationInfoWithLMSI();
	}

	public Boolean getMwdSet() {
		return this.wrappedEvent.getMwdSet();
	}

    @Override
    public IpSmGwGuidance getIpSmGwGuidance() {
        return this.wrappedEvent.getIpSmGwGuidance();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendRoutingInfoForSMResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
