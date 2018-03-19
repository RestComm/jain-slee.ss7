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

package org.restcomm.slee.resource.cap;

import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.slee.Address;
import javax.slee.AddressPlan;
import javax.slee.SLEEException;
import javax.slee.facilities.Tracer;
import javax.slee.resource.ActivityAlreadyExistsException;
import javax.slee.resource.ActivityFlags;
import javax.slee.resource.ActivityHandle;
import javax.slee.resource.ActivityIsEndingException;
import javax.slee.resource.ConfigProperties;
import javax.slee.resource.EventFlags;
import javax.slee.resource.FailureReason;
import javax.slee.resource.FireEventException;
import javax.slee.resource.FireableEventType;
import javax.slee.resource.IllegalEventException;
import javax.slee.resource.InvalidConfigurationException;
import javax.slee.resource.Marshaler;
import javax.slee.resource.ReceivableService;
import javax.slee.resource.ResourceAdaptor;
import javax.slee.resource.ResourceAdaptorContext;
import javax.slee.resource.SleeEndpoint;
import javax.slee.resource.StartActivityException;
import javax.slee.resource.UnrecognizedActivityHandleException;

import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPDialogListener;
import org.restcomm.protocols.ss7.cap.api.CAPMessage;
import org.restcomm.protocols.ss7.cap.api.CAPProvider;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPDialogState;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGeneralAbortReason;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPGprsReferenceNumber;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPNoticeProblemDiagnostic;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPUserAbortReason;
import org.restcomm.protocols.ss7.cap.api.errors.CAPErrorMessage;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.*;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ActivityTestGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ActivityTestGPRSResponse;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ApplyChargingGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ApplyChargingReportGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ApplyChargingReportGPRSResponse;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPDialogGprs;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CAPServiceGprsListener;
import org.restcomm.protocols.ss7.cap.api.service.gprs.CancelGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ConnectGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ContinueGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.EntityReleasedGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.EntityReleasedGPRSResponse;
import org.restcomm.protocols.ss7.cap.api.service.gprs.EventReportGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.EventReportGPRSResponse;
import org.restcomm.protocols.ss7.cap.api.service.gprs.FurnishChargingInformationGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.InitialDpGprsRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ReleaseGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.RequestReportGPRSEventRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ResetTimerGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.SendChargingInformationGPRSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPDialogSms;
import org.restcomm.protocols.ss7.cap.api.service.sms.CAPServiceSmsListener;
import org.restcomm.protocols.ss7.cap.api.service.sms.ConnectSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.ContinueSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.EventReportSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.FurnishChargingInformationSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.InitialDPSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.ReleaseSMSRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.RequestReportSMSEventRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.ResetTimerSMSRequest;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.slee.resource.cap.events.CAPEvent;
import org.restcomm.slee.resource.cap.events.DialogAccept;
import org.restcomm.slee.resource.cap.events.DialogClose;
import org.restcomm.slee.resource.cap.events.DialogDelimiter;
import org.restcomm.slee.resource.cap.events.DialogNotice;
import org.restcomm.slee.resource.cap.events.DialogProviderAbort;
import org.restcomm.slee.resource.cap.events.DialogRelease;
import org.restcomm.slee.resource.cap.events.DialogRequest;
import org.restcomm.slee.resource.cap.events.DialogTimeout;
import org.restcomm.slee.resource.cap.events.DialogUserAbort;
import org.restcomm.slee.resource.cap.events.ErrorComponent;
import org.restcomm.slee.resource.cap.events.InvokeTimeout;
import org.restcomm.slee.resource.cap.events.RejectComponent;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ActivityTestRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ActivityTestResponseWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ApplyChargingReportRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ApplyChargingRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.AssistRequestInstructionsRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CAPDialogCircuitSwitchedCallWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CallGapRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CallInformationReportRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CallInformationRequestRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CancelRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.CollectInformationRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ConnectRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ConnectToResourceRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ContinueRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ContinueWithArgumentRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.DisconnectForwardConnectionRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.DisconnectForwardConnectionWithArgumentRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.DisconnectLegRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.DisconnectLegResponseWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.EstablishTemporaryConnectionRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.EventReportBCSMRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.FurnishChargingInformationRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.InitialDPRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.InitiateCallAttemptRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.InitiateCallAttemptResponseWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.MoveLegRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.MoveLegResponseWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.PlayAnnouncementRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.PromptAndCollectUserInformationRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.PromptAndCollectUserInformationResponseWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ReleaseCallRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.RequestReportBCSMEventRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.ResetTimerRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.SendChargingInformationRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.SpecializedResourceReportRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.SplitLegRequestWrapper;
import org.restcomm.slee.resource.cap.service.circuitSwitchedCall.wrappers.SplitLegResponseWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ActivityTestGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ActivityTestGPRSResponseWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ApplyChargingGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ApplyChargingReportGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ApplyChargingReportGPRSResponseWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.CAPDialogGprsWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.CancelGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ConnectGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ContinueGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.EntityReleasedGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.EntityReleasedGPRSResponseWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.EventReportGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.EventReportGPRSResponseWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.FurnishChargingInformationGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.InitialDpGprsRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ReleaseGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.RequestReportGPRSEventRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.ResetTimerGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.gprs.wrappers.SendChargingInformationGPRSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.CAPDialogSmsWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.ConnectSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.ContinueSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.EventReportSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.FurnishChargingInformationSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.InitialDPSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.ReleaseSMSRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.RequestReportSMSEventRequestWrapper;
import org.restcomm.slee.resource.cap.service.sms.wrappers.ResetTimerSMSRequestWrapper;
import org.restcomm.slee.resource.cap.wrappers.CAPDialogWrapper;
import org.restcomm.slee.resource.cap.wrappers.CAPProviderWrapper;

