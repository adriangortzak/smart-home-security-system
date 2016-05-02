package org.gortz.alarm.model.Loggers;

import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.gortz.alarm.model.Setting.Settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adrian on 04/04/16.
 */
public class Logger {
    private static Logger instance = null;
    Settings settings = Settings.getInstance();
    boolean debugging = settings.getDebuggingStatus();
    Database db = new mysql(settings.getDbUsername(),settings.getDbPassword());

    /*
    ----------------
    |Priority level|
    ----------------
    [1] -- Runtime debugging --> "var a = 23"
    [2] -- Runtime message -->  "Sensor data received";
    [3] -- Change in system --> "Settings updated"
    [4] -- Important message --> "[Error] could not connect to database"
    [5] -- History on website --> "Jimmy changed alarm status to ON"
     */



    private  Logger(){
    }

    public static Logger getInstace(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }





    /**
     * Get the time and date in String form.
     * @return timeAndDate
     */
    private String timeAndDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     * Prints the message at different places depending on priority.
     * @param message
     * @param priority
     */
    public void write(String user, String message, int priority) {
        switch (priority) {
            case 1:
                if(debugging) {
                    System.out.println("[" + timeAndDate() + "] - ("+user+")" + message);
                }
                break;
            case 2:
                System.out.println("[" + timeAndDate() + "] - ("+user+")" + message);
                break;
            case 3:
                System.out.println("[" + timeAndDate() + "] - ("+user+")" + message);
                break;
            case 4:
                System.out.println("[" + timeAndDate() + "] - ("+user+")" + message);
                break;
            case 5:
                if(user != "Test") db.writeHistory(user, message);
                break;
            default:
                break;
        }
    }
}
