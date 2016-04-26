package org.gortz.alarm.model.Setting;

import org.gortz.alarm.model.CommandObject;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Notifications.PushBullet;
import org.gortz.alarm.model.Sensors.TellstickDuo;

import javax.activation.CommandMap;

import static org.gortz.alarm.model.Setting.Settings.Setting.*;


/**
 * Created by adrian on 25/04/16.
 */
public class Settings {
    CommandObject triggers[];
    //Singelton object
    static Settings instance = null;
    //
    Database db;
    //--------------------------------------//
    int pendingTime;
    int notificationInterval;
    String dbPassword;
    String dbUsername;
    boolean debuggingStatus;

    TellstickDuo ts = TellstickDuo.getInstance();

    Notification[] notifications;
    //-------------------------------------

private Settings(){
    update(AUTHENTICATION);
    db = new mysql(getDbUsername(),getDbPassword());
    update(PARAMETER);
}

    public CommandObject[] getTriggerObject() {
        return new CommandObject[0];
    }

    public enum Setting {
        AUTHENTICATION,
        PARAMETER,
        ALL
    }


    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }

    public void update(Setting type){
        switch (type){
            case AUTHENTICATION:
                updateAuthentication();
                break;
            case PARAMETER:
                updateParameters();
                break;
            case ALL:
                updateAuthentication();
                updateParameters();
                break;
            default:
                //TODO throw exception
                break;

        }
    }

    public void updateAuthentication(){//TODO fix file instead of database for password and add to gitignore
        dbPassword = "APJ4A5M6sXTPBH74";
        dbUsername = "shss";
    }

    public void updateParameters(){
        pendingTime = db.getServerSettingInt("pendingTime");
        notificationInterval = db.getServerSettingInt("notificationInterval");
        debuggingStatus = false;
        notifications = db.getNotifications();
        triggers = ts.getConfiguredDevices(db.getTriggerDevices());
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


