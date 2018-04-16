/**
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

package org.restcomm.protocols.ss7.sleetest;

import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.RolledBackContext;
import javax.slee.Sbb;
import javax.slee.SbbContext;
import javax.slee.facilities.Tracer;
import javax.slee.resource.ResourceAdaptorTypeID;

import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSRequest;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.slee.resource.map.MAPContextInterfaceFactory;
import org.restcomm.slee.resource.map.events.DialogDelimiter;
import org.mobicents.slee.SbbContextExt;

/**
 *
 * @author sergey vetyutnev
 */
public abstract class Ss7TestSbb implements Sbb {

    private final String loggerName = "ParentSbb";

    protected SbbContextExt sbbContext;
    protected Tracer logger;

    protected static final ResourceAdaptorTypeID mapRATypeID = new ResourceAdaptorTypeID("MAPResourceAdaptorType",
            "org.restcomm", "2.0");
    protected static final String mapRaLink = "MAPRA";
    protected MAPContextInterfaceFactory mapAcif;
    protected MAPProvider mapProvider;
    protected MAPParameterFactory mapParameterFactory;


    /** Creates a new instance of CallSbb */
    public Ss7TestSbb() {
    }

    public void setSbbContext(SbbContext sbbContext) {
        this.sbbContext = (SbbContextExt) sbbContext;
        this.logger = sbbContext.getTracer(this.loggerName);

        try {
            mapAcif = (MAPContextInterfaceFactory) this.sbbContext.getActivityContextInterfaceFactory(mapRATypeID);
            mapProvider = (MAPProvider) this.sbbContext.getResourceAdaptorInterface(mapRATypeID, mapRaLink);
            mapParameterFactory = mapProvider.getMAPParameterFactory();
        } catch (Exception ne) {
            logger.severe("Could not set SBB context:", ne);
        }
    }

    public void unsetSbbContext() {
        // clean RAs
        this.mapAcif = null;
        this.mapProvider = null;
        this.mapParameterFactory = null;

        // clean SLEE
        this.sbbContext = null;
        this.logger = null;
    }

    public void sbbCreate() throws CreateException {
    }

    public void sbbPostCreate() throws CreateException {
    }

    public void sbbActivate() {
    }

    public void sbbPassivate() {
    }

    public void sbbLoad() {
    }

    public void sbbStore() {
    }

    public void sbbRemove() {
    }

    public void sbbExceptionThrown(Exception exception, Object object, ActivityContextInterface activityContextInterface) {
    }

    public void sbbRolledBack(RolledBackContext rolledBackContext) {
    }


	public void onDialogRequest(org.restcomm.slee.resource.map.events.DialogRequest evt, ActivityContextInterface aci) {
        this.logger.info("New MAP Dialog. Received event MAPOpenInfo " + evt);
        this.logger.info("New MAP Dialog. Received MAPDialog=" + evt.getMAPDialog() + " LocalAddress=["
                + evt.getMAPDialog().getLocalAddress() + "] RemoteAddress=[" + evt.getMAPDialog().getRemoteAddress() + "]");
	}

	public void onProcessUnstructuredSSRequest(ProcessUnstructuredSSRequest evt, ActivityContextInterface aci) {
        this.logger.info(String.format("Received PROCESS_UNSTRUCTURED_SS_REQUEST_INDICATION=%s", evt));

        this.setProcessUnstructuredSSRequestInvokeId(evt.getInvokeId());
	}

    public void onDialogDelimiter(DialogDelimiter evt, ActivityContextInterface aci) {
        this.logger.info("Rx :  onDialogDelimiter " + evt);

        MAPDialogSupplementary dialog = (MAPDialogSupplementary) evt.getMAPDialog();

        try {
            USSDString ussdString = mapParameterFactory.createUSSDString("USSD response from TestSBB");
            CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(0x0f);
            dialog.addProcessUnstructuredSSResponse(this.getProcessUnstructuredSSRequestInvokeId(), cbsDataCodingScheme,
                    ussdString);
            dialog.close(false);

            this.logger.info("Sent a response back");
        } catch (Exception e) {
            logger.severe("Exception while trying to send MAP ErrorMessage", e);
        }
    }

    public abstract void setProcessUnstructuredSSRequestInvokeId(long processUnstructuredSSRequestInvokeId);

    public abstract long getProcessUnstructuredSSRequestInvokeId();

}
