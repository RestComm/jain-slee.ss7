package org.restcomm.slee.resource.map.service.lsm.wrappers;

import org.restcomm.protocols.ss7.map.api.service.lsm.LsmMessage;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.slee.resource.map.wrappers.MAPMessageWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class LsmMessageWrapper<T extends LsmMessage> extends MAPMessageWrapper<T> implements LsmMessage {

	public LsmMessageWrapper(MAPDialogLsmWrapper mapDialogWrapper, String eventTypeName, T wrappedEvent) {
		super(mapDialogWrapper, eventTypeName, wrappedEvent);
	}

	public MAPDialogLsm getMAPDialog() {
		return (MAPDialogLsmWrapper) this.mapDialogWrapper;
	}

}
