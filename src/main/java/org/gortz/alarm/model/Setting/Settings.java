package org.gortz.alarm.model.Setting;

import org.gortz.alarm.model.Loggers.Logger;


/**
 * Created by adrian on 25/04/16.
 */
public class Settings {
    //Singelton object
    static Settings instance = null;
    //Logger
    Logger logger = Logger.getInstace();
    //
    //--------------------------------------//
    int pendingTime;
    int notificationInterval;
    String dbPassword;
    String dbUsername;

    //-------------------------------------

private Settings(){
    update();
}

    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }

    public void update(){
        pendingTime = 120;
        notificationInterval = 10;
        dbPassword = "APJ4A5M6sXTPBH74";
        dbUsername = "shss";
    }


    public int getPendingTime() {
        return pendingTime;
    }

    public int getNotificationInterval() {
        return notificationInterval;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUsername() {
        return dbUsername;
    }
}


