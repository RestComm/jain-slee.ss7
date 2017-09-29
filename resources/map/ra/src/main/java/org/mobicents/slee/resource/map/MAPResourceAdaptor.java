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

package org.mobicents.slee.resource.map;

import org.mobicents.protocols.ss7.map.api.MAPDialog;
import org.mobicents.protocols.ss7.map.api.MAPDialogListener;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPMessage;
import org.mobicents.protocols.ss7.map.api.MAPProvider;
import org.mobicents.protocols.ss7.map.api.dialog.MAPAbortProviderReason;
import org.mobicents.protocols.ss7.map.api.dialog.MAPAbortSource;
import org.mobicents.protocols.ss7.map.api.dialog.MAPDialogState;
import org.mobicents.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic;
import org.mobicents.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.mobicents.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.mobicents.protocols.ss7.map.api.primitives.AddressString;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.callhandling.IstCommandRequest;
import org.mobicents.protocols.ss7.map.api.service.callhandling.IstCommandResponse;
import org.mobicents.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.mobicents.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandlingListener;
import org.mobicents.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberRequest;
import org.mobicents.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberResponse;
import org.mobicents.protocols.ss7.map.api.service.callhandling.SendRoutingInformationRequest;
import org.mobicents.protocols.ss7.map.api.service.callhandling.SendRoutingInformationResponse;
import org.mobicents.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.mobicents.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener;
import org.mobicents.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationRequest;
import org.mobicents.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.mobicents.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSRequest;
import org.mobicents.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSResponse;
import org.mobicents.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.mobicents.protocols.ss7.map.api.service.lsm.SubscriberLocationReportResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.mobicents.protocols.ss7.map.api.service.mobility.MAPServiceMobilityListener;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.faultRecovery.ForwardCheckSSIndicationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.faultRecovery.ResetRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.imei.CheckImeiRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.mobicents.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.mobicents.protocols.ss7.map.api.service.oam.ActivateTraceModeRequest_Oam;
import org.mobicents.protocols.ss7.map.api.service.oam.ActivateTraceModeResponse_Oam;
import org.mobicents.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.mobicents.protocols.ss7.map.api.service.oam.MAPServiceOamListener;
import org.mobicents.protocols.ss7.map.api.service.oam.SendImsiRequest;
import org.mobicents.protocols.ss7.map.api.service.oam.SendImsiResponse;
import org.mobicents.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.mobicents.protocols.ss7.map.api.service.pdpContextActivation.MAPServicePdpContextActivationListener;
import org.mobicents.protocols.ss7.map.api.service.pdpContextActivation.SendRoutingInfoForGprsRequest;
import org.mobicents.protocols.ss7.map.api.service.pdpContextActivation.SendRoutingInfoForGprsResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.AlertServiceCentreRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.AlertServiceCentreResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.ForwardShortMessageRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.ForwardShortMessageResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.InformServiceCentreRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.mobicents.protocols.ss7.map.api.service.sms.MAPServiceSmsListener;
import org.mobicents.protocols.ss7.map.api.service.sms.MoForwardShortMessageRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.MoForwardShortMessageResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.MtForwardShortMessageRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.MtForwardShortMessageResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.NoteSubscriberPresentRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.ReadyForSMRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.ReadyForSMResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.ReportSMDeliveryStatusRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.ReportSMDeliveryStatusResponse;
import org.mobicents.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMRequest;
import org.mobicents.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ActivateSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ActivateSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.DeactivateSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.DeactivateSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.EraseSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.EraseSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.GetPasswordRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.GetPasswordResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.InterrogateSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.InterrogateSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.mobicents.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementaryListener;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.RegisterPasswordRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.RegisterPasswordResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.RegisterSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.RegisterSSResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSNotifyRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSNotifyResponse;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponse;
import org.mobicents.protocols.ss7.tcap.asn.ApplicationContextName;
import org.mobicents.protocols.ss7.tcap.asn.comp.Problem;
import org.mobicents.slee.resource.cluster.FaultTolerantResourceAdaptor;
import org.mobicents.slee.resource.cluster.FaultTolerantResourceAdaptorContext;
import org.mobicents.slee.resource.cluster.ReplicatedData;
import org.mobicents.slee.resource.map.events.DialogAccept;
import org.mobicents.slee.resource.map.events.DialogClose;
import org.mobicents.slee.resource.map.events.DialogDelimiter;
import org.mobicents.slee.resource.map.events.DialogNotice;
import org.mobicents.slee.resource.map.events.DialogProviderAbort;
import org.mobicents.slee.resource.map.events.DialogReject;
import org.mobicents.slee.resource.map.events.DialogRelease;
import org.mobicents.slee.resource.map.events.DialogRequest;
import org.mobicents.slee.resource.map.events.DialogTimeout;
import org.mobicents.slee.resource.map.events.DialogUserAbort;
import org.mobicents.slee.resource.map.events.ErrorComponent;
import org.mobicents.slee.resource.map.events.InvokeTimeout;
import org.mobicents.slee.resource.map.events.MAPEvent;
import org.mobicents.slee.resource.map.events.RejectComponent;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.IstCommandRequestWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.IstCommandResponseWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.MAPDialogCallHandlingWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.ProvideRoamingNumberRequestWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.ProvideRoamingNumberResponseWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.SendRoutingInformationRequestWrapper;
import org.mobicents.slee.resource.map.service.callhandling.wrappers.SendRoutingInformationResponseWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.MAPDialogLsmWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.ProvideSubscriberLocationRequestWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.ProvideSubscriberLocationResponseWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.SendRoutingInfoForLCSRequestWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.SendRoutingInfoForLCSResponseWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.SubscriberLocationReportRequestWrapper;
import org.mobicents.slee.resource.map.service.lsm.wrappers.SubscriberLocationReportResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.authentication.wrappers.AuthenticationFailureReportRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.authentication.wrappers.AuthenticationFailureReportResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.authentication.wrappers.SendAuthenticationInfoRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.authentication.wrappers.SendAuthenticationInfoResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.faultRecovery.wrappers.ForwardCheckSSIndicationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.faultRecovery.wrappers.ResetRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.faultRecovery.wrappers.RestoreDataRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.faultRecovery.wrappers.RestoreDataResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.imei.wrappers.CheckImeiRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.imei.wrappers.CheckImeiResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.CancelLocationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.CancelLocationResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.PurgeMSRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.PurgeMSResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.SendIdentificationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.SendIdentificationResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.UpdateGprsLocationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.UpdateGprsLocationResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.UpdateLocationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.locationManagement.wrappers.UpdateLocationResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.oam.wrappers.ActivateTraceModeRequest_MobilityWrapper;
import org.mobicents.slee.resource.map.service.mobility.oam.wrappers.ActivateTraceModeResponse_MobilityWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberInformation.wrappers.AnyTimeInterrogationRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberInformation.wrappers.AnyTimeInterrogationResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberInformation.wrappers.ProvideSubscriberInfoRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberInformation.wrappers.ProvideSubscriberInfoResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberManagement.wrappers.DeleteSubscriberDataRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberManagement.wrappers.DeleteSubscriberDataResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberManagement.wrappers.InsertSubscriberDataRequestWrapper;
import org.mobicents.slee.resource.map.service.mobility.subscriberManagement.wrappers.InsertSubscriberDataResponseWrapper;
import org.mobicents.slee.resource.map.service.mobility.wrappers.MAPDialogMobilityWrapper;
import org.mobicents.slee.resource.map.service.oam.wrappers.ActivateTraceModeRequest_OamWrapper;
import org.mobicents.slee.resource.map.service.oam.wrappers.ActivateTraceModeResponse_OamWrapper;
import org.mobicents.slee.resource.map.service.oam.wrappers.MAPDialogOamWrapper;
import org.mobicents.slee.resource.map.service.oam.wrappers.SendImsiRequestWrapper;
import org.mobicents.slee.resource.map.service.oam.wrappers.SendImsiResponseWrapper;
import org.mobicents.slee.resource.map.service.pdpContextActivation.wrappers.MAPDialogPdpContextActivationWrapper;
import org.mobicents.slee.resource.map.service.pdpContextActivation.wrappers.SendRoutingInfoForGprsRequestWrapper;
import org.mobicents.slee.resource.map.service.pdpContextActivation.wrappers.SendRoutingInfoForGprsResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.AlertServiceCentreRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.AlertServiceCentreResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ForwardShortMessageRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ForwardShortMessageResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.InformServiceCentreRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.MAPDialogSmsWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.MoForwardShortMessageRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.MoForwardShortMessageResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.MtForwardShortMessageRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.MtForwardShortMessageResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.NoteSubscriberPresentRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ReadyForSMRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ReadyForSMResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ReportSMDeliveryStatusRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.ReportSMDeliveryStatusResponseWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.SendRoutingInfoForSMRequestWrapper;
import org.mobicents.slee.resource.map.service.sms.wrappers.SendRoutingInfoForSMResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.ActivateSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.ActivateSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.DeactivateSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.DeactivateSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.EraseSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.EraseSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.GetPasswordRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.GetPasswordResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.InterrogateSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.InterrogateSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.MAPDialogSupplementaryWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.ProcessUnstructuredSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.ProcessUnstructuredSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.RegisterPasswordRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.RegisterPasswordResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.RegisterSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.RegisterSSResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.UnstructuredSSNotifyRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.UnstructuredSSNotifyResponseWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.UnstructuredSSRequestWrapper;
import org.mobicents.slee.resource.map.service.supplementary.wrappers.UnstructuredSSResponseWrapper;
import org.mobicents.slee.resource.map.wrappers.MAPDialogWrapper;
import org.mobicents.slee.resource.map.wrappers.MAPProviderWrapper;

