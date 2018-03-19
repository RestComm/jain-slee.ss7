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

package org.restcomm.slee.resource.map.service.mobility.imei.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIu;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.restcomm.slee.resource.map.service.mobility.wrappers.MobilityMessageWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CheckImeiResponseWrapper extends MobilityMessageWrapper<CheckImeiResponse> implements CheckImeiResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.mobility.imei.CHECK_IMEI_RESPONSE";

	/**
	 * @param mAPDialog
	 */
	public CheckImeiResponseWrapper(MAPDialogMobilityWrapper mAPDialog, CheckImeiResponse req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public EquipmentStatus getEquipmentStatus() {
		return this.wrappedEvent.getEquipmentStatus();
	}

	public UESBIIu getBmuef() {
		return this.wrappedEvent.getBmuef();
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public String toString() {
		return "CheckImeiResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}
}
