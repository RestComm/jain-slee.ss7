/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 * 
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.slee.resource.tcap.wrappers;

import org.mobicents.protocols.ss7.tcap.api.tc.component.InvokeClass;
import org.mobicents.protocols.ss7.tcap.asn.comp.Invoke;
import org.mobicents.protocols.ss7.tcap.asn.comp.OperationCode;
import org.mobicents.protocols.ss7.tcap.asn.comp.Parameter;
import org.mobicents.slee.resource.tcap.events.InvokeEvent;

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
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#getInvokeClass()
	 */
	@Override
	public InvokeClass getInvokeClass() {
		return this.wrappedComponent.getInvokeClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#getLinkedId()
	 */
	@Override
	public Long getLinkedId() {
		return this.wrappedComponent.getLinkedId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#getOperationCode()
	 */
	@Override
	public OperationCode getOperationCode() {
		return this.wrappedComponent.getOperationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#getParameter()
	 */
	@Override
	public Parameter getParameter() {
		return this.wrappedComponent.getParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#getTimeout()
	 */
	@Override
	public long getTimeout() {
		return this.wrappedComponent.getTimeout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#setLinkedId(java.lang
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
	 * org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#setOperationCode(org
	 * .mobicents.protocols.ss7.tcap.asn.comp.OperationCode)
	 */
	@Override
	public void setOperationCode(OperationCode operationCode) {
		this.wrappedComponent.setOperationCode(operationCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#setParameter(org.mobicents
	 * .protocols.ss7.tcap.asn.comp.Parameter)
	 */
	@Override
	public void setParameter(Parameter parameter) {
		this.wrappedComponent.setParameter(parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Invoke#setTimeout(long)
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
