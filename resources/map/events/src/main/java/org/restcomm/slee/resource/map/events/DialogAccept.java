/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
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

/**
 * 
 */
package org.restcomm.slee.resource.map.events;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * @author amit bhayani
 * 
 */
public class DialogAccept extends MAPEvent {

	private final MAPExtensionContainer extensionContainer;

	private static final String EVENT_TYPE_NAME = "ss7.map.DIALOG_ACCEPT";

	/**
	 * @param mapDialogWrapper
	 */
	public DialogAccept(MAPDialog mapDialogWrapper, MAPExtensionContainer extensionContainer) {
		super(mapDialogWrapper, EVENT_TYPE_NAME, null);
		this.extensionContainer = extensionContainer;
	}

	public MAPExtensionContainer getExtensionContainer() {
		return extensionContainer;
	}

	@Override
	public String toString() {
		return "DialogAccept [extensionContainer=" + extensionContainer + ", mapDialogWrapper=" + mapDialogWrapper
				+ "]";
	}
}
