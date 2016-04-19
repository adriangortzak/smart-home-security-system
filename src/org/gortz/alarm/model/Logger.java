package org.gortz.alarm.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adrian on 04/04/16.
 */
public class Logger {
    String fileName;
    /*
    ----------------
    |Priority level|
    ----------------
    [1] -- Runtime debugging
    [2]
    [3]
    [4] -- Important message
    [5] -- Notify users
     */


    /**
     * Constructor that gets logging messages from system. Names them after the fileName at save.
     * @param fileName
     */
    public Logger(String fileName){
        this.fileName = fileName;
    }

    /**
     * Prints the message at different places depending on priority.
     * @param message
     * @param priority
     */
   public void write(String message, int priority){
       switch (priority) {
           case 1:
               System.out.println("[" + timeAndDate() + "]-" + message);
               break;
           case 2:
               System.out.println("[" + timeAndDate() + "]-" + message);
               break;
           case 3:
               System.out.println("[" + timeAndDate() + "]-" + message);
               break;
           case 4:
               System.out.println("[" + timeAndDate() + "]-" + message);
               break;
           case 5:
               System.out.println("[" + timeAndDate() + "]-" + message);
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
