package org.gortz.alarm.model.Alarms;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by adrian on 26/04/16.
 */
public class SharedStatus {
    //public volatile Status status;
    public AtomicBoolean status;
    public SharedStatus(boolean start){
        status = new AtomicBoolean(start);
    }
}
