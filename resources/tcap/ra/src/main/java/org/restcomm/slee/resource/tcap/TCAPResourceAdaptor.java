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

package org.restcomm.slee.resource.tcap;

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

import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.TCListener;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.TRPseudoState;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCNoticeIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.restcomm.protocols.ss7.tcap.asn.InvokeImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.Reject;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResult;
import org.restcomm.protocols.ss7.tcap.asn.comp.ReturnResultLast;
import org.restcomm.slee.resource.tcap.events.TCAPEvent;
import org.restcomm.slee.resource.tcap.wrappers.InvokeEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.ProviderAbortEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.RejectEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.ReturnErrorEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.ReturnResultEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.ReturnResultLastEventImpl;
import org.restcomm.slee.resource.tcap.wrappers.TCAPDialogWrapper;
import org.restcomm.slee.resource.tcap.wrappers.TCAPProviderWrapper;
import org.restcomm.slee.resource.tcap.wrappers.UserAbortEventImpl;

import java.lang.management.ManagementFactory;

/**
 * 
 * @author amit bhayani
 * 
 */
public class TCAPResourceAdaptor implements ResourceAdaptor, TCListener {
	/**
	 * for all events we are interested in knowing when the event failed to be
	 * processed
	 */
	public static final int DEFAULT_EVENT_FLAGS = EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK;

	private static final int ACTIVITY_FLAGS = ActivityFlags.REQUEST_ENDED_CALLBACK;// .NO_FLAGS;

	/**
	 * This is local proxy of provider.
	 */
	protected TCAPProviderWrapper tcapProvider = null;
	protected TCAPProvider realProvider = null; // so we dont have to "get"
	private Tracer tracer;
	private transient SleeEndpoint sleeEndpoint = null;

	private ResourceAdaptorContext resourceAdaptorContext;

	private EventIDCache eventIdCache = null;

	/**
	 * tells the RA if an event with a specified ID should be filtered or not
	 */
	private final EventIDFilter eventIDFilter = new EventIDFilter();

	// ////////////////////////////
	// Configuration parameters //
	// ////////////////////////////
	private static final String CONF_TCAP_JNDI = "tcapJndi";

	private String tcapJndi = null;
	private transient static final Address address = new Address(AddressPlan.IP, "localhost");

	public TCAPResourceAdaptor() {
		this.tcapProvider = new TCAPProviderWrapper(this);
	}

	// ////////////////
	// RA callbacks //
	// ////////////////
	public void activityEnded(ActivityHandle activityHandle) {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("Activity with handle " + activityHandle + " ended");
		}
		TCAPDialogActivityHandle mdah = (TCAPDialogActivityHandle) activityHandle;
		final TCAPDialogWrapper dw = mdah.getActivity();
		mdah.setActivity(null);

