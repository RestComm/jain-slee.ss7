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

package org.restcomm.slee.resource.map.service.callhandling.wrappers;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberResponse;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ProvideRoamingNumberResponseWrapper extends CallHandlingMessageWrapper<ProvideRoamingNumberResponse> implements ProvideRoamingNumberResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.callhandling.PROVIDE_ROAMING_NUMBER_RESPONSE";

	/**
	 * @param mapDialog
	 */
	public ProvideRoamingNumberResponseWrapper(MAPDialogCallHandlingWrapper mapDialog, ProvideRoamingNumberResponse req) {
		super(mapDialog, EVENT_TYPE_NAME, req);
	}

	@Override
	public ISDNAddressString getRoamingNumber() {
		return this.wrappedEvent.getRoamingNumber();
	}

	@Override
	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	@Override
	public boolean getReleaseResourcesSupported() {
		return this.wrappedEvent.getReleaseResourcesSupported();
	}

	@Override
	public ISDNAddressString getVmscAddress() {
		return this.wrappedEvent.getVmscAddress();
	}

	@Override
	public long getMapProtocolVersion() {
		return this.wrappedEvent.getMapProtocolVersion();
	}

	@Override
	public String toString() {
		return "ProvideRoamingNumberResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
