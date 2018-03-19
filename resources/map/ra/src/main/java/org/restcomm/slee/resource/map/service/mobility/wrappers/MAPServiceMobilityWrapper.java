package org.restcomm.slee.resource.map.service.mobility.wrappers;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.ServingCheckData;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobilityListener;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.slee.resource.map.MAPDialogActivityHandle;
import org.restcomm.slee.resource.map.wrappers.MAPProviderWrapper;

/**
 * 
 * @author amit bhayani
 * 
 */
public class MAPServiceMobilityWrapper implements MAPServiceMobility {

	protected MAPServiceMobility wrappedMobility;
	protected MAPProviderWrapper mapProviderWrapper;

	/**
	 * @param mapServiceSupplementary
	 */
	public MAPServiceMobilityWrapper(MAPProviderWrapper mapProviderWrapper, MAPServiceMobility mapServiceSupplementary) {
		this.wrappedMobility = mapServiceSupplementary;
		this.mapProviderWrapper = mapProviderWrapper;
	}

	public MAPProvider getMAPProvider() {
		return this.mapProviderWrapper;
	}

	public void acivate() {
		throw new UnsupportedOperationException();
	}

	public void deactivate() {
		throw new UnsupportedOperationException();
	}

	public boolean isActivated() {
		return this.wrappedMobility.isActivated();
	}

	public ServingCheckData isServingService(MAPApplicationContext mapapplicationcontext) {
		return this.wrappedMobility.isServingService(mapapplicationcontext);
	}

	public void addMAPServiceListener(MAPServiceMobilityListener arg0) {
		throw new UnsupportedOperationException();

	}

    public MAPDialogMobility createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference, Long localTrId) throws MAPException {

        MAPDialogMobility mapDialog = this.wrappedMobility.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, localTrId);
        MAPDialogActivityHandle activityHandle = new MAPDialogActivityHandle(mapDialog.getLocalDialogId());
        MAPDialogMobilityWrapper dw = new MAPDialogMobilityWrapper(mapDialog, activityHandle, this.mapProviderWrapper.getRa());
        mapDialog.setUserObject(dw);

        try {
            this.mapProviderWrapper.getRa().startSuspendedActivity(dw);
        } catch (Exception e) {
            throw new MAPException(e);
        }

        return dw;
    }

    public MAPDialogMobility createNewDialog(MAPApplicationContext appCntx, SccpAddress origAddress, AddressString origReference, SccpAddress destAddress,
            AddressString destReference) throws MAPException {
        return this.createNewDialog(appCntx, origAddress, origReference, destAddress, destReference, null);
    }

	public void removeMAPServiceListener(MAPServiceMobilityListener arg0) {
		throw new UnsupportedOperationException();
	}

}
