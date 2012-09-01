package org.mobicents.slee.resource.tcap.wrappers;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.mobicents.protocols.ss7.tcap.asn.ParseException;
import org.mobicents.protocols.ss7.tcap.asn.comp.Component;
import org.mobicents.protocols.ss7.tcap.asn.comp.ComponentType;
import org.mobicents.slee.resource.tcap.events.ComponentEvent;

/**
 * 
 * @author amit bhayani
 * 
 */
public class ComponentEventImpl<T extends Component> implements ComponentEvent {

	private final TCAPDialogWrapper dialogWrapper;
	protected final T wrappedComponent;

	/**
	 * @param wrappedDialog
	 */
	public ComponentEventImpl(TCAPDialogWrapper wrappedDialog, T wrappedComponent) {
		super();
		this.dialogWrapper = wrappedDialog;
		this.wrappedComponent = wrappedComponent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols
	 * .asn.AsnInputStream)
	 */
	@Override
	public void decode(AsnInputStream arg0) throws ParseException {
		throw new UnsupportedOperationException("Unsupported Operation");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols
	 * .asn.AsnOutputStream)
	 */
	@Override
	public void encode(AsnOutputStream arg0) throws ParseException {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Component#getInvokeId()
	 */
	@Override
	public Long getInvokeId() {
		return this.wrappedComponent.getInvokeId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.protocols.ss7.tcap.asn.comp.Component#getType()
	 */
	@Override
	public ComponentType getType() {
		return this.wrappedComponent.getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.comp.Component#setInvokeId(java.
	 * lang.Long)
	 */
	@Override
	public void setInvokeId(Long invokeId) {
		this.wrappedComponent.setInvokeId(invokeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mobicents.slee.resource.tcap.events.ComponentEvent#getDialog()
	 */
	@Override
	public Dialog getDialog() {
		return this.dialogWrapper;
	}

}