		if (dw != null) {
			dw.clear();
		}
	}

	public void activityUnreferenced(ActivityHandle arg0) {
		// TODO Auto-generated method stub

	}

	public void administrativeRemove(ActivityHandle handle) {
		if (tracer.isFineEnabled())
			tracer.fine("administrativeRemove:" + handle);
	}

	public void eventProcessingFailed(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags,
			FailureReason reason) {
		if (tracer.isFineEnabled())
			tracer.fine("eventProcessingFailed:" + eventType + ":" + handle);
		// used to inform the resource adaptor object that the specified Event
		// could not be processed successfully by the SLEE.

		if (eventType.getEventType().getName().equals(TCAPEvent.EVENT_TYPE_NAME_DIALOG_TIMEOUT)) {
			TCAPDialogActivityHandle dah = (TCAPDialogActivityHandle) handle;
			TCAPDialogWrapper dw = dah.getActivity();
			if (dw != null)
				releaseDialog(dw);
		}
	}

	public void eventProcessingSuccessful(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service,
			int flags) {
		if (tracer.isFineEnabled())
			tracer.fine("eventProcessingSuccessful:" + eventType + ":" + handle);
		// used to inform the resource adaptor object that the specified Event
		// was processed successfully by the SLEE.

		if (eventType.getEventType().getName().equals(TCAPEvent.EVENT_TYPE_NAME_DIALOG_TIMEOUT)) {
			TCAPDialogActivityHandle dah = (TCAPDialogActivityHandle) handle;
			TCAPDialogWrapper dw = dah.getActivity();
			if (dw != null)
				releaseDialog(dw);
		}
	}

	public void eventUnreferenced(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
		if (tracer.isFineEnabled())
			tracer.fine("eventUnreferenced:" + eventType + ":" + handle);
		// used to inform the Resource Adaptor that the SLEE no longer
		// references an Event object which was previously fired by the
		// resource adaptor object.
	}

	private void releaseDialog(TCAPDialogWrapper dw) {
		dw.release();
	}

	public Object getActivity(ActivityHandle handle) {
		return ((TCAPDialogActivityHandle) handle).getActivity();
	}

	public ActivityHandle getActivityHandle(Object activity) {
		if (activity instanceof TCAPDialogWrapper) {
			final TCAPDialogWrapper wrapper = ((TCAPDialogWrapper) activity);
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

	public Object getResourceAdaptorInterface(String className) {
		return this.tcapProvider;
	}

	public void queryLiveness(ActivityHandle activityHandle) {
		final TCAPDialogActivityHandle handle = ((TCAPDialogActivityHandle) activityHandle);
		final TCAPDialogWrapper mapDialog = handle.getActivity();
		if (mapDialog == null || mapDialog.getWrappedDialog() == null || mapDialog.getState() == TRPseudoState.Expunged) {
			sleeEndpoint.endActivity(handle);
		}
	}

	public void raActive() {

		try {
			//InitialContext ic = new InitialContext();
			//this.realProvider = (TCAPProvider) ic.lookup(this.tcapJndi);
			//if (tracer.isInfoEnabled()) {
			//	tracer.info("Successfully connected to TCAP service[" + this.tcapJndi + "]");
			//}

            ObjectName objectName = new ObjectName("org.restcomm.ss7:service=TCAPSS7Service");
            Object object = null;
            if (ManagementFactory.getPlatformMBeanServer().isRegistered(objectName)) {
                // trying to get via MBeanServer
                object = ManagementFactory.getPlatformMBeanServer().getAttribute(objectName, "Stack");
				if (tracer.isInfoEnabled()) {
					tracer.info("Trying to get via MBeanServer: " + objectName + ", object: " + object);
				}
            } else {
                // trying to get via Jndi
                InitialContext ic = new InitialContext();
                object = ic.lookup(this.tcapJndi);
				if (tracer.isInfoEnabled()) {
					tracer.info("Trying to get via JNDI: " + this.tcapJndi + ", object: " + object);
				}
            }
			if (object instanceof TCAPProvider) {
				this.realProvider = (TCAPProvider) object;
				if (tracer.isInfoEnabled()) {
					tracer.info("Successfully connected to TCAP service[" +
							this.realProvider.getClass().getCanonicalName() + "]");
				}
            } else {
				if (tracer.isSevereEnabled()) {
					tracer.severe("Failed of connecting to TCAP service[org.restcomm.ss7:service=TCAPSS7Service]");
				}
			}

			this.realProvider.addTCListener(this);
			this.sleeEndpoint = resourceAdaptorContext.getSleeEndpoint();
			this.tcapProvider.setWrappedProvider(this.realProvider);

		} catch (Exception e) {
			this.tracer.severe("Failed to activate TCAP RA ", e);
		}
	}

	public void raConfigurationUpdate(ConfigProperties properties) {
		raConfigure(properties);
	}

	public void raConfigure(ConfigProperties properties) {
		try {
			if (tracer.isInfoEnabled()) {
				tracer.info("Configuring TCAP RA: " + this.resourceAdaptorContext.getEntityName());
			}
			this.tcapJndi = (String) properties.getProperty(CONF_TCAP_JNDI).getValue();
		} catch (Exception e) {
			tracer.severe("Configuring of MAP RA failed ", e);
		}
	}

	public void raInactive() {
		if (tracer.isFineEnabled()) {
			tracer.fine("MAP Resource Adaptor - inactivating");
		}
		this.realProvider.removeTCListener(this);
		if (tracer.isInfoEnabled()) {
			tracer.info("TCAP Resource Adaptor entity inactive.");
		}
	}

	public void raStopping() {
		if (tracer.isFineEnabled()) {
			tracer.fine("TCAP Resource Adaptor entity stopping.");
		}
	}

	public void raUnconfigure() {
		this.tcapJndi = null;

		if (tracer.isFineEnabled()) {
			tracer.fine("MAP Resource Adaptor unconfigured.");
		}
	}

	public void raVerifyConfiguration(ConfigProperties properties) throws InvalidConfigurationException {
		try {

			if (tracer.isInfoEnabled()) {
				tracer.info("Verifying configuring TCAP RA: " + this.resourceAdaptorContext.getEntityName());
			}

			this.tcapJndi = (String) properties.getProperty(CONF_TCAP_JNDI).getValue();
			if (this.tcapJndi == null) {
				throw new InvalidConfigurationException("TCAP JNDI lookup name cannot be null");
			}

		} catch (Exception e) {
			throw new InvalidConfigurationException("Failed to test configuration options!", e);
		}

		if (tracer.isInfoEnabled()) {
			tracer.info("TCAP Resource Adaptor configuration verified.");
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
		this.tracer = resourceAdaptorContext.getTracer(TCAPResourceAdaptor.class.getSimpleName());
		this.sleeEndpoint = raContext.getSleeEndpoint();

		this.eventIdCache = new EventIDCache(this.tracer);
	}

	public void unsetResourceAdaptorContext() {
		this.resourceAdaptorContext = null;
	}

	// //////////////////
	// Helper methods //
	// //////////////////
	public void startActivity(TCAPDialogWrapper mapDialogWrapper) throws ActivityAlreadyExistsException, NullPointerException, IllegalStateException,
			SLEEException, StartActivityException {
		this.sleeEndpoint.startActivity(mapDialogWrapper.getActivityHandle(), mapDialogWrapper, ACTIVITY_FLAGS);
	}

	public void startSuspendedActivity(TCAPDialogWrapper mapDialogWrapper) throws ActivityAlreadyExistsException, NullPointerException, IllegalStateException,
			SLEEException, StartActivityException {
		this.sleeEndpoint.startActivitySuspended(mapDialogWrapper.getActivityHandle(), mapDialogWrapper, ActivityFlags.REQUEST_ENDED_CALLBACK);
	}

	/**
	 * Private methods
	 */
	private void fireEvent(String eventName, ActivityHandle handle, Object event, int flags) {

		FireableEventType eventID = eventIdCache.getEventId(this.resourceAdaptorContext.getEventLookupFacility(), eventName);

		if (eventIDFilter.filterEvent(eventID)) {
			if (tracer.isFineEnabled()) {
				tracer.fine("Event " + (eventID == null ? "null" : eventID.getEventType()) + " filtered");
			}
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
		}
	}

	// /////////////////
	// Event helpers //
	// /////////////////
	private TCAPDialogActivityHandle onEvent(String eventName, TCAPDialogWrapper dw, TCAPEvent event) {
		return this.onEvent(eventName, dw, event, EventFlags.NO_FLAGS);
	}

	private TCAPDialogActivityHandle onEvent(String eventName, TCAPDialogWrapper dw, TCAPEvent event, int flags) {
		if (dw == null) {
			this.tracer.severe(String.format("Firing %s but TCAPDialogWrapper userObject is null", eventName));
			return null;
		}

		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Firing %s for DialogId=%d", eventName, dw.getWrappedDialog().getLocalDialogId()));
		}

		this.fireEvent(eventName, dw.getActivityHandle(), event, flags);
		return dw.getActivityHandle();
	}

	@Override
	public void onInvokeTimeout(Invoke tcInvokeRequest) {
		TCAPDialogWrapper mapDialogWrapper = (TCAPDialogWrapper) ((InvokeImpl) tcInvokeRequest).getDialog().getUserObject();
		InvokeEventImpl invokeEvent = new InvokeEventImpl(mapDialogWrapper, tcInvokeRequest);
		onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_INVOKE_TIMEOUT, mapDialogWrapper, invokeEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCBegin(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication)
	 */
	@Override
	public void onTCBegin(TCBeginIndication tcBeginIndication) {
		try {
			Dialog wrappedDialog = tcBeginIndication.getDialog();
			TCAPDialogActivityHandle activityHandle = new TCAPDialogActivityHandle(wrappedDialog.getLocalDialogId());
			TCAPDialogWrapper tcapDialogWrapper = new TCAPDialogWrapper(activityHandle, this, wrappedDialog);

			wrappedDialog.setUserObject(tcapDialogWrapper);
			this.startActivity(tcapDialogWrapper);
			this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_BEGIN, tcapDialogWrapper, tcapDialogWrapper);

			Component[] components = tcBeginIndication.getComponents();
			this.processComponents(tcapDialogWrapper, components);
		} catch (Exception e) {
			this.tracer.severe(String.format("Exception when trying to fire event DIALOG_BEGIN for received TCBeginIndication=%s ", tcBeginIndication), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCContinue(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCContinueIndication)
	 */
	@Override
	public void onTCContinue(TCContinueIndication tcContinueIndication) {
		Dialog wrappedDialog = tcContinueIndication.getDialog();
		TCAPDialogWrapper tcapDialogWrapper = (TCAPDialogWrapper) wrappedDialog.getUserObject();
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_CONTINUE, tcapDialogWrapper, tcapDialogWrapper);

		Component[] components = tcContinueIndication.getComponents();
		this.processComponents(tcapDialogWrapper, components);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCEnd(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCEndIndication)
	 */
	@Override
	public void onTCEnd(TCEndIndication tcEndIndication) {
		Dialog wrappedDialog = tcEndIndication.getDialog();
		TCAPDialogWrapper tcapDialogWrapper = (TCAPDialogWrapper) wrappedDialog.getUserObject();
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_END, tcapDialogWrapper, tcapDialogWrapper);

		Component[] components = tcEndIndication.getComponents();
		this.processComponents(tcapDialogWrapper, components);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCNotice(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCNoticeIndication)
	 */
	@Override
	public void onTCNotice(TCNoticeIndication tcNoticeIndication) {
		Dialog wrappedDialog = tcNoticeIndication.getDialog();
		TCAPDialogWrapper tcapDialogWrapper = (TCAPDialogWrapper) wrappedDialog.getUserObject();
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_NOTICE, tcapDialogWrapper, tcapDialogWrapper);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCPAbort(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication)
	 */
	@Override
	public void onTCPAbort(TCPAbortIndication tcPAbortIndication) {
		Dialog wrappedDialog = tcPAbortIndication.getDialog();
		TCAPDialogWrapper tcapDialogWrapper = (TCAPDialogWrapper) wrappedDialog.getUserObject();
		ProviderAbortEventImpl providerAbortEvent = new ProviderAbortEventImpl(tcapDialogWrapper, tcPAbortIndication);
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_PROVIDERABORT, tcapDialogWrapper, providerAbortEvent);
//		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_PROVIDERABORT, tcapDialogWrapper, tcapDialogWrapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCUni(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCUniIndication)
	 */
	@Override
	public void onTCUni(TCUniIndication tcUniIndication) {

		TCAPDialogActivityHandle activityHandle = null;
		Dialog wrappedDialog = tcUniIndication.getDialog();

		try {
			activityHandle = new TCAPDialogActivityHandle(wrappedDialog.getLocalDialogId());
			TCAPDialogWrapper tcapDialogWrapper = new TCAPDialogWrapper(activityHandle, this, wrappedDialog);

			wrappedDialog.setUserObject(tcapDialogWrapper);
			this.startActivity(tcapDialogWrapper);
			this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_UNI, tcapDialogWrapper, tcapDialogWrapper);

			Component[] components = tcUniIndication.getComponents();
			this.processComponents(tcapDialogWrapper, components);
		} catch (Exception e) {
			this.tracer.severe(String.format("Exception when trying to fire event DIALOG_UNI for received TCUniIndication=%s ", tcUniIndication), e);
		} finally {
			// End Activity

			// we do not end activity here because we do it by TCAP stack 
//			if (activityHandle != null) {
//				try {
//					this.sleeEndpoint.endActivity(activityHandle);
//				} catch (Exception e) {
//					this.tracer.severe(String.format("Exception while trying to end the Activity for Dialog=%s for received TCUniIndication=%s", wrappedDialog,
//							tcUniIndication));
//				}
//			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onTCUserAbort(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication)
	 */
	@Override
	public void onTCUserAbort(TCUserAbortIndication tcUserAbortIndication) {
		Dialog wrappedDialog = tcUserAbortIndication.getDialog();
		TCAPDialogWrapper tcapDialogWrapper = (TCAPDialogWrapper) wrappedDialog.getUserObject();
		UserAbortEventImpl userAbortEvent = new UserAbortEventImpl(tcapDialogWrapper, tcUserAbortIndication);
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_USERABORT, tcapDialogWrapper, userAbortEvent);
//		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_USERABORT, tcapDialogWrapper, tcapDialogWrapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onDialogTimeout(org.restcomm
	 * .protocols.ss7.tcap.api.tc.dialog.Dialog)
	 */
	@Override
	public void onDialogTimeout(Dialog dialog) {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Rx : onDialogTimeout for DialogId=%d", dialog.getLocalDialogId()));
		}

		dialog.keepAlive();

		TCAPDialogWrapper mapDialogWrapper = (TCAPDialogWrapper) dialog.getUserObject();
		mapDialogWrapper.startDialogTimeoutProc();

		onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_TIMEOUT, mapDialogWrapper, mapDialogWrapper,
				(EventFlags.REQUEST_PROCESSING_SUCCESSFUL_CALLBACK | EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.protocols.ss7.tcap.api.TCListener#onDialogReleased(org.
	 * restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog)
	 */
	@Override
	public void onDialogReleased(Dialog dialog) {
		try {

			TCAPDialogWrapper mapDialogWrapper = (TCAPDialogWrapper) dialog.getUserObject();
			TCAPDialogActivityHandle handle = onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_RELEASED, mapDialogWrapper, mapDialogWrapper);

			// End Activity
			this.sleeEndpoint.endActivity(handle);
		} catch (Exception e) {
			this.tracer.severe(String.format("onDialogRelease : Exception while trying to end activity for TCAP Dialog=%s", dialog), e);
		}
	}

	private void processComponents(TCAPDialogWrapper dialogWrapper, Component[] components) {
		if (components != null) {
			for (int count = 0; count < components.length; count++) {
				Component component = components[count];
				switch (component.getType()) {
				case Invoke:
					Invoke invoke = (Invoke) component;
					InvokeEventImpl invokeEventImpl = new InvokeEventImpl(dialogWrapper, invoke);
					this.onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_INVOKE, dialogWrapper, invokeEventImpl);
					break;
				case ReturnResult:
					ReturnResult returnResult = (ReturnResult) component;
					ReturnResultEventImpl returnResultEventImpl = new ReturnResultEventImpl(dialogWrapper, returnResult);
					this.onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_RETURNRESULT, dialogWrapper, returnResultEventImpl);
					break;
				case ReturnResultLast:
					ReturnResultLast returnResultLast = (ReturnResultLast) component;
					ReturnResultLastEventImpl returnResultLastEventImpl = new ReturnResultLastEventImpl(dialogWrapper, returnResultLast);
					this.onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_RETURNRESULT_LAST, dialogWrapper, returnResultLastEventImpl);
					break;
				case Reject:
					Reject reject = (Reject) component;
					RejectEventImpl rejectEventImpl = new RejectEventImpl(dialogWrapper, reject);
					this.onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_REJECT, dialogWrapper, rejectEventImpl);
					break;
				case ReturnError:
					ReturnError returnError = (ReturnError) component;
					ReturnErrorEventImpl returnErrorEventImpl = new ReturnErrorEventImpl(dialogWrapper, returnError);
					this.onEvent(TCAPEvent.EVENT_TYPE_NAME_COMPONENT_RETURNERROR, dialogWrapper, returnErrorEventImpl);
					break;
				default:
					// Should this ever happen?
					break;
				}
			}
		}

		// sending DIALOG_DELIMITER message after delivering all components
		this.onEvent(TCAPEvent.EVENT_TYPE_NAME_DIALOG_DELIMITER, dialogWrapper, dialogWrapper);
	}
}
