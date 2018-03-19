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

package org.restcomm.slee.resource.cap.service.gprs.wrappers;

import org.restcomm.protocols.ss7.cap.api.primitives.TimerID;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ResetTimerGPRSRequest;

/**
 * 
 * @author Lasith Waruna Perera
 * 
 */
public class ResetTimerGPRSRequestWrapper extends GprsMessageWrapper<ResetTimerGPRSRequest> 
implements ResetTimerGPRSRequest{
	
	private static final String EVENT_TYPE_NAME = "ss7.cap.service.gprs.RESET_TIMER_GPRS_REQUEST";
	
	public ResetTimerGPRSRequestWrapper(CAPDialogGprsWrapper capDialog, ResetTimerGPRSRequest req) {
		super(capDialog, EVENT_TYPE_NAME, req);
	}
	
	@Override
	public TimerID getTimerID() {
		return this.wrappedEvent.getTimerID();
	}

	@Override
	public int getTimerValue() {
		return this.wrappedEvent.getTimerValue();
	}

	@Override
	public String toString() {
		return "ResetTimerGPRSRequestWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
