package org.gortz.alarm.model.Setting;

import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.Notification;


/**
 * Created by adrian on 25/04/16.
 */
public class Settings {
    //Singelton object
    static Settings instance = null;
    //Logger
    Logger logger = Logger.getInstace();
    //
    Database db;
    //--------------------------------------//
    int pendingTime;
    int notificationInterval;
    String dbPassword;
    String dbUsername;
    boolean debuggingStatus;

    Notification[] notifications;
    //-------------------------------------

private Settings(){
    update();
    db = new mysql(getDbUsername(),getDbPassword());
}

    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }

    public void update(){
        logger.write("Server", "Updated settings",3);
        pendingTime = 10;// db.getServerSettingInt("pendingTime");
        notificationInterval = 10;//db.getServerSettingInt("notificationInterval");
        dbPassword = "APJ4A5M6sXTPBH74";
        dbUsername = "shss";
        debuggingStatus = false;
    }


    public int getPendingTime() {
        return pendingTime;
    }

    public int getNotificationInterval() {
        return notificationInterval;
    }

    public  String getDbPassword() {
        return dbPassword;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public boolean getDebuggingStatus() {
        return debuggingStatus;
    }

    public  Notification[] getNotification() {
        return notifications;
    }
}


