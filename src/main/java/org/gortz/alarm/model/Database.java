package org.gortz.alarm.model;

import org.gortz.alarm.model.Alarms.Alarm;

/**
 * Created by adrian on 18/04/16.
 */
public interface Database {
    /**
     * Retrieve the last saved alarm status from database
     * @return alarmStatus
     */
    Alarm.Status getAlarmStatus();

    /**
     * Changes the saved status to the new status.
     * @param newStatus
     */
    void updateAlarmStatus(Alarm.Status newStatus);

    void writeHistory(String user, String statusChange, String s);
}
