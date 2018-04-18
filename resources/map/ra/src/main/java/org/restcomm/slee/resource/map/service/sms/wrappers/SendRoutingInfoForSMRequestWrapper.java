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

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.sms.CorrelationID;
import org.restcomm.protocols.ss7.map.api.service.sms.SMDeliveryNotIntended;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_MTI;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_SMEA;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMRequest;

/**
 * 
 * @author amit bhayani
 *
 */
public class SendRoutingInfoForSMRequestWrapper extends SmsMessageWrapper<SendRoutingInfoForSMRequest> implements
		SendRoutingInfoForSMRequest {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.sms.SEND_ROUTING_INFO_FOR_SM_REQUEST";

	public SendRoutingInfoForSMRequestWrapper(MAPDialogSmsWrapper mAPDialog, SendRoutingInfoForSMRequest req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public boolean getGprsSupportIndicator() {
		return this.wrappedEvent.getGprsSupportIndicator();
	}

	public ISDNAddressString getMsisdn() {
		return this.wrappedEvent.getMsisdn();
	}

	public SM_RP_MTI getSM_RP_MTI() {
		return this.wrappedEvent.getSM_RP_MTI();
	}

	public SM_RP_SMEA getSM_RP_SMEA() {
		return this.wrappedEvent.getSM_RP_SMEA();
	}

	public AddressString getServiceCentreAddress() {
		return this.wrappedEvent.getServiceCentreAddress();
	}

	public boolean getSm_RP_PRI() {
		return this.wrappedEvent.getSm_RP_PRI();
	}

	public TeleserviceCode getTeleservice() {
		return this.wrappedEvent.getTeleservice();
	}

    @Override
    public SMDeliveryNotIntended getSmDeliveryNotIntended() {
        return this.wrappedEvent.getSmDeliveryNotIntended();
    }

    @Override
    public boolean getIpSmGwGuidanceIndicator() {
        return this.wrappedEvent.getIpSmGwGuidanceIndicator();
    }

    @Override
    public IMSI getImsi() {
        return this.wrappedEvent.getImsi();
    }

    @Override
    public boolean getT4TriggerIndicator() {
        return this.wrappedEvent.getT4TriggerIndicator();
    }

    @Override
    public boolean getSingleAttemptDelivery() {
        return this.wrappedEvent.getSingleAttemptDelivery();
    }

    @Override
    public CorrelationID getCorrelationID() {
        return this.wrappedEvent.getCorrelationID();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendRoutingInfoForSMRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
