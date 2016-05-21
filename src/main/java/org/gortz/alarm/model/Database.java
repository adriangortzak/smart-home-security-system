package org.gortz.alarm.model;

import org.gortz.alarm.model.Alarms.Alarm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adrian on 18/04/16.
 */
public interface Database {
    /**
     * Retrieve the last saved alarm status from database
     * @return alarmStatus
     */
    boolean getAlarmStatus();

    /**
     * Changes the saved status to the new status.
     * @param newStatus the desired status on the server
     */
    void updateAlarmStatus(Alarm.Status newStatus);

    /**
     * Writing  user and message history to database for website interface.
     * @param user that did the action
     * @param message of what he/she did
     */
    void writeHistory(String user,  String message);

    /**
     * Get setting value in int format
     * @param setting that is the wanted setting
     * @return int with value of setting
     */
    int getServerSettingInt(String setting);

    /**
     * Get setting value in string format
     * @param setting that is the wanted setting
     * @return String with value of selected setting
     */
    String getServerSettingString(String setting);

    /**
     * Get all active notifications from database
     * @return active notifications
     */
    Notification[] getNotifications();
    /**
     *  Get all trigger devices from database
     * @return trigger devices
     */
    int[] getTriggerDevices();
}
