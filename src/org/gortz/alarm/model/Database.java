package org.gortz.alarm.model;

/**
 * Created by adrian on 18/04/16.
 */
public interface Database {
    boolean connect();

    boolean tryConnection();

    void killConnection();

    Alarm.Status getAlarmStatus();
}
