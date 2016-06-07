package org.gortz.alarm.model.Setting;


import org.gortz.alarm.model.SensorData;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.Mysql;
import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Sensors.TellstickDuo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adrian on 25/04/16.
 */
public class Settings {

    //Singelton object
    static Settings instance = null;
    static boolean createLock = false;
    //
    Database db;
    //--------------------------------------//
    AtomicInteger pendingTime = new AtomicInteger();
    AtomicInteger notificationInterval = new AtomicInteger();
    static String dbPassword ="APJ4A5M6sXTPBH74";
    static String dbUsername ="shss";
    static boolean debuggingStatus = false;
    SensorData[] triggers;
    AtomicInteger threadPoolCount = new AtomicInteger();
    TellstickDuo ts = TellstickDuo.getInstance();

    Notification[] notifications;
    //-------------------------------------

private Settings(){
    db = new Mysql(getDbUsername(),getDbPassword());
    update();
}


    public static Settings getInstance(){
        if(instance == null && !createLock){
            createLock = true;
            instance = new Settings();
            createLock = false;
        }
        while (createLock){
            try{
                Thread.sleep(1);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void update(){
        pendingTime.set(db.getServerSettingInt("pendingTime"));
        notificationInterval.set( db.getServerSettingInt("notificationInterval"));
        notifications = db.getNotifications();
        triggers = ts.getConfiguredDevices(db.getTriggerDevices());
        threadPoolCount.set(db.getServerSettingInt("threadPool"));
    }


    public int getPendingTime() {
        return pendingTime.get();
    }

    public int getNotificationInterval() {
        return notificationInterval.get();
    }

    public static  String getDbPassword() {
        return dbPassword;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static boolean getDebuggingStatus() {
        return debuggingStatus;
    }

    public  Notification[] getNotification() {
        return notifications;
    }

    public SensorData[] getTriggerObject() {
        return triggers;
    }

    public int getThreadPoolCount() {
        return threadPoolCount.get();
    }
}


