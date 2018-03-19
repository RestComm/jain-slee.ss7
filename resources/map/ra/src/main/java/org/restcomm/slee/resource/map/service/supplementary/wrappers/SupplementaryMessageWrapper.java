package org.restcomm.slee.resource.map.service.supplementary.wrappers;

import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryMessage;
import org.restcomm.slee.resource.map.wrappers.MAPMessageWrapper;

/**
 * 
 * @author amit bhayani
 *
 */
public class SupplementaryMessageWrapper<T extends SupplementaryMessage> extends MAPMessageWrapper<T> implements
		SupplementaryMessage {

	public SupplementaryMessageWrapper(MAPDialogSupplementaryWrapper mapDialogWrapper, String eventTypeName,
			T wrappedEvent) {
		super(mapDialogWrapper, eventTypeName, wrappedEvent);
	}

	public MAPDialogSupplementary getMAPDialog() {
		return (MAPDialogSupplementaryWrapper) this.mapDialogWrapper;
	}

}