import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.slee.Address;
import javax.slee.AddressPlan;
import javax.slee.SLEEException;
import javax.slee.facilities.TraceLevel;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class MAPResourceAdaptor implements ResourceAdaptor, FaultTolerantResourceAdaptor<Long,byte[]>, MAPDialogListener, MAPServiceMobilityListener,
		MAPServiceCallHandlingListener, MAPServiceOamListener, MAPServicePdpContextActivationListener,
		MAPServiceSupplementaryListener, MAPServiceSmsListener, MAPServiceLsmListener {
	/**
	 * for all events we are interested in knowing when the event failed to be
	 * processed
	 */
	public static final int DEFAULT_EVENT_FLAGS = EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK;

	private static final int ACTIVITY_FLAGS = ActivityFlags.REQUEST_ENDED_CALLBACK;// .NO_FLAGS;

	/**
	 * This is local proxy of provider.
	 */
	protected MAPProviderWrapper mapProvider = null;
	protected MAPProvider realProvider = null; // so we dont have to "get"
	private Tracer tracer;
	private transient SleeEndpoint sleeEndpoint = null;

	public ResourceAdaptorContext resourceAdaptorContext;
	public FaultTolerantResourceAdaptorContext<Long,byte[]> ftResourceAdaptorContext;

	private EventIDCache eventIdCache = null;

	private Marshaler marshaler = new MAPRAMarshaler();

	/**
	 * tells the RA if an event with a specified ID should be filtered or not
	 */
	private final EventIDFilter eventIDFilter = new EventIDFilter();

	// ////////////////////////////
	// Configuration parameters //
	// ////////////////////////////
	private static final String CONF_MAP_JNDI = "mapJndi";

	private String mapJndi = null;
	private transient static final Address address = new Address(AddressPlan.IP, "localhost");


	private ReplicatedData<Long, byte[]> replicateData;
	private final ConcurrentHashMap<Long,MAPDialogWrapper> localData=new ConcurrentHashMap<>();


	public MAPResourceAdaptor() {
		this.mapProvider = new MAPProviderWrapper(this);
	}

	// ////////////////
	// RA callbacks //
	// ////////////////
	public void activityEnded(ActivityHandle activityHandle) {
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine("Activity with handle " + activityHandle + " ended");
		}
		MAPDialogActivityHandle mdah = (MAPDialogActivityHandle) activityHandle;
		final MAPDialogWrapper dw = mdah.getActivity();
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

	public void eventProcessingFailed(ActivityHandle handle, FireableEventType eventType, Object event,
			Address address, ReceivableService service, int flags, FailureReason reason) {
		if (tracer.isFineEnabled())
			tracer.fine("eventProcessingFailed:" + eventType + ":" + handle);
		// used to inform the resource adaptor object that the specified Event
		// could not be processed successfully by the SLEE.

		if (eventType.getEventType().getName().equals(DialogTimeout.EVENT_TYPE_NAME)) {
			MAPDialogActivityHandle dah = (MAPDialogActivityHandle) handle;
			MAPDialogWrapper dw = dah.getActivity();
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
			MAPDialogActivityHandle dah = (MAPDialogActivityHandle) handle;
			MAPDialogWrapper dw = dah.getActivity();
			if (dw != null && !dw.checkDialogTimeoutProcKeeped())
				releaseDialog(dw);
		}
	}

	public void eventUnreferenced(ActivityHandle handle, FireableEventType eventType, Object event, Address address,
			ReceivableService service, int flags) {
		if (tracer.isFineEnabled())
			tracer.fine("eventUnreferenced:" + eventType + ":" + handle);
		// used to inform the Resource Adaptor that the SLEE no longer
		// references an Event object which was previously fired by the
		// resource adaptor object.
	}

	private void releaseDialog(MAPDialogWrapper dw) {
		dw.release();
	}


	@Override
	public Object getActivity(ActivityHandle handle) {
		if(! (handle instanceof MAPDialogActivityHandle))
			return null;

		MAPDialogActivityHandle mah=(MAPDialogActivityHandle)handle;
		if(mah.hasCachedActivity())
			return mah.getActivity();
		MAPDialogWrapper activity=retrieveMapDialogWrapper(mah.getDialogId());
		mah.setActivity(activity);
		mah.setRa(this);
		return activity;
	}

	public ActivityHandle getActivityHandle(Object activity) {
		if (activity instanceof MAPDialogWrapper) {
			final MAPDialogWrapper wrapper = ((MAPDialogWrapper) activity);
			if (wrapper.getRa() == this) {
				return wrapper.getActivityHandle();
			}
		}

		return null;
	}

	public Marshaler getMarshaler() {
		return marshaler;
	}

	public Object getResourceAdaptorInterface(String className) {
		return this.mapProvider;
	}

	public void queryLiveness(ActivityHandle activityHandle) {
		final MAPDialogActivityHandle handle = ((MAPDialogActivityHandle) activityHandle);
		final MAPDialogWrapper mapDialog = handle.getActivity();
		if (mapDialog == null || mapDialog.getWrappedDialog() == null
				|| mapDialog.getState() == MAPDialogState.EXPUNGED) {
			if(tracer.isFinestEnabled())
				tracer.finest("ending activity due to queryLiveness test "+((MAPDialogActivityHandle) activityHandle).getDialogId());
			sleeEndpoint.endActivity(handle);
		}
	}

	public void raActive() {

		try {
            ObjectName objectName = new ObjectName("org.mobicents.ss7:service=MAPSS7Service");
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
                object = ic.lookup(this.mapJndi);
				if (tracer.isInfoEnabled()) {
					tracer.info("Trying to get via JNDI: " + this.mapJndi + ", object: " + object);
				}
            }

            if (object instanceof MAPProvider) {
                this.realProvider = (MAPProvider) object;
				if (tracer.isInfoEnabled()) {
					tracer.info("Successfully connected to MAP service[" +
							this.realProvider.getClass().getCanonicalName() + "]");
				}
            } else {
				if (tracer.isSevereEnabled()) {
					tracer.severe("Failed of connecting to MAP service[org.mobicents.ss7:service=MAPSS7Service]");
				}
            }

			this.realProvider.addMAPDialogListener(this);

			this.realProvider.getMAPServiceMobility().addMAPServiceListener(this);
			this.realProvider.getMAPServiceCallHandling().addMAPServiceListener(this);
			this.realProvider.getMAPServiceOam().addMAPServiceListener(this);
			this.realProvider.getMAPServicePdpContextActivation().addMAPServiceListener(this);
			this.realProvider.getMAPServiceSupplementary().addMAPServiceListener(this);
			this.realProvider.getMAPServiceSms().addMAPServiceListener(this);
			this.realProvider.getMAPServiceLsm().addMAPServiceListener(this);

			this.sleeEndpoint = resourceAdaptorContext.getSleeEndpoint();

			this.realProvider.getMAPServiceMobility().acivate();
			this.realProvider.getMAPServiceCallHandling().acivate();
			this.realProvider.getMAPServiceOam().acivate();
			this.realProvider.getMAPServicePdpContextActivation().acivate();
			this.realProvider.getMAPServiceSupplementary().acivate();
			this.realProvider.getMAPServiceSms().acivate();
			this.realProvider.getMAPServiceLsm().acivate();

			this.mapProvider.setWrappedProvider(this.realProvider);
		} catch (Exception e) {
			this.tracer.severe("Failed to activate MAP RA ", e);
		}
	}

	public void raConfigurationUpdate(ConfigProperties properties) {
		raConfigure(properties);
	}

	public void raConfigure(ConfigProperties properties) {
		try {
			if (tracer.isInfoEnabled()) {
				tracer.info("Configuring MAP RA: " + this.resourceAdaptorContext.getEntityName());
			}
			this.mapJndi = (String) properties.getProperty(CONF_MAP_JNDI).getValue();
		} catch (Exception e) {
			tracer.severe("Configuring of MAP RA failed ", e);
		}
	}

	public void raInactive() {
		if (tracer.isFineEnabled())
			tracer.fine("raInactive");
		if (tracer.isFineEnabled()) {
			tracer.fine("MAP Resource Adaptor - inactivating");
		}

		this.realProvider.getMAPServiceMobility().deactivate();
		this.realProvider.getMAPServiceCallHandling().deactivate();
		this.realProvider.getMAPServiceOam().deactivate();
		this.realProvider.getMAPServicePdpContextActivation().deactivate();
		this.realProvider.getMAPServiceSupplementary().deactivate();
		this.realProvider.getMAPServiceLsm().deactivate();
		this.realProvider.getMAPServiceSms().deactivate();

		this.realProvider.getMAPServiceMobility().removeMAPServiceListener(this);
		this.realProvider.getMAPServiceCallHandling().removeMAPServiceListener(this);
		this.realProvider.getMAPServiceOam().removeMAPServiceListener(this);
		this.realProvider.getMAPServicePdpContextActivation().removeMAPServiceListener(this);
		this.realProvider.getMAPServiceSupplementary().removeMAPServiceListener(this);
		this.realProvider.getMAPServiceLsm().removeMAPServiceListener(this);
		this.realProvider.getMAPServiceSms().removeMAPServiceListener(this);

		this.realProvider.removeMAPDialogListener(this);

		if (tracer.isInfoEnabled()) {
			tracer.info("MAP Resource Adaptor entity inactive.");
		}
	}

	public void raStopping() {
		if (tracer.isFineEnabled()) {
			tracer.fine("raStopping");
			tracer.fine("Ending all activities");

		}
		// for (CAPDialog a : activities.values()) {
		// endActivity(a);
		// }

		if (tracer.isFineEnabled()) {
			tracer.fine("MAP Resource Adaptor entity stopping.");
		}
	}

	public void raUnconfigure() {
		if (tracer.isFineEnabled())
			tracer.fine("raUnconfigure");

		this.mapJndi = null;

		if (tracer.isFineEnabled()) {
			tracer.fine("MAP Resource Adaptor unconfigured.");
		}
	}

	public void raVerifyConfiguration(ConfigProperties properties) throws InvalidConfigurationException {
		try {

			if (tracer.isInfoEnabled()) {
				tracer.info("Verifying configuring MAPRA: " + this.resourceAdaptorContext.getEntityName());
			}

			this.mapJndi = (String) properties.getProperty(CONF_MAP_JNDI).getValue();
			if (this.mapJndi == null) {
				throw new InvalidConfigurationException("MAP JNDI lookup name cannot be null");
			}

		} catch (Exception e) {
			throw new InvalidConfigurationException("Failed to test configuration options!", e);
		}

		if (tracer.isInfoEnabled()) {
			tracer.info("MAP Resource Adaptor configuration verified.");
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
		this.tracer = resourceAdaptorContext.getTracer(MAPResourceAdaptor.class.getSimpleName());
		this.sleeEndpoint = raContext.getSleeEndpoint();

		this.eventIdCache = new EventIDCache(this.tracer);
	}

	public void unsetResourceAdaptorContext() {
		this.resourceAdaptorContext = null;
	}

	// //////////////////
	// Helper methods //
	// //////////////////
	public void startActivity(MAPDialogWrapper mapDialogWrapper) throws ActivityAlreadyExistsException,
			NullPointerException, IllegalStateException, SLEEException, StartActivityException {
		this.sleeEndpoint.startActivity(mapDialogWrapper.getActivityHandle(), mapDialogWrapper, ACTIVITY_FLAGS);
	}

	public void startSuspendedActivity(MAPDialogWrapper mapDialogWrapper) throws ActivityAlreadyExistsException,
			NullPointerException, IllegalStateException, SLEEException, StartActivityException {
		this.sleeEndpoint.startActivitySuspended(mapDialogWrapper.getActivityHandle(), mapDialogWrapper,
				ActivityFlags.REQUEST_ENDED_CALLBACK);
	}

	// sending mapDialog.abort() and then endActivity()
	private void endActivity(MAPDialogWrapper mapDialog) {
		if (tracer.isFineEnabled())
			tracer.fine("endActivity:" + mapDialog);

		MAPDialogActivityHandle mapDialogActHndl = mapDialog.getActivityHandle();
		if (mapDialogActHndl == null) {
			if (this.tracer.isWarningEnabled()) {
				this.tracer.warning("Activity ended but no MAPDialogActivityHandle found for Dialog ID, ignoring "
						+ mapDialog.getLocalDialogId());
			}
		} else {
			try {
				MAPUserAbortChoice ach = this.realProvider.getMAPParameterFactory().createMAPUserAbortChoice();
				ach.setUserSpecificReason();
				mapDialog.abort(ach);
			} catch (MAPException e) {
				tracer.warning("Cannot abort dialog ", e);
			}
			this.sleeEndpoint.endActivity(mapDialogActHndl);
			if (tracer.isFineEnabled())
				this.tracer.fine("Activity marked to be ended:" + mapDialog.getLocalDialogId());
		}
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

	private MAPDialogActivityHandle onEvent(String eventName, MAPDialogWrapper dw, MAPEvent event) {
		return onEvent(eventName, dw, event, EventFlags.NO_FLAGS);
	}

	private MAPDialogActivityHandle onEvent(String eventName, MAPDialogWrapper dw, MAPEvent event, int flags) {
		if (dw == null) {
			this.tracer.severe(String.format("Firing %s but MAPDialogWrapper is null", eventName));
			return null;
		}

		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Firing %s for DialogId=%d", eventName, dw.getWrappedDialog()
					.getLocalDialogId()));
		}
		storeMapDialogWrapper(dw);
		this.fireEvent(eventName, dw.getActivityHandle(), event, flags);
		return dw.getActivityHandle();
	}

	// ////////////////////
	// Dialog callbacks //
	// ////////////////////
	/**
	 * {@inheritDoc}
	 */
	public void onDialogAccept(MAPDialog mapDialog, MAPExtensionContainer extension) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogAccept dialogAccept = new DialogAccept(mapDialogWrapper, extension);
		onEvent(dialogAccept.getEventTypeName(), mapDialogWrapper, dialogAccept);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogClose(MAPDialog mapDialog) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogClose dialogClose = new DialogClose(mapDialogWrapper);
		MAPDialogActivityHandle handle = onEvent(dialogClose.getEventTypeName(), mapDialogWrapper, dialogClose);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogDelimiter(MAPDialog mapDialog) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogDelimiter dialogDelimiter = new DialogDelimiter(mapDialogWrapper);
		onEvent(dialogDelimiter.getEventTypeName(), mapDialogWrapper, dialogDelimiter);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogNotice(MAPDialog mapDialog, MAPNoticeProblemDiagnostic noticeProblemDiagnostic) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogNotice dialogNotice = new DialogNotice(mapDialogWrapper, noticeProblemDiagnostic);
		onEvent(dialogNotice.getEventTypeName(), mapDialogWrapper, dialogNotice);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogProviderAbort(MAPDialog mapDialog, MAPAbortProviderReason abortProviderReason,
			MAPAbortSource abortSource, MAPExtensionContainer extensionContainer) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogProviderAbort dialogProviderAbort = new DialogProviderAbort(mapDialogWrapper, abortProviderReason,
				abortSource, extensionContainer);
		MAPDialogActivityHandle handle = onEvent(dialogProviderAbort.getEventTypeName(), mapDialogWrapper,
				dialogProviderAbort);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogReject(MAPDialog mapDialog, MAPRefuseReason refuseReason,
			ApplicationContextName alternativeApplicationContext, MAPExtensionContainer extensionContainer) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogReject dialogReject = new DialogReject(mapDialogWrapper, refuseReason, alternativeApplicationContext,
				extensionContainer);
		MAPDialogActivityHandle handle = onEvent(dialogReject.getEventTypeName(), mapDialogWrapper, dialogReject);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);
	}

	private void handleDialogRequest(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
			MAPExtensionContainer extensionContainer, AddressString eriMsisdn, AddressString eriVlrNo) {
		try {

			if (this.tracer.isFineEnabled()) {
				this.tracer.fine(String.format("Received onDialogRequest id=%d ", mapDialog.getLocalDialogId()));
			}

			MAPDialogActivityHandle activityHandle = new MAPDialogActivityHandle(this,mapDialog.getLocalDialogId());
			MAPDialogWrapper mapDialogWrapper = null;

			if (mapDialog instanceof MAPDialogMobility) {
				mapDialogWrapper = new MAPDialogMobilityWrapper((MAPDialogMobility) mapDialog, activityHandle, this);
			} else if (mapDialog instanceof MAPDialogOam) {
				mapDialogWrapper = new MAPDialogOamWrapper((MAPDialogOam) mapDialog, activityHandle, this);
			} else if (mapDialog instanceof MAPDialogCallHandling) {
				mapDialogWrapper = new MAPDialogCallHandlingWrapper((MAPDialogCallHandling) mapDialog, activityHandle,
						this);
			} else if (mapDialog instanceof MAPDialogPdpContextActivation) {
				mapDialogWrapper = new MAPDialogPdpContextActivationWrapper((MAPDialogPdpContextActivation) mapDialog,
						activityHandle, this);
			} else if (mapDialog instanceof MAPDialogSupplementary) {
				mapDialogWrapper = new MAPDialogSupplementaryWrapper((MAPDialogSupplementary) mapDialog,
						activityHandle, this);
			} else if (mapDialog instanceof MAPDialogSms) {
				mapDialogWrapper = new MAPDialogSmsWrapper((MAPDialogSms) mapDialog, activityHandle, this);
			} else if (mapDialog instanceof MAPDialogLsm) {
				mapDialogWrapper = new MAPDialogLsmWrapper((MAPDialogLsm) mapDialog, activityHandle, this);
			} else {
				this.tracer.severe(String.format("Received onDialogRequest id=%d for unknown MAPDialog class=%s",
						mapDialog.getLocalDialogId(), mapDialog.getClass().getName()));
				return;
			}

			DialogRequest event = new DialogRequest(mapDialogWrapper, destReference, origReference, extensionContainer,
					eriMsisdn, eriVlrNo);
			storeMapDialogWrapper(mapDialogWrapper );
			this.startActivity(mapDialogWrapper);
			this.fireEvent(event.getEventTypeName(), mapDialogWrapper.getActivityHandle(), event, EventFlags.NO_FLAGS);


		} catch (Exception e) {
			this.tracer.severe(String.format(
					"Exception when trying to fire event DIALOG_REQUEST for received DialogRequest=%s ", mapDialog), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogRequest(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
			MAPExtensionContainer extensionContainer) {
		this.handleDialogRequest(mapDialog, destReference, origReference, extensionContainer, null, null);
	}

	public void onDialogRequestEricsson(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
	        AddressString eriMsisdn, AddressString eriVlrNo) {
		this.handleDialogRequest(mapDialog, destReference, origReference, null, eriMsisdn, eriVlrNo);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogUserAbort(MAPDialog mapDialog, MAPUserAbortChoice userReason,
			MAPExtensionContainer extensionContainer) {

		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogUserAbort dialogUserAbort = new DialogUserAbort(mapDialogWrapper, userReason, extensionContainer);
		MAPDialogActivityHandle handle = onEvent(dialogUserAbort.getEventTypeName(), mapDialogWrapper, dialogUserAbort);

		// End Activity
		// if (handle != null)
		// this.sleeEndpoint.endActivity(handle);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogRelease(MAPDialog mapDialog) {
		if(tracer.isFineEnabled())
			tracer.fine("onDialogRelease: "+mapDialog.getLocalDialogId());
		try {
			Long dlgId=mapDialog.getLocalDialogId();
			MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(dlgId);
			DialogRelease dialogRelease = new DialogRelease(mapDialogWrapper);
			MAPDialogActivityHandle handle = onEvent(dialogRelease.getEventTypeName(), mapDialogWrapper, dialogRelease);
			// End Activity
			if(handle!=null)
			this.sleeEndpoint.endActivity(handle);
			else
				tracer.warning("Failed to release dialog - dialog does not exist "+mapDialog.getLocalDialogId());
			replicateData.remove(dlgId);
			localData.remove(dlgId);
		} catch (Exception e) {
			this.tracer.severe(String.format(
					"onDialogRelease : Exception while trying to end activity for MAPDialog=%s", mapDialog), e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void onDialogTimeout(MAPDialog mapDialog) {

		// TODO: done like that, since we want to process dialog callbacks
		// before we fire event.
		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Rx : onDialogTimeout for DialogId=%d", mapDialog.getLocalDialogId()));
		}

		mapDialog.keepAlive();

		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		DialogTimeout dt = new DialogTimeout(mapDialogWrapper);

		if (mapDialogWrapper == null) {
			this.tracer
					.severe(String.format("Firing %s but MAPDialogWrapper userObject is null", dt.getEventTypeName()));
			mapDialog.release();
			return;
		}

		mapDialogWrapper.startDialogTimeoutProc();

		if (this.tracer.isFineEnabled()) {
			this.tracer.fine(String.format("Firing %s for DialogId=%d", dt.getEventTypeName(), mapDialogWrapper
					.getWrappedDialog().getLocalDialogId()));
		}

		boolean fired = fireEvent(dt.getEventTypeName(), mapDialogWrapper.getActivityHandle(), dt,
				(EventFlags.REQUEST_PROCESSING_SUCCESSFUL_CALLBACK | EventFlags.REQUEST_PROCESSING_FAILED_CALLBACK));

		if (!fired) {
			// If event filtered out, lets release Dialog
			mapDialogWrapper.release();
		}
	}

	// ///////////////////////
	// Component callbacks //
	// ///////////////////////
	/**
	 * {@inheritDoc}
	 */
	public void onInvokeTimeout(MAPDialog mapDialog, Long invokeId) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		InvokeTimeout invokeTimeout = new InvokeTimeout(mapDialogWrapper, invokeId);
		onEvent(invokeTimeout.getEventTypeName(), mapDialogWrapper, invokeTimeout);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onErrorComponent(MAPDialog mapDialog, Long invokeId, MAPErrorMessage mapErrorMessage) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		ErrorComponent errorComponent = new ErrorComponent(mapDialogWrapper, invokeId, mapErrorMessage);
		onEvent(errorComponent.getEventTypeName(), mapDialogWrapper, errorComponent);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onRejectComponent(MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
		MAPDialogWrapper mapDialogWrapper = retrieveMapDialogWrapper(mapDialog.getLocalDialogId());
		RejectComponent rejectComponent = new RejectComponent(mapDialogWrapper, invokeId, problem, isLocalOriginated);
		onEvent(rejectComponent.getEventTypeName(), mapDialogWrapper, rejectComponent);
	}

	// ///////////////
	// SERVICE : MOBILITY
	// //////////////

	// -- Location Management Service
	public void onUpdateLocationRequest(UpdateLocationRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		UpdateLocationRequestWrapper event = new UpdateLocationRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	public void onUpdateLocationResponse(UpdateLocationResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		UpdateLocationResponseWrapper event = new UpdateLocationResponseWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onCancelLocationRequest(CancelLocationRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		CancelLocationRequestWrapper event = new CancelLocationRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onCancelLocationResponse(CancelLocationResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		CancelLocationResponseWrapper event = new CancelLocationResponseWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onSendIdentificationRequest(SendIdentificationRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		SendIdentificationRequestWrapper event = new SendIdentificationRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onSendIdentificationResponse(SendIdentificationResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		SendIdentificationResponseWrapper event = new SendIdentificationResponseWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

    @Override
    public void onPurgeMSRequest(PurgeMSRequest ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        PurgeMSRequestWrapper event = new PurgeMSRequestWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onPurgeMSResponse(PurgeMSResponse ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        PurgeMSResponseWrapper event = new PurgeMSResponseWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

	@Override
	public void onUpdateGprsLocationRequest(UpdateGprsLocationRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		UpdateGprsLocationRequestWrapper event = new UpdateGprsLocationRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onUpdateGprsLocationResponse(UpdateGprsLocationResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper)retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		UpdateGprsLocationResponseWrapper event = new UpdateGprsLocationResponseWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	// --- Handover services

	// -- Authentication management services
	public void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		SendAuthenticationInfoRequestWrapper event = new SendAuthenticationInfoRequestWrapper(mapDialogMobilityWrapper,
				ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	public void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		SendAuthenticationInfoResponseWrapper event = new SendAuthenticationInfoResponseWrapper(
				mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

    @Override
    public void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        AuthenticationFailureReportRequestWrapper event = new AuthenticationFailureReportRequestWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        AuthenticationFailureReportResponseWrapper event = new AuthenticationFailureReportResponseWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    // --- Mobility - Fault recovery
    @Override
    public void onResetRequest(ResetRequest ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        ResetRequestWrapper event = new ResetRequestWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        ForwardCheckSSIndicationRequestWrapper event = new ForwardCheckSSIndicationRequestWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onRestoreDataRequest(RestoreDataRequest ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        RestoreDataRequestWrapper event = new RestoreDataRequestWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onRestoreDataResponse(RestoreDataResponse ind) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        RestoreDataResponseWrapper event = new RestoreDataResponseWrapper(mapDialogMobilityWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

	// --- IMEI management services
	public void onCheckImeiRequest(CheckImeiRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		CheckImeiRequestWrapper event = new CheckImeiRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	public void onCheckImeiResponse(CheckImeiResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		CheckImeiResponseWrapper event = new CheckImeiResponseWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	// -- Subscriber Management services
	@Override
	public void onInsertSubscriberDataRequest(InsertSubscriberDataRequest ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		InsertSubscriberDataRequestWrapper event = new InsertSubscriberDataRequestWrapper(mapDialogMobilityWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	@Override
	public void onInsertSubscriberDataResponse(InsertSubscriberDataResponse ind) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		InsertSubscriberDataResponseWrapper event = new InsertSubscriberDataResponseWrapper(mapDialogMobilityWrapper,
				ind);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

    @Override
    public void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest request) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        DeleteSubscriberDataRequestWrapper event = new DeleteSubscriberDataRequestWrapper(mapDialogMobilityWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse request) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        DeleteSubscriberDataResponseWrapper event = new DeleteSubscriberDataResponseWrapper(mapDialogMobilityWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

	// --- Fault recovery services

	// -- Subscriber Information services
	public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest anyTimeInterrogationRequest) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper)retrieveMapDialogWrapper(anyTimeInterrogationRequest.getMAPDialog().getLocalDialogId());
		AnyTimeInterrogationRequestWrapper event = new AnyTimeInterrogationRequestWrapper(mapDialogMobilityWrapper,
				anyTimeInterrogationRequest);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

	public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse anyTimeInterrogationResponse) {
		MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(anyTimeInterrogationResponse.getMAPDialog().getLocalDialogId());
		AnyTimeInterrogationResponseWrapper event = new AnyTimeInterrogationResponseWrapper(mapDialogMobilityWrapper,
				anyTimeInterrogationResponse);
		onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
	}

    @Override
    public void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest request) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        ProvideSubscriberInfoRequestWrapper event = new ProvideSubscriberInfoRequestWrapper(mapDialogMobilityWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse response) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        ProvideSubscriberInfoResponseWrapper event = new ProvideSubscriberInfoResponseWrapper(mapDialogMobilityWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

	// ///////////////
	// SERVICE : OAM
	// //////////////
    @Override
    public void onSendImsiRequest(SendImsiRequest request) {
        MAPDialogOamWrapper mapDialogOamWrapper = (MAPDialogOamWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        SendImsiRequestWrapper event = new SendImsiRequestWrapper(mapDialogOamWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogOamWrapper, event);
    }

    @Override
    public void onSendImsiResponse(SendImsiResponse request) {
        MAPDialogOamWrapper mapDialogOamWrapper = (MAPDialogOamWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        SendImsiResponseWrapper event = new SendImsiResponseWrapper(mapDialogOamWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogOamWrapper, event);
    }

    @Override
    public void onActivateTraceModeRequest_Oam(ActivateTraceModeRequest_Oam request) {
        MAPDialogOamWrapper mapDialogOamWrapper = (MAPDialogOamWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        ActivateTraceModeRequest_OamWrapper event = new ActivateTraceModeRequest_OamWrapper(mapDialogOamWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogOamWrapper, event);
    }

    @Override
    public void onActivateTraceModeResponse_Oam(ActivateTraceModeResponse_Oam response) {
        MAPDialogOamWrapper mapDialogOamWrapper = (MAPDialogOamWrapper) retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        ActivateTraceModeResponse_OamWrapper event = new ActivateTraceModeResponse_OamWrapper(mapDialogOamWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogOamWrapper, event);
    }

    @Override
    public void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility request) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        ActivateTraceModeRequest_MobilityWrapper event = new ActivateTraceModeRequest_MobilityWrapper(mapDialogMobilityWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }

    @Override
    public void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility response) {
        MAPDialogMobilityWrapper mapDialogMobilityWrapper = (MAPDialogMobilityWrapper) retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        ActivateTraceModeResponse_MobilityWrapper event = new ActivateTraceModeResponse_MobilityWrapper(mapDialogMobilityWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogMobilityWrapper, event);
    }


    // ///////////////
	// SERVICE : Call Handling
	// //////////////

	@Override
	public void onSendRoutingInformationRequest(SendRoutingInformationRequest ind) {
		MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper)retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		SendRoutingInformationRequestWrapper event = new SendRoutingInformationRequestWrapper(
				mapDialogCallHandlingWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
	}

	@Override
	public void onSendRoutingInformationResponse(SendRoutingInformationResponse ind) {
		MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper) ind.getMAPDialog()
				.getUserObject();
		SendRoutingInformationResponseWrapper event = new SendRoutingInformationResponseWrapper(
				mapDialogCallHandlingWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
	}

	@Override
	public void onProvideRoamingNumberRequest(ProvideRoamingNumberRequest ind) {
		MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper) ind.getMAPDialog()
				.getUserObject();
		ProvideRoamingNumberRequestWrapper event = new ProvideRoamingNumberRequestWrapper(mapDialogCallHandlingWrapper,
				ind);
		onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
	}

	@Override
	public void onProvideRoamingNumberResponse(ProvideRoamingNumberResponse ind) {
		MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper)retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
		ProvideRoamingNumberResponseWrapper event = new ProvideRoamingNumberResponseWrapper(
				mapDialogCallHandlingWrapper, ind);
		onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
	}

    @Override
    public void onIstCommandRequest(IstCommandRequest ind) {
        MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper)retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        IstCommandRequestWrapper event = new IstCommandRequestWrapper(mapDialogCallHandlingWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
    }

    @Override
    public void onIstCommandResponse(IstCommandResponse ind) {
        MAPDialogCallHandlingWrapper mapDialogCallHandlingWrapper = (MAPDialogCallHandlingWrapper)retrieveMapDialogWrapper(ind.getMAPDialog().getLocalDialogId());
        IstCommandResponseWrapper event = new IstCommandResponseWrapper(mapDialogCallHandlingWrapper, ind);
        onEvent(event.getEventTypeName(), mapDialogCallHandlingWrapper, event);
    }

	// /////////////////////////
	// SERVICE: Suplementary //
	// /////////////////////////

    @Override
    public void onRegisterSSRequest(RegisterSSRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        RegisterSSRequestWrapper event = new RegisterSSRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onRegisterSSResponse(RegisterSSResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        RegisterSSResponseWrapper event = new RegisterSSResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onEraseSSRequest(EraseSSRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        EraseSSRequestWrapper event = new EraseSSRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onEraseSSResponse(EraseSSResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        EraseSSResponseWrapper event = new EraseSSResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onActivateSSRequest(ActivateSSRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        ActivateSSRequestWrapper event = new ActivateSSRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onActivateSSResponse(ActivateSSResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        ActivateSSResponseWrapper event = new ActivateSSResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onDeactivateSSRequest(DeactivateSSRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        DeactivateSSRequestWrapper event = new DeactivateSSRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onDeactivateSSResponse(DeactivateSSResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        DeactivateSSResponseWrapper event = new DeactivateSSResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onInterrogateSSRequest(InterrogateSSRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        InterrogateSSRequestWrapper event = new InterrogateSSRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onInterrogateSSResponse(InterrogateSSResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        InterrogateSSResponseWrapper event = new InterrogateSSResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onGetPasswordRequest(GetPasswordRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        GetPasswordRequestWrapper event = new GetPasswordRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onGetPasswordResponse(GetPasswordResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        GetPasswordResponseWrapper event = new GetPasswordResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onRegisterPasswordRequest(RegisterPasswordRequest request) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        RegisterPasswordRequestWrapper event = new RegisterPasswordRequestWrapper(mapDialogSupplementaryWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

    @Override
    public void onRegisterPasswordResponse(RegisterPasswordResponse response) {
        MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        RegisterPasswordResponseWrapper event = new RegisterPasswordResponseWrapper(mapDialogSupplementaryWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
    }

	/**
	 * {@inheritDoc}
	 */
	public void onProcessUnstructuredSSRequest(ProcessUnstructuredSSRequest processUnstrSSInd) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) processUnstrSSInd
				.getMAPDialog().getUserObject();
		ProcessUnstructuredSSRequestWrapper event = new ProcessUnstructuredSSRequestWrapper(
				mapDialogSupplementaryWrapper, processUnstrSSInd);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onProcessUnstructuredSSResponse(ProcessUnstructuredSSResponse processUnstructuredSSResponse) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) processUnstructuredSSResponse
				.getMAPDialog().getUserObject();
		ProcessUnstructuredSSResponseWrapper event = new ProcessUnstructuredSSResponseWrapper(
				mapDialogSupplementaryWrapper, processUnstructuredSSResponse);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onUnstructuredSSRequest(UnstructuredSSRequest unstructuredSSRequest) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) unstructuredSSRequest
				.getMAPDialog().getUserObject();
		UnstructuredSSRequestWrapper event = new UnstructuredSSRequestWrapper(mapDialogSupplementaryWrapper,
				unstructuredSSRequest);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onUnstructuredSSResponse(UnstructuredSSResponse unstructuredSSResponse) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) unstructuredSSResponse
				.getMAPDialog().getUserObject();
		UnstructuredSSResponseWrapper event = new UnstructuredSSResponseWrapper(mapDialogSupplementaryWrapper,
				unstructuredSSResponse);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onUnstructuredSSNotifyRequest(UnstructuredSSNotifyRequest unstructuredSSNotifyRequest) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) unstructuredSSNotifyRequest
				.getMAPDialog().getUserObject();
		UnstructuredSSNotifyRequestWrapper event = new UnstructuredSSNotifyRequestWrapper(
				mapDialogSupplementaryWrapper, unstructuredSSNotifyRequest);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	public void onUnstructuredSSNotifyResponse(UnstructuredSSNotifyResponse unstructuredSSNotifyResponse) {
		MAPDialogSupplementaryWrapper mapDialogSupplementaryWrapper = (MAPDialogSupplementaryWrapper) unstructuredSSNotifyResponse
				.getMAPDialog().getUserObject();
		UnstructuredSSNotifyResponseWrapper event = new UnstructuredSSNotifyResponseWrapper(
				mapDialogSupplementaryWrapper, unstructuredSSNotifyResponse);
		onEvent(event.getEventTypeName(), mapDialogSupplementaryWrapper, event);
	}

	// ////////////////
	// SERVICE: SMS //
	// ////////////////

	public void onForwardShortMessageRequest(ForwardShortMessageRequest forwardShortMessageRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(forwardShortMessageRequest.getMAPDialog().getLocalDialogId());
		ForwardShortMessageRequestWrapper event = new ForwardShortMessageRequestWrapper(mapDialogSmsWrapper,
				forwardShortMessageRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onForwardShortMessageResponse(ForwardShortMessageResponse forwardShortMessageResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(forwardShortMessageResponse.getMAPDialog().getLocalDialogId());
		ForwardShortMessageResponseWrapper event = new ForwardShortMessageResponseWrapper(mapDialogSmsWrapper,
				forwardShortMessageResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);

	}

	public void onMoForwardShortMessageRequest(MoForwardShortMessageRequest moForwardShortMessageRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(moForwardShortMessageRequest.getMAPDialog().getLocalDialogId());
		MoForwardShortMessageRequestWrapper event = new MoForwardShortMessageRequestWrapper(mapDialogSmsWrapper,
				moForwardShortMessageRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);

	}

	public void onMoForwardShortMessageResponse(MoForwardShortMessageResponse moForwardShortMessageResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(moForwardShortMessageResponse.getMAPDialog().getLocalDialogId());
		MoForwardShortMessageResponseWrapper event = new MoForwardShortMessageResponseWrapper(mapDialogSmsWrapper,
				moForwardShortMessageResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onMtForwardShortMessageRequest(MtForwardShortMessageRequest mtForwardShortMessageRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(mtForwardShortMessageRequest.getMAPDialog().getLocalDialogId());
		MtForwardShortMessageRequestWrapper event = new MtForwardShortMessageRequestWrapper(mapDialogSmsWrapper,
				mtForwardShortMessageRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onMtForwardShortMessageResponse(MtForwardShortMessageResponse mtForwardShortMessageResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(mtForwardShortMessageResponse.getMAPDialog().getLocalDialogId());
		MtForwardShortMessageResponseWrapper event = new MtForwardShortMessageResponseWrapper(mapDialogSmsWrapper,
				mtForwardShortMessageResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onSendRoutingInfoForSMRequest(SendRoutingInfoForSMRequest sendRoutingInfoForSMRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(sendRoutingInfoForSMRequest.getMAPDialog().getLocalDialogId());
		SendRoutingInfoForSMRequestWrapper event = new SendRoutingInfoForSMRequestWrapper(mapDialogSmsWrapper,
				sendRoutingInfoForSMRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onSendRoutingInfoForSMResponse(SendRoutingInfoForSMResponse sendRoutingInfoForSmResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(sendRoutingInfoForSmResponse.getMAPDialog().getLocalDialogId());
		SendRoutingInfoForSMResponseWrapper event = new SendRoutingInfoForSMResponseWrapper(mapDialogSmsWrapper,
				sendRoutingInfoForSmResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onReportSMDeliveryStatusRequest(ReportSMDeliveryStatusRequest reportSmDeliveryStatusRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(reportSmDeliveryStatusRequest.getMAPDialog().getLocalDialogId());
		ReportSMDeliveryStatusRequestWrapper event = new ReportSMDeliveryStatusRequestWrapper(mapDialogSmsWrapper,
				reportSmDeliveryStatusRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);

	}

	public void onReportSMDeliveryStatusResponse(ReportSMDeliveryStatusResponse reportSmDeliveryStatusResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(reportSmDeliveryStatusResponse.getMAPDialog().getLocalDialogId());
		ReportSMDeliveryStatusResponseWrapper event = new ReportSMDeliveryStatusResponseWrapper(mapDialogSmsWrapper,
				reportSmDeliveryStatusResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);

	}

	public void onInformServiceCentreRequest(InformServiceCentreRequest informServiCecentreRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(informServiCecentreRequest.getMAPDialog().getLocalDialogId());
		InformServiceCentreRequestWrapper event = new InformServiceCentreRequestWrapper(mapDialogSmsWrapper,
				informServiCecentreRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onAlertServiceCentreRequest(AlertServiceCentreRequest alertServiCecentreRequest) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(alertServiCecentreRequest.getMAPDialog().getLocalDialogId());
		AlertServiceCentreRequestWrapper event = new AlertServiceCentreRequestWrapper(mapDialogSmsWrapper,
				alertServiCecentreRequest);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

	public void onAlertServiceCentreResponse(AlertServiceCentreResponse alertServiCecentreResponse) {
		MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(alertServiCecentreResponse.getMAPDialog().getLocalDialogId());
		AlertServiceCentreResponseWrapper event = new AlertServiceCentreResponseWrapper(mapDialogSmsWrapper,
				alertServiCecentreResponse);
		onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
	}

    @Override
    public void onReadyForSMRequest(ReadyForSMRequest request) {
        MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        ReadyForSMRequestWrapper event = new ReadyForSMRequestWrapper(mapDialogSmsWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
    }

    @Override
    public void onReadyForSMResponse(ReadyForSMResponse response) {
        MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        ReadyForSMResponseWrapper event = new ReadyForSMResponseWrapper(mapDialogSmsWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
    }

    @Override
    public void onNoteSubscriberPresentRequest(NoteSubscriberPresentRequest request) {
        MAPDialogSmsWrapper mapDialogSmsWrapper = (MAPDialogSmsWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        NoteSubscriberPresentRequestWrapper event = new NoteSubscriberPresentRequestWrapper(mapDialogSmsWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogSmsWrapper, event);
    }

	// ////////////////
	// SERVICE: Network-Requested PDP Context Activation services
	// ////////////////
    @Override
    public void onSendRoutingInfoForGprsRequest(SendRoutingInfoForGprsRequest request) {
        MAPDialogPdpContextActivationWrapper mapDialogPdpContextActivationWrapper = (MAPDialogPdpContextActivationWrapper)retrieveMapDialogWrapper(request.getMAPDialog().getLocalDialogId());
        SendRoutingInfoForGprsRequestWrapper event = new SendRoutingInfoForGprsRequestWrapper(mapDialogPdpContextActivationWrapper, request);
        onEvent(event.getEventTypeName(), mapDialogPdpContextActivationWrapper, event);
    }

    @Override
    public void onSendRoutingInfoForGprsResponse(SendRoutingInfoForGprsResponse response) {
        MAPDialogPdpContextActivationWrapper mapDialogPdpContextActivationWrapper = (MAPDialogPdpContextActivationWrapper)retrieveMapDialogWrapper(response.getMAPDialog().getLocalDialogId());
        SendRoutingInfoForGprsResponseWrapper event = new SendRoutingInfoForGprsResponseWrapper(mapDialogPdpContextActivationWrapper, response);
        onEvent(event.getEventTypeName(), mapDialogPdpContextActivationWrapper, event);
    }

	// ////////////////
	// SERVICE: LSM //
	// ////////////////

	/**
	 * {@inheritDoc}
	 */
	public void onProvideSubscriberLocationRequest(ProvideSubscriberLocationRequest provideSubscriberLocationRequest) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper)retrieveMapDialogWrapper(provideSubscriberLocationRequest.getMAPDialog().getLocalDialogId());
		ProvideSubscriberLocationRequestWrapper event = new ProvideSubscriberLocationRequestWrapper(
				mapDialogLsmWrapper, provideSubscriberLocationRequest);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onProvideSubscriberLocationResponse(ProvideSubscriberLocationResponse provideSubscriberLocationResponse) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper) provideSubscriberLocationResponse
				.getMAPDialog().getUserObject();
		ProvideSubscriberLocationResponseWrapper event = new ProvideSubscriberLocationResponseWrapper(
				mapDialogLsmWrapper, provideSubscriberLocationResponse);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onSendRoutingInfoForLCSRequest(SendRoutingInfoForLCSRequest sendRoutingInfoForLCSRequest) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper)retrieveMapDialogWrapper(sendRoutingInfoForLCSRequest.getMAPDialog().getLocalDialogId());
		SendRoutingInfoForLCSRequestWrapper event = new SendRoutingInfoForLCSRequestWrapper(mapDialogLsmWrapper,
				sendRoutingInfoForLCSRequest);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse sendRoutingInfoForLCSResponse) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper)retrieveMapDialogWrapper(sendRoutingInfoForLCSResponse.getMAPDialog().getLocalDialogId());
		SendRoutingInfoForLCSResponseWrapper event = new SendRoutingInfoForLCSResponseWrapper(mapDialogLsmWrapper,
				sendRoutingInfoForLCSResponse);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onSubscriberLocationReportRequest(SubscriberLocationReportRequest subscriberLocationReportRequest) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper)retrieveMapDialogWrapper(subscriberLocationReportRequest.getMAPDialog().getLocalDialogId());
		SubscriberLocationReportRequestWrapper event = new SubscriberLocationReportRequestWrapper(mapDialogLsmWrapper,
				subscriberLocationReportRequest);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);

	}

	/**
	 * {@inheritDoc}
	 */
	public void onSubscriberLocationReportResponse(SubscriberLocationReportResponse subscriberLocationReportResponse) {
		MAPDialogLsmWrapper mapDialogLsmWrapper = (MAPDialogLsmWrapper)retrieveMapDialogWrapper(subscriberLocationReportResponse.getMAPDialog().getLocalDialogId());
		SubscriberLocationReportResponseWrapper event = new SubscriberLocationReportResponseWrapper(
				mapDialogLsmWrapper, subscriberLocationReportResponse);
		onEvent(event.getEventTypeName(), mapDialogLsmWrapper, event);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.mobicents.protocols.ss7.map.api.MAPServiceListener#onMAPMessage(org
	 * .mobicents.protocols.ss7.map.api.MAPMessage)
	 */
	public void onMAPMessage(MAPMessage arg0) {
		// Do nothing
	}

	public void failOver(Long key) {
		if(tracer.isTraceable(TraceLevel.INFO))
			tracer.info("Cache reports MAPDialogWrapper failOver "+key);
	}

	public void dataRemoved(Long key) {
		if(tracer.isTraceable(TraceLevel.FINE))
			tracer.fine("Cache reports MAPDialogWrapper removed "+key);
		MAPDialogWrapper dw=localData.remove(key);
		if(dw!=null)
			dw.release();
	}
	public void storeMapDialogWrapper(MAPDialogWrapper w) {
		if(!w.getChanged())
			return;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream dos = new ObjectOutputStream(bos);
			dos.writeObject(w);
			localData.put(w.getLocalDialogId(), w);
			replicateData.put(w.getLocalDialogId(), bos.toByteArray());
			w.setChanged(false);
		} catch(Exception e) {
			tracer.severe("Failed to serialize "+w,e);
			throw new RuntimeException("Serialization failure",e);
		}
	}

	private MAPDialogWrapper retrieveMapDialogWrapper(Long dialogId) {
		MAPDialogWrapper wrapper=localData.get(dialogId);
		if(wrapper!=null)
			return wrapper;
		byte b[]=replicateData.get(dialogId);
		if(b!=null) {
			try {
				ObjectInputStream bis = new ObjectInputStream(new ByteArrayInputStream(b));
				wrapper=(MAPDialogWrapper)bis.readObject();
			} catch(Exception e) {
				tracer.severe("Failed to deserialize dialog "+dialogId,e);
			}
		}
		if(wrapper==null) {
			if(tracer.isInfoEnabled())
				tracer.info("Failed to retrieve dialog id : "+dialogId,new IllegalStateException());
			return null;
		}
		wrapper.restoreTransientData(this);
		localData.put(dialogId,wrapper);
		return wrapper;
	}


	public void setFaultTolerantResourceAdaptorContext(FaultTolerantResourceAdaptorContext<Long,byte[]> faultTolerantResourceAdaptorContext) {
		this.ftResourceAdaptorContext=faultTolerantResourceAdaptorContext;
		replicateData=this.ftResourceAdaptorContext.getReplicateData(true);
	}

	public void unsetFaultTolerantResourceAdaptorContext() {
		this.ftResourceAdaptorContext=null;
	}

	public <T extends MAPDialog> T getWrappedDialog(Long dialogId) {
		MAPDialog cdia=this.realProvider.getMAPDialog(dialogId);
		return (T)cdia;
	}

}
