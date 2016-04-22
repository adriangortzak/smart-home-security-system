package org.gortz.alarm.model;

import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Sensors.TellstickDuo;

import java.util.NoSuchElementException;

/**
 * Created by adrian on 14/04/16.
 */
public class Alarm {
    Logger alarmLogger = new Logger("alarm");
    int pendingTime = 5;
    Status alarmStatus;
    Database myDatabase;


    private static Alarm instance = null;

    /**
     * Private constructor to follow the Singleton.
     * Creates connections to the database and gets old status.
     */
    private Alarm(){
        myDatabase = new mysql("shss", "APJ4A5M6sXTPBH74");
        alarmStatus = getStatusFromDb();
    }

    /**
     *Singleton constructor for the alarm.
     * @return
     */
    public static Alarm getInstance(){
        if(instance == null){
            return instance = new Alarm();
        }
        else return instance;
    }

    /**
     * Get the actual status from the server.
     * @return status
     */
    public Status getStatus() {
        if (alarmStatus == Status.ON){return Status.ON;}
            else if(alarmStatus == Status.OFF){return Status.OFF;}
            else if(alarmStatus == Status.PENDING){return Status.PENDING;}
        else{
            throw new NoSuchElementException();
        }
    }

    public enum Status {
        ON,
        OFF,
        PENDING
    }

    /**
     * Changed the Status on the alarm by the new status and does a fast check and return if it changed.
     * @param newStatus
     * @return Succeed
     */
    public boolean changeStatus(Status newStatus){
        //Public methods cant change status to to pending.
        if(newStatus == Status.PENDING){
            return false;
        }
        //-----------------------------  DEMO   ----------------------------------------
        Sensor se = new TellstickDuo();
        if(newStatus == Status.ON){
            se.turnOn(1);
        }else if(newStatus == Status.OFF){
            se.turnOff(1);
        }
        //------------------------------------------------------------------------------
        this.alarmStatus = newStatus;
        alarmLogger.write("Changing alarm status to " + newStatus, 2);
        //Fast check
        if(this.alarmStatus == newStatus){
            updateStatusToDb(newStatus);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Updates the saved status in database.
     * @param newStatus
     */
    private void updateStatusToDb(Alarm.Status newStatus){
    myDatabase.updateAlarmStatus(newStatus);
    }

    /**
     * Retrieves last set alarm status from the database
     * @return Alarm.Status from database
     */
    private Status getStatusFromDb(){
        //Status oldStatus = myDatabase.getAlarmStatus();
        //alarmLogger.write("Old status saved in Database is "+ oldStatus,2);
        //return oldStatus;
        return Status.ON;
    }


}
