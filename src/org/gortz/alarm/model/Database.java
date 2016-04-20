package org.gortz.alarm.model;

/**
 * Created by adrian on 18/04/16.
 */
public interface Database {
    /**
     * Create a connection to the database and returns true on success.
     * @return succeeded
     */
    boolean connect();

    /**
     * Ends the connection to the database.
     */
    void killConnection();

    /**
     * Retrivse the last saved alarm status from database
     * @return alarmStatus
     */
    Alarm.Status getAlarmStatus();

    /**
     * Changes the saved status to the new status.
     * @param newStatus
     */
    void updateAlarmStatus(Alarm.Status newStatus);
}
