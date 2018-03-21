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
package org.restcomm.slee.resource.tcap.wrappers;

import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.Return;
import org.restcomm.slee.resource.tcap.events.ReturnEvent;

/**
 * @author abhayani
 * 
 */
public class ReturnEventImpl<T extends Return> extends ComponentEventImpl<T> implements ReturnEvent {

	/**
	 * 
	 */
	public ReturnEventImpl(TCAPDialogWrapper dialogWrapper, T wrappedReturn) {
		super(dialogWrapper, wrappedReturn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#getOperationCode()
	 */
	@Override
	public OperationCode getOperationCode() {
		return this.wrappedComponent.getOperationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.Return#getParameter()
	 */
	@Override
	public Parameter getParameter() {
		return this.wrappedComponent.getParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.Return#setOperationCode(org
	 * .restcomm.protocols.ss7.tcap.asn.comp.OperationCode)
	 */
	@Override
	public void setOperationCode(OperationCode opCode) {
		this.wrappedComponent.setOperationCode(opCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.Return#setParameter(org.restcomm
	 * .protocols.ss7.tcap.asn.comp.Parameter)
	 */
	@Override
	public void setParameter(Parameter parameter) {
		this.wrappedComponent.setParameter(parameter);
	}
}
