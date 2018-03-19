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

package org.restcomm.slee.resource.cap.wrappers;

import org.restcomm.protocols.ss7.cap.api.CAPMessage;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.slee.resource.cap.events.CAPEvent;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CAPMessageWrapper<T extends CAPMessage> extends CAPEvent<T> implements CAPMessage {

	

	public CAPMessageWrapper(CAPDialogWrapper capDialogWrapper, String eventTypeName, T wrappedEvent) {
		super(capDialogWrapper, eventTypeName, wrappedEvent);
	}

	public long getInvokeId() {
		return this.wrappedEvent.getInvokeId();
	}

	public CAPMessageType getMessageType() {
		return this.wrappedEvent.getMessageType();
	}

	public int getOperationCode() {
		return this.wrappedEvent.getOperationCode();
	}

	public void setInvokeId(long arg0) {
		this.wrappedEvent.setInvokeId(arg0);
	}

}