import java.lang.management.ManagementFactory;

/**
 * 
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 * 
 */
public class CAPResourceAdaptor implements ResourceAdaptor, CAPDialogListener, CAPServiceCircuitSwitchedCallListener,
		CAPServiceGprsListener, CAPServiceSmsListener {
	/**
	 * for all events we are interested in knowing when the event failed to be
	 * processed
	 */
	public static final int DEFAULT_EVENT_FLAGS = EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK;

	private static final int ACTIVITY_FLAGS = ActivityFlags.REQUEST_ENDED_CALLBACK;// .NO_FLAGS;

	/**
	 * This is local proxy of provider.
	 */
	protected CAPProviderWrapper capProvider = null;
	protected CAPProvider realProvider = null; // so we dont have to "get"
	private Tracer tracer;
	private transient SleeEndpoint sleeEndpoint = null;

	private ResourceAdaptorContext resourceAdaptorContext;
	private CAPResourceAdaptorStatisticsUsageParameters defaultUsageParameters;

	private EventIDCache eventIdCache = null;

	/**
	 * tells the RA if an event with a specified ID should be filtered or not
	 */
	private final EventIDFilter eventIDFilter = new EventIDFilter();

	// ////////////////////////////
	// Configuration parameters //
	// ////////////////////////////
	private static final String CONF_CAP_JNDI = "capJndi";

	private String capJndi = null;
	private transient static final Address address = new Address(AddressPlan.IP, "localhost");

	public CAPResourceAdaptor() {
		this.capProvider = new CAPProviderWrapper(this);
	}

	// ////////////////
	// RA callbacks //
	// ////////////////
	public void activityEnded(ActivityHandle activityHandle) {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("Activity with handle " + activityHandle + " ended");
		}
		CAPDialogActivityHandle mdah = (CAPDialogActivityHandle) activityHandle;
		final CAPDialogWrapper dw = mdah.getActivity();
		mdah.setActivity(null);

		if (dw != null) {
			dw.clear();
		}
	}

	public void activityUnreferenced(ActivityHandle arg0) {
		// TODO Auto-generated method stub

	}

	public void administrativeRemove(ActivityHandle arg0) {
		// TODO Auto-generated method stub

	}

	public void eventProcessingFailed(ActivityHandle handle, FireableEventType eventType, Object event,
			Address address, ReceivableService service, int flags, FailureReason reason) {
		if (tracer.isFineEnabled())
			tracer.fine("eventProcessingFailed:" + eventType + ":" + handle);
		// used to inform the resource adaptor object that the specified Event
		// could not be processed successfully by the SLEE.

		if (eventType.getEventType().getName().equals(DialogTimeout.EVENT_TYPE_NAME)) {
			CAPDialogActivityHandle dah = (CAPDialogActivityHandle) handle;
			CAPDialogWrapper dw = dah.getActivity();
			if (dw != null)
				releaseDialog(dw);
		}
	}

	public void eventProcessingSuccessful(ActivityHandle handle, FireableEventType eventType, Object event,
			Address address, ReceivableService service, int flags) {

		if (tracer.isFineEnabled())
			tracer.fine("eventProcessingSuccessful:" + eventType + ":" + handle);
		// used to inform the resource adaptor object that the specified Event
		// was processed successfully by the SLEE.

		if (eventType.getEventType().getName().equals(DialogTimeout.EVENT_TYPE_NAME)) {
			CAPDialogActivityHandle dah = (CAPDialogActivityHandle) handle;
			CAPDialogWrapper dw = dah.getActivity();
			if (dw != null && !dw.checkDialogTimeoutProcKeeped())
				releaseDialog(dw);
		}
	}

	public void eventUnreferenced(ActivityHandle arg0, FireableEventType arg1, Object arg2, Address arg3,
			ReceivableService arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	private void releaseDialog(CAPDialogWrapper dw) {
		dw.release();
	}

	public Object getActivity(ActivityHandle handle) {
		return ((CAPDialogActivityHandle) handle).getActivity();
	}

	public ActivityHandle getActivityHandle(Object activity) {
		if (activity instanceof CAPDialogWrapper) {
			final CAPDialogWrapper wrapper = ((CAPDialogWrapper) activity);
			if (wrapper.getRa() == this) {
				return wrapper.getActivityHandle();
			}
		}

		return null;
	}

	public Marshaler getMarshaler() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getResourceAdaptorInterface(String arg0) {
		return this.capProvider;
	}

	public void queryLiveness(ActivityHandle activityHandle) {
		final CAPDialogActivityHandle handle = ((CAPDialogActivityHandle) activityHandle);
		final CAPDialogWrapper capDialog = handle.getActivity();
		if (capDialog == null || capDialog.getWrappedDialog() == null
				|| capDialog.getState() == CAPDialogState.Expunged) {
			sleeEndpoint.endActivity(handle);
		}
	}

	public void raActive() {

		try {
			//InitialContext ic = new InitialContext();
			//this.realProvider = (CAPProvider) ic.lookup(this.capJndi);
			//tracer.info("Successfully connected to CAP service[" + this.capJndi + "]");

            ObjectName objectName = new ObjectName("org.restcomm.ss7:service=CAPSS7Service");
            Object object = null;
            if (ManagementFactory.getPlatformMBeanServer().isRegistered(objectName)) {
                // trying to get via MBeanServer
                object = ManagementFactory.getPlatformMBeanServer().getAttribute(objectName, "Stack");
				if (tracer.isInfoEnabled()) {
					tracer.info("Trying to get via MBeanServer: " + objectName + ", object: " + object);
				}
            } else {
                // trying to get via JNDI
                InitialContext ic = new InitialContext();
                object = ic.lookup(this.capJndi);
				if (tracer.isInfoEnabled()) {
					tracer.info("Trying to get via JNDI: " + this.capJndi + ", object: " + object);
				}
            }

			if (object instanceof CAPProvider) {
				this.realProvider = (CAPProvider) object;
				if (tracer.isInfoEnabled()) {
					tracer.info("Successfully connected to CAP service[" +
							this.realProvider.getClass().getCanonicalName() + "]");
				}
            } else {
				if (tracer.isSevereEnabled()) {
					tracer.severe("Failed of connecting to CAP service[org.restcomm.ss7:service=CAPSS7Service]");
				}
			}

			this.realProvider.addCAPDialogListener(this);

			this.realProvider.getCAPServiceCircuitSwitchedCall().addCAPServiceListener(this);
			this.realProvider.getCAPServiceGprs().addCAPServiceListener(this);
			this.realProvider.getCAPServiceSms().addCAPServiceListener(this);

			this.sleeEndpoint = resourceAdaptorContext.getSleeEndpoint();

			this.realProvider.getCAPServiceCircuitSwitchedCall().acivate();
			this.realProvider.getCAPServiceGprs().acivate();
			this.realProvider.getCAPServiceSms().acivate();

			this.capProvider.setWrappedProvider(this.realProvider);
		} catch (Exception e) {
			this.tracer.severe("Failed to activate CAP RA ", e);
		}
	}

	public void raConfigurationUpdate(ConfigProperties properties) {
		raConfigure(properties);
	}

	public void raConfigure(ConfigProperties properties) {
		try {
			if (tracer.isInfoEnabled()) {
				tracer.info("Configuring CAP RA: " + this.resourceAdaptorContext.getEntityName());
			}
			this.capJndi = (String) properties.getProperty(CONF_CAP_JNDI).getValue();
		} catch (Exception e) {
			tracer.severe("Configuring of CAP RA failed ", e);
		}
	}

	public void raInactive() {
		this.realProvider.getCAPServiceCircuitSwitchedCall().deactivate();
		this.realProvider.getCAPServiceGprs().deactivate();
		this.realProvider.getCAPServiceSms().deactivate();

		this.realProvider.getCAPServiceCircuitSwitchedCall().removeCAPServiceListener(this);
		this.realProvider.getCAPServiceGprs().removeCAPServiceListener(this);
		this.realProvider.getCAPServiceSms().removeCAPServiceListener(this);

		this.realProvider.removeCAPDialogListener(this);
	}

	public void raStopping() {
		// TODO Auto-generated method stub

	}

	public void raUnconfigure() {
		this.capJndi = null;
	}

	public void raVerifyConfiguration(ConfigProperties properties) throws InvalidConfigurationException {
		try {

			if (tracer.isInfoEnabled()) {
				tracer.info("Verifying configuring CAP RA: " + this.resourceAdaptorContext.getEntityName());
			}

			this.capJndi = (String) properties.getProperty(CONF_CAP_JNDI).getValue();
			if (this.capJndi == null) {
				throw new InvalidConfigurationException("CAP JNDI lookup name cannot be null");
			}

		} catch (Exception e) {
			throw new InvalidConfigurationException("Failed to test configuration options!", e);
		}
	}

	public void serviceActive(ReceivableService receivableService) {
		eventIDFilter.serviceActive(receivableService);
	}

	public void serviceInactive(ReceivableService receivableService) {
		eventIDFilter.serviceInactive(receivableService);
	}

	public void serviceStopping(ReceivableService receivableService) {
		eventIDFilter.serviceStopping(receivableService);
	}

	public void setResourceAdaptorContext(ResourceAdaptorContext raContext) {
		this.resourceAdaptorContext = raContext;
		this.tracer = resourceAdaptorContext.getTracer(CAPResourceAdaptor.class.getSimpleName());

		this.eventIdCache = new EventIDCache(this.tracer);

		try {
			this.defaultUsageParameters =
					(CAPResourceAdaptorStatisticsUsageParameters) raContext.getDefaultUsageParameterSet();

			tracer.info("defaultUsageParameters: " + this.defaultUsageParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unsetResourceAdaptorContext() {
		this.resourceAdaptorContext = null;
	}

	// //////////////////
	// Helper methods //
	// //////////////////
	public void startActivity(CAPDialogWrapper capDialogWrapper) throws ActivityAlreadyExistsException,
			NullPointerException, IllegalStateException, SLEEException, StartActivityException {
		this.sleeEndpoint.startActivity(capDialogWrapper.getActivityHandle(), capDialogWrapper, ACTIVITY_FLAGS);
	}

	public void startSuspendedActivity(CAPDialogWrapper capDialogWrapper) throws ActivityAlreadyExistsException,
			NullPointerException, IllegalStateException, SLEEException, StartActivityException {
		this.sleeEndpoint.startActivitySuspended(capDialogWrapper.getActivityHandle(), capDialogWrapper,
				ActivityFlags.REQUEST_ENDED_CALLBACK);
	}

	/**
	 * Private methods
	 */

	/**
	 * Filters the even and returns true if the event was fired
	 * 
	 * @param eventName
	 * @param handle
	 * @param event
	 * @param flags
	 * @return
	 */
	private boolean fireEvent(String eventName, ActivityHandle handle, Object event, int flags) {

		FireableEventType eventID = eventIdCache.getEventId(this.resourceAdaptorContext.getEventLookupFacility(),
				eventName);

		if (eventIDFilter.filterEvent(eventID)) {
			if (tracer.isFineEnabled()) {
				tracer.fine("Event " + (eventID == null ? "null" : eventID.getEventType()) + " filtered");
			}
			return false;
		} else {

			try {
				sleeEndpoint.fireEvent(handle, eventID, event, address, null, flags);
			} catch (UnrecognizedActivityHandleException e) {
				this.tracer.severe("Error while firing event", e);
			} catch (IllegalEventException e) {
				this.tracer.severe("Error while firing event", e);
			} catch (ActivityIsEndingException e) {
				this.tracer.severe("Error while firing event", e);
			} catch (NullPointerException e) {
				this.tracer.severe("Error while firing event", e);
			} catch (SLEEException e) {
				this.tracer.severe("Error while firing event", e);
			} catch (FireEventException e) {
				this.tracer.severe("Error while firing event", e);
			}
			return true;
		}
	}

	// /////////////////
	// Event helpers //
	// /////////////////

	private CAPDialogActivityHandle onEvent(String eventName, CAPDialogWrapper dw, CAPEvent event) {
		return onEvent(eventName, dw, event, EventFlags.NO_FLAGS);
	}

	private CAPDialogActivityHandle onEvent(String eventName, CAPDialogWrapper dw, CAPEvent event, int flags) {
		if (dw == null) {
			this.tracer.severe(String.format("Firing %s but CAPDialogWrapper userObject is null", eventName));
			return null;
		}

		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Firing %s for DialogId=%d", eventName, dw.getWrappedDialog()
					.getLocalDialogId()));
		}

		this.fireEvent(eventName, dw.getActivityHandle(), event, flags);
		return dw.getActivityHandle();
	}

	public void onDialogAccept(CAPDialog capDialog, CAPGprsReferenceNumber capGprsReferenceNumber) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogAccept dialogAccept = new DialogAccept(capDialogWrapper, capGprsReferenceNumber);
		onEvent(dialogAccept.getEventTypeName(), capDialogWrapper, dialogAccept);
	}

	public void onDialogClose(CAPDialog capDialog) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogClose dialogClose = new DialogClose(capDialogWrapper);
		CAPDialogActivityHandle handle = onEvent(dialogClose.getEventTypeName(), capDialogWrapper, dialogClose);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);
	}

	public void onDialogDelimiter(CAPDialog capDialog) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogDelimiter dialogDelimiter = new DialogDelimiter(capDialogWrapper);
		onEvent(dialogDelimiter.getEventTypeName(), capDialogWrapper, dialogDelimiter);
	}

	public void onDialogNotice(CAPDialog capDialog, CAPNoticeProblemDiagnostic noticeProblemDiagnostic) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogNotice dialogNotice = new DialogNotice(capDialogWrapper, noticeProblemDiagnostic);
		onEvent(dialogNotice.getEventTypeName(), capDialogWrapper, dialogNotice);
	}

	public void onDialogProviderAbort(CAPDialog capDialog, PAbortCauseType abortCause) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogProviderAbort dialogProviderAbort = new DialogProviderAbort(capDialogWrapper, abortCause);
		CAPDialogActivityHandle handle = onEvent(dialogProviderAbort.getEventTypeName(), capDialogWrapper,
				dialogProviderAbort);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);
	}

	public void onDialogUserAbort(CAPDialog capDialog, CAPGeneralAbortReason generalReason,
			CAPUserAbortReason userReason) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogUserAbort dialogUserAbort = new DialogUserAbort(capDialogWrapper, generalReason, userReason);
		CAPDialogActivityHandle handle = onEvent(dialogUserAbort.getEventTypeName(), capDialogWrapper, dialogUserAbort);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);

	}

	private void handleDialogRequest(CAPDialog capDialog, CAPGprsReferenceNumber capGprsReferenceNumber) {
		try {

			if (this.tracer.isFineEnabled()) {
				this.tracer.fine(String.format("Received onDialogRequest id=%d ", capDialog.getLocalDialogId()));
			}

			CAPDialogActivityHandle activityHandle = new CAPDialogActivityHandle(capDialog.getLocalDialogId());
			CAPDialogWrapper capDialogWrapper = null;

			if (capDialog instanceof CAPDialogCircuitSwitchedCall) {
				this.defaultUsageParameters.incrementCalls(1L);
				capDialogWrapper = new CAPDialogCircuitSwitchedCallWrapper((CAPDialogCircuitSwitchedCall) capDialog,
						activityHandle, this);
			} else if (capDialog instanceof CAPDialogGprs) {
				capDialogWrapper = new CAPDialogGprsWrapper((CAPDialogGprs) capDialog, activityHandle, this);
			} else if (capDialog instanceof CAPDialogSms) {
				this.defaultUsageParameters.incrementMessages(1L);
				capDialogWrapper = new CAPDialogSmsWrapper((CAPDialogSms) capDialog, activityHandle, this);
			} else {
				this.tracer.severe(String.format("Received onDialogRequest id=%d for unknown CAPDialog class=%s",
						capDialog.getLocalDialogId(), capDialog.getClass().getName()));
				return;
			}

			DialogRequest event = new DialogRequest(capDialogWrapper, capGprsReferenceNumber);
			capDialog.setUserObject(capDialogWrapper);
			this.startActivity(capDialogWrapper);
			this.fireEvent(event.getEventTypeName(), capDialogWrapper.getActivityHandle(), event, EventFlags.NO_FLAGS);

		} catch (Exception e) {
			this.tracer.severe(String.format(
					"Exception when trying to fire event DIALOG_REQUEST for received DialogRequest=%s ", capDialog), e);
		}
	}

	public void onDialogRequest(CAPDialog capDialog, CAPGprsReferenceNumber capGprsReferenceNumber) {
		this.handleDialogRequest(capDialog, capGprsReferenceNumber);
	}

	public void onDialogRelease(CAPDialog capDialog) {
		try {

			CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
			DialogRelease dialogRelease = new DialogRelease(capDialogWrapper);
			CAPDialogActivityHandle handle = onEvent(dialogRelease.getEventTypeName(), capDialogWrapper, dialogRelease);

			// End Activity
			this.sleeEndpoint.endActivity(handle);
		} catch (Exception e) {
			this.tracer.severe(String.format(
					"onDialogRelease : Exception while trying to end activity for CAPDialog=%s", capDialog), e);
		}
	}

	public void onDialogTimeout(CAPDialog capDialog) {

		// TODO: done like that, since we want to process dialog callbacks
		// before we fire event.
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Rx : onDialogTimeout for DialogId=%d", capDialog.getLocalDialogId()));
		}

		capDialog.keepAlive();

		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		DialogTimeout dt = new DialogTimeout(capDialogWrapper);

		if (capDialogWrapper == null) {
			this.tracer
					.severe(String.format("Firing %s but CAPDialogWrapper userObject is null", dt.getEventTypeName()));
			capDialog.release();
			return;
		}

		capDialogWrapper.startDialogTimeoutProc();

		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Firing %s for DialogId=%d", dt.getEventTypeName(), capDialogWrapper
					.getWrappedDialog().getLocalDialogId()));
		}

		boolean fired = fireEvent(dt.getEventTypeName(), capDialogWrapper.getActivityHandle(), dt,
				(EventFlags.REQUEST_PROCESSING_SUCCESSFUL_CALLBACK | EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK));

		if (!fired) {
			// If event filtered out, lets release Dialog
			capDialogWrapper.release();
		}

	}

	// ///////////////////////
	// Component callbacks //
	// ///////////////////////

	public void onErrorComponent(CAPDialog capDialog, Long invokeId, CAPErrorMessage capErrorMessage) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		ErrorComponent errorComponent = new ErrorComponent(capDialogWrapper, invokeId, capErrorMessage);
		onEvent(errorComponent.getEventTypeName(), capDialogWrapper, errorComponent);
	}

	public void onRejectComponent(CAPDialog capDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		RejectComponent rejectComponent = new RejectComponent(capDialogWrapper, invokeId, problem, isLocalOriginated);
		onEvent(rejectComponent.getEventTypeName(), capDialogWrapper, rejectComponent);
	}

	public void onInvokeTimeout(CAPDialog capDialog, Long invokeId) {
		CAPDialogWrapper capDialogWrapper = (CAPDialogWrapper) capDialog.getUserObject();
		InvokeTimeout invokeTimeout = new InvokeTimeout(capDialogWrapper, invokeId);
		onEvent(invokeTimeout.getEventTypeName(), capDialogWrapper, invokeTimeout);
	}

	public void onCAPMessage(CAPMessage capMessage) {
		// TODO Auto-generated method stub
	}

	// ///////////////////////
	// Service: CircuitSwitchedCall
	// ///////////////////////

	@Override
	public void onInitialDPRequest(InitialDPRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		InitialDPRequestWrapper event = new InitialDPRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onRequestReportBCSMEventRequest(RequestReportBCSMEventRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		RequestReportBCSMEventRequestWrapper event = new RequestReportBCSMEventRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onApplyChargingRequest(ApplyChargingRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ApplyChargingRequestWrapper event = new ApplyChargingRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onEventReportBCSMRequest(EventReportBCSMRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		EventReportBCSMRequestWrapper event = new EventReportBCSMRequestWrapper(capDialogCircuitSwitchedCallWrapper,
				ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onContinueRequest(ContinueRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ContinueRequestWrapper event = new ContinueRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onApplyChargingReportRequest(ApplyChargingReportRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ApplyChargingReportRequestWrapper event = new ApplyChargingReportRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onReleaseCallRequest(ReleaseCallRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ReleaseCallRequestWrapper event = new ReleaseCallRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onConnectRequest(ConnectRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ConnectRequestWrapper event = new ConnectRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onCallInformationRequestRequest(CallInformationRequestRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		CallInformationRequestRequestWrapper event = new CallInformationRequestRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onCallInformationReportRequest(CallInformationReportRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		CallInformationReportRequestWrapper event = new CallInformationReportRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onActivityTestRequest(ActivityTestRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ActivityTestRequestWrapper event = new ActivityTestRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onActivityTestResponse(ActivityTestResponse ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ActivityTestResponseWrapper event = new ActivityTestResponseWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onAssistRequestInstructionsRequest(AssistRequestInstructionsRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		AssistRequestInstructionsRequestWrapper event = new AssistRequestInstructionsRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onEstablishTemporaryConnectionRequest(EstablishTemporaryConnectionRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		EstablishTemporaryConnectionRequestWrapper event = new EstablishTemporaryConnectionRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onDisconnectForwardConnectionRequest(DisconnectForwardConnectionRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		DisconnectForwardConnectionRequestWrapper event = new DisconnectForwardConnectionRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onConnectToResourceRequest(ConnectToResourceRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ConnectToResourceRequestWrapper event = new ConnectToResourceRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onResetTimerRequest(ResetTimerRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		ResetTimerRequestWrapper event = new ResetTimerRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onFurnishChargingInformationRequest(FurnishChargingInformationRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		FurnishChargingInformationRequestWrapper event = new FurnishChargingInformationRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onSendChargingInformationRequest(SendChargingInformationRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		SendChargingInformationRequestWrapper event = new SendChargingInformationRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onSpecializedResourceReportRequest(SpecializedResourceReportRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		SpecializedResourceReportRequestWrapper event = new SpecializedResourceReportRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onPlayAnnouncementRequest(PlayAnnouncementRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		PlayAnnouncementRequestWrapper event = new PlayAnnouncementRequestWrapper(capDialogCircuitSwitchedCallWrapper,
				ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onPromptAndCollectUserInformationRequest(PromptAndCollectUserInformationRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		PromptAndCollectUserInformationRequestWrapper event = new PromptAndCollectUserInformationRequestWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onPromptAndCollectUserInformationResponse(PromptAndCollectUserInformationResponse ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		PromptAndCollectUserInformationResponseWrapper event = new PromptAndCollectUserInformationResponseWrapper(
				capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

	@Override
	public void onCancelRequest(CancelRequest ind) {
		CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
				.getCAPDialog().getUserObject();
		CancelRequestWrapper event = new CancelRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
	}

    @Override
    public void onContinueWithArgumentRequest(ContinueWithArgumentRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        ContinueWithArgumentRequestWrapper event = new ContinueWithArgumentRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onDisconnectLegRequest(DisconnectLegRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        DisconnectLegRequestWrapper event = new DisconnectLegRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onDisconnectLegResponse(DisconnectLegResponse ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        DisconnectLegResponseWrapper event = new DisconnectLegResponseWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onDisconnectForwardConnectionWithArgumentRequest(DisconnectForwardConnectionWithArgumentRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        DisconnectForwardConnectionWithArgumentRequestWrapper event = new DisconnectForwardConnectionWithArgumentRequestWrapper(
                capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onInitiateCallAttemptRequest(InitiateCallAttemptRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        InitiateCallAttemptRequestWrapper event = new InitiateCallAttemptRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onInitiateCallAttemptResponse(InitiateCallAttemptResponse ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        InitiateCallAttemptResponseWrapper event = new InitiateCallAttemptResponseWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onMoveLegRequest(MoveLegRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        MoveLegRequestWrapper event = new MoveLegRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onMoveLegResponse(MoveLegResponse ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        MoveLegResponseWrapper event = new MoveLegResponseWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onSplitLegRequest(SplitLegRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
                .getCAPDialog().getUserObject();
        SplitLegRequestWrapper event = new SplitLegRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onSplitLegResponse(SplitLegResponse ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
                .getCAPDialog().getUserObject();
        SplitLegResponseWrapper event = new SplitLegResponseWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onCollectInformationRequest(CollectInformationRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind.getCAPDialog().getUserObject();
        CollectInformationRequestWrapper event = new CollectInformationRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

    @Override
    public void onCallGapRequest(CallGapRequest ind) {
        CAPDialogCircuitSwitchedCallWrapper capDialogCircuitSwitchedCallWrapper = (CAPDialogCircuitSwitchedCallWrapper) ind
                .getCAPDialog().getUserObject();
        CallGapRequestWrapper event = new CallGapRequestWrapper(capDialogCircuitSwitchedCallWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogCircuitSwitchedCallWrapper, event);
    }

	// ///////////////////////
	// Service: GPRS
	// ///////////////////////
	
	@Override
	public void onInitialDpGprsRequest(InitialDpGprsRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		InitialDpGprsRequestWrapper event = new InitialDpGprsRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onRequestReportGPRSEventRequest(
			RequestReportGPRSEventRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		RequestReportGPRSEventRequestWrapper event = new RequestReportGPRSEventRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onApplyChargingGPRSRequest(ApplyChargingGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ApplyChargingGPRSRequestWrapper event = new ApplyChargingGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onEntityReleasedGPRSRequest(EntityReleasedGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		EntityReleasedGPRSRequestWrapper event = new EntityReleasedGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onEntityReleasedGPRSResponse(EntityReleasedGPRSResponse ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		EntityReleasedGPRSResponseWrapper event = new EntityReleasedGPRSResponseWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onConnectGPRSRequest(ConnectGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ConnectGPRSRequestWrapper event = new ConnectGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onContinueGPRSRequest(ContinueGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ContinueGPRSRequestWrapper event = new ContinueGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onReleaseGPRSRequest(ReleaseGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ReleaseGPRSRequestWrapper event = new ReleaseGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onResetTimerGPRSRequest(ResetTimerGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ResetTimerGPRSRequestWrapper event = new ResetTimerGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onFurnishChargingInformationGPRSRequest(
			FurnishChargingInformationGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		FurnishChargingInformationGPRSRequestWrapper event = new FurnishChargingInformationGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onCancelGPRSRequest(CancelGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		CancelGPRSRequestWrapper event = new CancelGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onSendChargingInformationGPRSRequest(
			SendChargingInformationGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		SendChargingInformationGPRSRequestWrapper event = new SendChargingInformationGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onApplyChargingReportGPRSRequest(
			ApplyChargingReportGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ApplyChargingReportGPRSRequestWrapper event = new ApplyChargingReportGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onApplyChargingReportGPRSResponse(
			ApplyChargingReportGPRSResponse ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ApplyChargingReportGPRSResponseWrapper event = new ApplyChargingReportGPRSResponseWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onEventReportGPRSRequest(EventReportGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		EventReportGPRSRequestWrapper event = new EventReportGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onEventReportGPRSResponse(EventReportGPRSResponse ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		EventReportGPRSResponseWrapper event = new EventReportGPRSResponseWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onActivityTestGPRSRequest(ActivityTestGPRSRequest ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ActivityTestGPRSRequestWrapper event = new ActivityTestGPRSRequestWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

	@Override
	public void onActivityTestGPRSResponse(ActivityTestGPRSResponse ind) {
		CAPDialogGprsWrapper capDialogGprsWrapper = (CAPDialogGprsWrapper) ind
				.getCAPDialog().getUserObject();
		ActivityTestGPRSResponseWrapper event = new ActivityTestGPRSResponseWrapper(capDialogGprsWrapper, ind);
		onEvent(event.getEventTypeName(), capDialogGprsWrapper, event);
	}

    // ///////////////////////
    // Service: SMS
    // ///////////////////////
    

    @Override
    public void onConnectSMSRequest(ConnectSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        ConnectSMSRequestWrapper event = new ConnectSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onEventReportSMSRequest(EventReportSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        EventReportSMSRequestWrapper event = new EventReportSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onFurnishChargingInformationSMSRequest(FurnishChargingInformationSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        FurnishChargingInformationSMSRequestWrapper event = new FurnishChargingInformationSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onInitialDPSMSRequest(InitialDPSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        InitialDPSMSRequestWrapper event = new InitialDPSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onReleaseSMSRequest(ReleaseSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        ReleaseSMSRequestWrapper event = new ReleaseSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onRequestReportSMSEventRequest(RequestReportSMSEventRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        RequestReportSMSEventRequestWrapper event = new RequestReportSMSEventRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onResetTimerSMSRequest(ResetTimerSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        ResetTimerSMSRequestWrapper event = new ResetTimerSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    @Override
    public void onContinueSMSRequest(ContinueSMSRequest ind) {
        CAPDialogSmsWrapper capDialogSmsWrapper = (CAPDialogSmsWrapper) ind.getCAPDialog().getUserObject();
        ContinueSMSRequestWrapper event = new ContinueSMSRequestWrapper(capDialogSmsWrapper, ind);
        onEvent(event.getEventTypeName(), capDialogSmsWrapper, event);
    }

    public CAPResourceAdaptorStatisticsUsageParameters getDefaultUsageParameters() {
    	return this.defaultUsageParameters;
    }
}
