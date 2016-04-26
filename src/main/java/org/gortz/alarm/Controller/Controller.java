package org.gortz.alarm.Controller;
import org.gortz.alarm.model.Alarms.Alarm;
/**
 * Created by adrian on 02/04/16.
 */
public class Controller {
    Alarm alarm;
    Thread Sensor;
    public Controller() {
        startSensor();
        alarm =  Alarm.getInstance();
    }
    private boolean startSensor(){
        //Create thread to handle Sensors
        Sensor = new Thread(new SensorController());
        Sensor.start();
        return true; //TODO - fix a better way to check if it succeeded
    }

    public void triggerAlarm(String by){
        alarm.trigger(by);
    }

    public boolean changeAlarmStatus(Alarm.Status newAlarmStatus, String user){
        return alarm.changeStatus(newAlarmStatus, user);
    }
    public String checkAlarmStatus(){
        Alarm.Status res = alarm.getStatus();
        if(res == Alarm.Status.ON) return "ON";
        else if(res == Alarm.Status.OFF)return "OFF";
        else if(res == Alarm.Status.PENDING)return "PENDING";
        else return "Error";
    }
}
