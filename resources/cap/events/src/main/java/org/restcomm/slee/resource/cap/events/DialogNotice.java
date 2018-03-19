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

package org.restcomm.slee.resource.cap.events;

import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPNoticeProblemDiagnostic;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class DialogNotice extends CAPEvent {

	private static final String EVENT_TYPE_NAME = "ss7.cap.DIALOG_NOTICE";

	private final CAPNoticeProblemDiagnostic noticeProblemDiagnostic;

	/**
	 * @param capDialogWrapper
	 */
	public DialogNotice(CAPDialog capDialogWrapper, CAPNoticeProblemDiagnostic noticeProblemDiagnostic) {
		super(capDialogWrapper, EVENT_TYPE_NAME, null);
		this.noticeProblemDiagnostic = noticeProblemDiagnostic;
	}

	public CAPNoticeProblemDiagnostic getNoticeProblemDiagnostic() {
		return noticeProblemDiagnostic;
	}

	@Override
	public String toString() {
		return "DialogNotice [noticeProblemDiagnostic=" + noticeProblemDiagnostic + ", " + this.capDialogWrapper + "]";
	}

}
