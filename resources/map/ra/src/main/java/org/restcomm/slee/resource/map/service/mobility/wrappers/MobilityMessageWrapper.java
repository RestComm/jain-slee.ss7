package org.restcomm.slee.resource.map.service.mobility.wrappers;

import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.slee.resource.map.wrappers.MAPMessageWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class MobilityMessageWrapper<T extends MobilityMessage> extends MAPMessageWrapper<T> implements MobilityMessage {

	public MobilityMessageWrapper(MAPDialogMobilityWrapper mapDialogWrapper, String eventTypeName, T wrappedEvent) {
		super(mapDialogWrapper, eventTypeName, wrappedEvent);
	}

	public MAPDialogMobility getMAPDialog() {
		return (MAPDialogMobilityWrapper) this.mapDialogWrapper;
	}

}
