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
    boolean getAlarmStatus();

    /**
     * Changes the saved status to the new status.
     * @param newStatus the desired status on the server
     */
    void updateAlarmStatus(Alarm.Status newStatus);

    /**
     * Writeing user and message history to database for website interface.
     * @param user that did the action
     * @param message of what he/she did
     */
    void writeHistory(String user,  String message);

    /**
     * Get setting value in int format
     * @param Setting that is the wanted setting
     * @return int with value of setting
     */
    int getServerSettingInt(String Setting);

    /**
     * Get setting value in string format
     * @param Setting that is the wanted setting
     * @return String with value of selected setting
     */
    String getServerSettingString(String Setting);

    /**
     * Get all active notifications from database
     * @return active notifications
     */
    Notification[] getNotifications();

    int[] getTriggerDevices();
}
