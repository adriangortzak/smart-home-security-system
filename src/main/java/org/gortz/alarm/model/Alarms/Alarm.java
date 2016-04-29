package org.gortz.alarm.model.Alarms;

import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Setting.Settings;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Loggers.Logger;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.gortz.alarm.model.Alarms.Alarm.Status.*;

/**
 * Created by adrian on 14/04/16.
 */
public class Alarm {
    Logger logger = Logger.getInstace();
    Settings settings = Settings.getInstance();
    int pendingTime = settings.getPendingTime();
    AtomicBoolean roar = new AtomicBoolean(false);
    Database myDatabase;
    private static Alarm instance = null;
    AtomicBoolean running = new AtomicBoolean(false);


    //    public AtomicBoolean status = new AtomicBoolean(getStatusFromDb());

     SharedStatus sharedStatus;

    /**
     * Private constructor to follow the Singleton.
     * Creates connections to the database and gets old status.
     */
    private Alarm(){
        myDatabase = new mysql(settings.getDbUsername(), settings.getDbPassword());
        sharedStatus = new SharedStatus(getStatusFromDb());
    }

    /**
     *Singleton constructor for the alarm.
     * @return Alarm object
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
        boolean status = sharedStatus.status.get();
            if(!status){return OFF;}
            else if(status){return ON;}
        else{
            throw new NoSuchElementException();
        }
    }

    /**
     * Reports of the Siren is running on the server
     * @return Siren status
     */
    public String getSirenStatus() {
        if(roar.get()){
            return "ON";
        }
        else return "OFF";
    }

    /**
     * Alarm server states
     */
    public enum Status {
        ON,
        OFF
    }

    /**
     * Changed the Status on the alarm by the new status and does a fast check and return if it changed.
     * @param newStatus
     * @return Succeed
     */
    public boolean changeStatus(Status newStatus, String user){
        boolean changedTo = false;
        //Public methods cant change status to to pending.
        if(newStatus == ON){
            sharedStatus.status.set(true);
            changedTo = true;
        }else if(newStatus == OFF){
            sharedStatus.status.set(false);
            changedTo = false;
        }
        logger.write(user,"Changing alarm status to " + changedTo , 5);
        //Fast check
        if(sharedStatus.status.get() == changedTo){
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
     * @return Alarms.Status from database
     */
    private boolean getStatusFromDb(){
        boolean oldStatus = myDatabase.getAlarmStatus();
        logger.write("Server","Old status saved in Database is "+ oldStatus,3);
        return oldStatus;
    }

    /**
     * The system has been triggered by a sensor and needs to check if any actions is required
     */
    public void trigger(String by){
        if(!running.get()) {
            running.set(true);
            if (getStatus() == ON) {
                if(!check(pendingTime)) {
                    logger.write("Server","Trigged Alarm by "+by, 5);
                    Thread typhoonThread;
                    typhoonThread = new Thread(new Typhoon());
                    typhoonThread.start();
                    roar.set(true);
                }
            }
            else {
                running.set(false);
                logger.write("Server","Triggered but no reaction", 2);
            }
        }
        else logger.write("Server","Triggered but roar is already ON", 2);
    }

    private boolean check(int time){
        for (int currentSleep =  time; currentSleep>0; currentSleep--) {
            logger.write("Server","Alarm countdown on "+ currentSleep,3);

            if (getStatus().equals(OFF)) {
                running.set(false);
                logger.write("Server","Trigger turned off by change of alarm status",3);
                roar.set(false);
                return true; //Stop the roar
            } else
                try {
                    Thread.sleep(1000);//sleep
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    private class Typhoon implements Runnable{
        int notificationInterval = settings.getNotificationInterval();

        public void run() {
        notifyUser();
        }

        private void notifyUser(){
            for(Notification notification : settings.getNotification()){
                notification.sendMessage("Alarm","Alert! Alert! Sensors has Triggered your Alarm!");
            }
            //Wait for response.
            if(!check(notificationInterval)){notifyUser();}
        }


    }

}
