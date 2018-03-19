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
package org.restcomm.slee.resource.tcap.events;

/**
 * @author amit bhayani
 * 
 */
public interface TCAPEvent {
	public static final String EVENT_TYPE_NAME_DIALOG_UNI = "ss7.tcap.DIALOG_UNI";
	public static final String EVENT_TYPE_NAME_DIALOG_BEGIN = "ss7.tcap.DIALOG_BEGIN";
	public static final String EVENT_TYPE_NAME_DIALOG_CONTINUE = "ss7.tcap.DIALOG_CONTINUE";
	public static final String EVENT_TYPE_NAME_DIALOG_END = "ss7.tcap.DIALOG_END";
	public static final String EVENT_TYPE_NAME_DIALOG_USERABORT = "ss7.tcap.DIALOG_USERABORT";
	public static final String EVENT_TYPE_NAME_DIALOG_PROVIDERABORT = "ss7.tcap.DIALOG_PROVIDERABORT";
	public static final String EVENT_TYPE_NAME_DIALOG_NOTICE = "ss7.tcap.DIALOG_NOTICE";
	public static final String EVENT_TYPE_NAME_DIALOG_RELEASED = "ss7.tcap.DIALOG_RELEASED";
	public static final String EVENT_TYPE_NAME_DIALOG_TIMEOUT = "ss7.tcap.DIALOG_TIMEOUT";
	public static final String EVENT_TYPE_NAME_DIALOG_DELIMITER = "ss7.tcap.DIALOG_DELIMITER";

	public static final String EVENT_TYPE_NAME_COMPONENT_INVOKE_TIMEOUT = "ss7.tcap.COMPONENT_INVOKE_TIMEOUT";

	public static final String EVENT_TYPE_NAME_COMPONENT_INVOKE = "ss7.tcap.COMPONENT_INVOKE";
	public static final String EVENT_TYPE_NAME_COMPONENT_REJECT = "ss7.tcap.COMPONENT_REJECT";
	public static final String EVENT_TYPE_NAME_COMPONENT_RETURNRESULT = "ss7.tcap.COMPONENT_RETURNRESULT";
	public static final String EVENT_TYPE_NAME_COMPONENT_RETURNRESULT_LAST = "ss7.tcap.COMPONENT_RETURNRESULT_LAST";
	public static final String EVENT_TYPE_NAME_COMPONENT_RETURNERROR = "ss7.tcap.COMPONENT_RETURNERROR";
}
