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

import org.restcomm.protocols.ss7.tcap.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError;
import org.restcomm.slee.resource.tcap.events.ReturnErrorEvent;

/**
 * @author abhayani
 * 
 */
public class ReturnErrorEventImpl extends ComponentEventImpl<ReturnError> implements ReturnErrorEvent {

	/**
	 * 
	 */
	public ReturnErrorEventImpl(TCAPDialogWrapper dialogWrapper, ReturnError wrappedReturnError) {
		super(dialogWrapper, wrappedReturnError);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError#getErrorCode()
	 */
	@Override
	public ErrorCode getErrorCode() {
		return this.wrappedComponent.getErrorCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError#getParameter()
	 */
	@Override
	public Parameter getParameter() {
		return this.wrappedComponent.getParameter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError#setErrorCode(org
	 * .restcomm.protocols.ss7.tcap.asn.comp.ErrorCode)
	 */
	@Override
	public void setErrorCode(ErrorCode errorCode) {
		this.wrappedComponent.setErrorCode(errorCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError#setParameter(org
	 * .restcomm.protocols.ss7.tcap.asn.comp.Parameter)
	 */
	@Override
	public void setParameter(Parameter parameter) {
		this.wrappedComponent.setParameter(parameter);
	}

}
