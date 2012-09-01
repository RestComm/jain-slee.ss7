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

package org.mobicents.slee.resource.cap.events;

import org.mobicents.protocols.ss7.cap.api.CAPDialog;
import org.mobicents.protocols.ss7.cap.api.dialog.CAPComponentErrorReason;


/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class ProviderErrorComponent extends ComponentEvent {

	private static final String EVENT_TYPE_NAME = "ss7.cap.PROVIDER_ERROR_COMPONENT";

	protected CAPComponentErrorReason providerError;

	/**
	 * @param capDialogWrapper
	 * @param invokeId
	 */
	public ProviderErrorComponent(CAPDialog cAPDialog, Long invokeId, CAPComponentErrorReason providerError) {
		super(cAPDialog, invokeId, EVENT_TYPE_NAME);
		this.providerError = providerError;
	}

	public CAPComponentErrorReason getProviderError() {
		return this.providerError;
	}

	@Override
	public String toString() {
		return "ProviderErrorComponent [providerError=" + providerError + ", invokeId=" + invokeId
				+ ", capDialogWrapper=" + capDialogWrapper + "]";
	}

}
