package org.gortz.alarm.model;

import java.util.NoSuchElementException;

/**
 * Created by adrian on 14/04/16.
 */
public class Alarm {
    Logger alarmLogger = new Logger("alarm");
    int pendingTime = 5;
    Status alarmStatus;


    private static Alarm instance = null;
    private Alarm(){

        alarmStatus = getStatusFromDb();
    }

    public static Alarm getInstance(){
        if(instance == null){
            return instance = new Alarm();
        }
        else return instance;
    }

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

    public boolean changeStatus(Status newStatus){
        //Public methods cant change status to to pending.
        if(newStatus == Status.PENDING){
            return false;
        }
        this.alarmStatus = newStatus;
        alarmLogger.write("changening alarm status to " + newStatus, 2);
        //Fast check
        if(this.alarmStatus == newStatus){
            updateStatusToDb();
            return true;
        }
        else{
            return false;
        }
    }

    private void updateStatusToDb(){}
    //TODO get real alarm from DB
    private Status getStatusFromDb(){return Status.ON;}


}
