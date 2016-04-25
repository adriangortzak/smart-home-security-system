package org.gortz.alarm.model.Alarms;

import org.gortz.alarm.model.Setting.Settings;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Loggers.Logger;

import java.util.NoSuchElementException;

import static org.gortz.alarm.model.Alarms.Alarm.Status.*;

/**
 * Created by adrian on 14/04/16.
 */
public class Alarm {
    Logger logger = new Logger("alarm");
    Settings settings = Settings.getInstance();
    int pendingTime = settings.getPendingTime();
    Status status;
    Boolean typhoon = false;
    Database myDatabase;
    private static Alarm instance = null;

    /**
     * Private constructor to follow the Singleton.
     * Creates connections to the database and gets old status.
     */
    private Alarm(){
        myDatabase = new mysql(settings.getDbUsername(), settings.getDbPassword());
        status = getStatusFromDb();
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
        if (status == ON){return ON;}
            else if(status == OFF){return OFF;}
            else if(status == PENDING){return PENDING;}
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
    public boolean changeStatus(Status newStatus, String user){
        //Public methods cant change status to to pending.
        if(newStatus == PENDING){
            return false;
        }

        this.status = newStatus;
        logger.write("Changing alarm status to " + newStatus + " by " + user, 2);
        myDatabase.writeHistory(user,"statusChange","Changed Alarm status to " + newStatus);
        //Fast check
        if(this.status == newStatus){
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
    private Status getStatusFromDb(){
        Status oldStatus = myDatabase.getAlarmStatus();
        logger.write("Old status saved in Database is "+ oldStatus,2);
        return oldStatus;
    }

    /**
     * The system has been triggered by a sensor and needs to check if any actions is required
     */
    public void trigger(){
        if(!typhoon) {
            if (status == ON) {
                logger.write("Trigged Alarm",5);
                Thread typhoonThread;
                typhoonThread = new Thread(new Typhoon());
                typhoonThread.start();
                typhoon = true;
            } else logger.write("Triggered but no reaction", 1);
        }
        else logger.write("Triggered but typhoon is already ON", 1);
    }

    private class Typhoon implements Runnable{
        Status myStatus;
        Boolean running;
        int notificationInterval = settings.getNotificationInterval();

        public void run() {
        notifyUser();
        }

        private void notifyUser(){
            String message;
            int[] users = new int[0];
            String[] notificationProtocols = new String[0];

            // int[]String[]
            //for all user
            for(int user: users)
                for (String notificationProtocol : notificationProtocols) {
               /*     switch (notificationProtocol){

                    }*/
                }
            System.out.println("help!");
            //for all notification wishes
            //notifaction
            //Wait for response.
            check();
        }

        private void check(){
        for (int currentSleep = notificationInterval; currentSleep>0; currentSleep--) {
            if (myStatus == OFF) {
                running = false;
                return; //Stop the typhoon
            } else
                try {
                    Thread.sleep(1000);//sleep
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        notifyUser();
        }
    }

}
