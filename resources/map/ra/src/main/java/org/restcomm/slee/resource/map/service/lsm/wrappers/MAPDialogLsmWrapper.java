package org.restcomm.slee.resource.map.service.lsm.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaEventInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPriority;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPrivacyCheck;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationType;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.SLRArgExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.SupportedGADShapes;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.MAPResourceAdaptor;
import org.restcomm.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class MAPDialogLsmWrapper extends MAPDialogWrapper<MAPDialogLsm> implements MAPDialogLsm {

	public MAPDialogLsmWrapper(MAPDialogLsm wrappedDialog, MAPDialogActivityHandle activityHandle, MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public MAPDialogLsm getWrappedDialog() {
		return this.wrappedDialog;
	}

	public Long addProvideSubscriberLocationRequest(LocationType arg0, ISDNAddressString arg1, LCSClientID arg2, boolean arg3, IMSI arg4,
			ISDNAddressString arg5, LMSI arg6, IMEI arg7, LCSPriority arg8, LCSQoS arg9, MAPExtensionContainer arg10, SupportedGADShapes arg11, Integer arg12,
			Integer arg13, LCSCodeword arg14, LCSPrivacyCheck arg15, AreaEventInfo arg16, GSNAddress arg17, boolean arg18, PeriodicLDRInfo arg19,
			ReportingPLMNList arg20) throws MAPException {
		return this.wrappedDialog.addProvideSubscriberLocationRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20);
	}

	public Long addProvideSubscriberLocationRequest(int arg0, LocationType arg1, ISDNAddressString arg2, LCSClientID arg3, boolean arg4, IMSI arg5,
			ISDNAddressString arg6, LMSI arg7, IMEI arg8, LCSPriority arg9, LCSQoS arg10, MAPExtensionContainer arg11, SupportedGADShapes arg12, Integer arg13,
			Integer arg14, LCSCodeword arg15, LCSPrivacyCheck arg16, AreaEventInfo arg17, GSNAddress arg18, boolean arg19, PeriodicLDRInfo arg20,
			ReportingPLMNList arg21) throws MAPException {
		return this.wrappedDialog.addProvideSubscriberLocationRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21);
	}

	public void addProvideSubscriberLocationResponse(long arg0, ExtGeographicalInformation arg1, PositioningDataInformation arg2,
			UtranPositioningDataInfo arg3, Integer arg4, AddGeographicalInformation arg5, MAPExtensionContainer arg6, boolean arg7,
			CellGlobalIdOrServiceAreaIdOrLAI arg8, boolean arg9, AccuracyFulfilmentIndicator arg10, VelocityEstimate arg11, boolean arg12,
			GeranGANSSpositioningData arg13, UtranGANSSpositioningData arg14, ServingNodeAddress arg15) throws MAPException {
		this.wrappedDialog.addProvideSubscriberLocationResponse(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14,
				arg15);
	}

	public Long addSendRoutingInfoForLCSRequest(ISDNAddressString arg0, SubscriberIdentity arg1,
			MAPExtensionContainer arg2) throws MAPException {
		return this.wrappedDialog.addSendRoutingInfoForLCSRequest(arg0, arg1, arg2);
	}

	public Long addSendRoutingInfoForLCSRequest(int arg0, ISDNAddressString arg1, SubscriberIdentity arg2,
			MAPExtensionContainer arg3) throws MAPException {
		return this.wrappedDialog.addSendRoutingInfoForLCSRequest(arg0, arg1, arg2, arg3);
	}

	public void addSendRoutingInfoForLCSResponse(long arg0, SubscriberIdentity arg1, LCSLocationInfo arg2, MAPExtensionContainer arg3, GSNAddress arg4,
			GSNAddress arg5, GSNAddress arg6, GSNAddress arg7) throws MAPException {
		this.wrappedDialog.addSendRoutingInfoForLCSResponse(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	public Long addSubscriberLocationReportRequest(LCSEvent arg0, LCSClientID arg1, LCSLocationInfo arg2, ISDNAddressString arg3, IMSI arg4, IMEI arg5,
			ISDNAddressString arg6, ISDNAddressString arg7, ExtGeographicalInformation arg8, Integer arg9, SLRArgExtensionContainer arg10,
			AddGeographicalInformation arg11, DeferredmtlrData arg12, Integer arg13, PositioningDataInformation arg14, UtranPositioningDataInfo arg15,
			CellGlobalIdOrServiceAreaIdOrLAI arg16, GSNAddress arg17, Integer arg18, boolean arg19, boolean arg20, AccuracyFulfilmentIndicator arg21,
			VelocityEstimate arg22, Integer arg23, PeriodicLDRInfo arg24, boolean arg25, GeranGANSSpositioningData arg26, UtranGANSSpositioningData arg27,
			ServingNodeAddress arg28) throws MAPException {
		return this.wrappedDialog.addSubscriberLocationReportRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28);
	}

	public Long addSubscriberLocationReportRequest(int arg0, LCSEvent arg1, LCSClientID arg2, LCSLocationInfo arg3, ISDNAddressString arg4, IMSI arg5,
			IMEI arg6, ISDNAddressString arg7, ISDNAddressString arg8, ExtGeographicalInformation arg9, Integer arg10, SLRArgExtensionContainer arg11,
			AddGeographicalInformation arg12, DeferredmtlrData arg13, Integer arg14, PositioningDataInformation arg15, UtranPositioningDataInfo arg16,
			CellGlobalIdOrServiceAreaIdOrLAI arg17, GSNAddress arg18, Integer arg19, boolean arg20, boolean arg21, AccuracyFulfilmentIndicator arg22,
			VelocityEstimate arg23, Integer arg24, PeriodicLDRInfo arg25, boolean arg26, GeranGANSSpositioningData arg27, UtranGANSSpositioningData arg28,
			ServingNodeAddress arg29) throws MAPException {
		return this.wrappedDialog.addSubscriberLocationReportRequest(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29);
	}

	public void addSubscriberLocationReportResponse(long arg0, ISDNAddressString arg1, ISDNAddressString arg2,
			MAPExtensionContainer arg3) throws MAPException {
		this.wrappedDialog.addSubscriberLocationReportResponse(arg0, arg1, arg2, arg3);
	}

	@Override
	public String toString() {
		return "MAPDialogLsmWrapper [wrappedDialog=" + wrappedDialog + "]";
	}

    @Override
    public Boolean isDoNotSendProtcolVersion() {
        return this.wrappedDialog.isDoNotSendProtcolVersion();
    }

    @Override
    public void setDoNotSendProtocolVersion(Boolean isSendProtocolVersion) {
        this.wrappedDialog.setDoNotSendProtocolVersion(isSendProtocolVersion);
    }

}
