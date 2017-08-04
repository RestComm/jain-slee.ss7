package org.mobicents.slee.resource.cap;

import javax.slee.resource.ActivityHandle;
import javax.slee.resource.FireableEventType;
import javax.slee.resource.Marshaler;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by piotr.sokolowski on 2017-06-28.
 */
public class CapMarshaler implements Marshaler {
    private final CAPResourceAdaptor capRA;
    CapMarshaler(CAPResourceAdaptor cra) {
        this.capRA=cra;
    }

    @Override
    public int getEstimatedEventSize(FireableEventType fireableEventType, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer getEventBuffer(FireableEventType fireableEventType, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void releaseEventBuffer(FireableEventType fireableEventType, Object o, ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void marshalEvent(FireableEventType fireableEventType, Object o, DataOutput dataOutput) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object unmarshalEvent(FireableEventType fireableEventType, DataInput dataInput) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getEstimatedHandleSize(ActivityHandle activityHandle) {
        return 8;
    }

    @Override
    public void marshalHandle(ActivityHandle activityHandle, DataOutput dataOutput) throws IOException {
        CAPDialogActivityHandle dah = (CAPDialogActivityHandle)activityHandle;
        dataOutput.writeLong(dah.getDialogId());
    }

    @Override
    public ActivityHandle unmarshalHandle(DataInput dataInput) throws IOException {
        long localDialogId=dataInput.readLong();
        CAPDialogActivityHandle ah=new CAPDialogActivityHandle(capRA,localDialogId);
        return ah;
    }
}
