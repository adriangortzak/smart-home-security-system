package org.gortz.alarm.model.Loggers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adrian on 04/04/16.
 */
public class Logger {
    private static Logger instance = null;
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



    private  Logger(){}

    public static Logger getInstace(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }


    /**
     * Prints the message at different places depending on priority.
     * @param message
     * @param priority
     */
   public void write(String message, int priority){
       switch (priority) {
           case 1:
               System.out.println("[" + timeAndDate() + "] - " + message);
               break;
           case 2:
               System.out.println("[" + timeAndDate() + "] - " + message);
               break;
           case 3:
               System.out.println("[" + timeAndDate() + "] - " + message);
               break;
           case 4:
               System.out.println("[" + timeAndDate() + "] - " + message);
               break;
           case 5:
               System.out.println("[" + timeAndDate() + "] - " + message);
               break;
           default:
               break;
       }
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
}
