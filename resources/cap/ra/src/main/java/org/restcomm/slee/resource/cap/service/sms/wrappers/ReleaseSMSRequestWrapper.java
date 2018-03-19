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

package org.restcomm.slee.resource.cap.service.sms.wrappers;

import org.restcomm.protocols.ss7.cap.api.service.sms.ReleaseSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.RPCause;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ReleaseSMSRequestWrapper extends SmsMessageWrapper<ReleaseSMSRequest> implements ReleaseSMSRequest {

    private static final String EVENT_TYPE_NAME = "ss7.cap.service.sms.RELEASE_SMS_REQUEST";

    public ReleaseSMSRequestWrapper(CAPDialogSmsWrapper capDialog, ReleaseSMSRequest req) {
        super(capDialog, EVENT_TYPE_NAME, req);
    }

    @Override
    public RPCause getRPCause() {
        return this.wrappedEvent.getRPCause();
    }

    @Override
    public String toString() {
        return "ConnectSMSRequestWrapper [wrapped=" + this.wrappedEvent + "]";
    }

}
