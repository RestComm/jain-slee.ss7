/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2012, Telestax Inc and individual contributors
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

package org.restcomm.slee.resource.tcap.wrappers;

import org.restcomm.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.slee.resource.tcap.events.InvokeEvent;

/**
 * @author amit bhayani
 * 
 */
public class InvokeEventImpl extends ComponentEventImpl<Invoke> implements InvokeEvent {

	/**
	 * 
	 */
	public InvokeEventImpl(TCAPDialogWrapper dialogWrapper, Invoke wrappedInvoke) {
		super(dialogWrapper, wrappedInvoke);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#getInvokeClass()
	 */
	@Override
	public InvokeClass getInvokeClass() {
		return this.wrappedComponent.getInvokeClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#getLinkedId()
	 */
	@Override
	public Long getLinkedId() {
		return this.wrappedComponent.getLinkedId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#getOperationCode()
	 */
	@Override
	public OperationCode getOperationCode() {
		return this.wrappedComponent.getOperationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#getParameter()
	 */
	@Override
	public Parameter getParameter() {
		return this.wrappedComponent.getParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#getTimeout()
	 */
	@Override
	public long getTimeout() {
		return this.wrappedComponent.getTimeout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#setLinkedId(java.lang
	 * .Long)
	 */
	@Override
	public void setLinkedId(Long linkedId) {
		this.wrappedComponent.setLinkedId(linkedId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#setOperationCode(org
	 * .restcomm.protocols.ss7.tcap.asn.comp.OperationCode)
	 */
	@Override
	public void setOperationCode(OperationCode operationCode) {
		this.wrappedComponent.setOperationCode(operationCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#setParameter(org.restcomm
	 * .protocols.ss7.tcap.asn.comp.Parameter)
	 */
	@Override
	public void setParameter(Parameter parameter) {
		this.wrappedComponent.setParameter(parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Invoke#setTimeout(long)
	 */
	@Override
	public void setTimeout(long timeout) {
		this.wrappedComponent.setTimeout(timeout);
	}

	@Override
	public Invoke getLinkedInvoke() {
		return this.wrappedComponent.getLinkedInvoke();
	}

	@Override
	public void setLinkedInvoke(Invoke val) {
		this.wrappedComponent.setLinkedInvoke(val);
	}

}
