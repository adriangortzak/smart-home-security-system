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



    public Logger(String fileName){
        this.fileName = fileName;
    }
   public void write(String message, int priority){
       switch (priority) {
           case 1:
               System.out.println("[" + timeAndDate() + "]-" + message);
               break;
           case 2:
               break;
           case 3:
               break;
           case 4:
               break;
           case 5:
               break;
           default:
               break;
       }
   }

    private String timeAndDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
