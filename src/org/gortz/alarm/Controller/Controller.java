package org.gortz.alarm.Controller;

/**
 * Created by adrian on 02/04/16.
 */
public class Controller {

    Thread Sensor;
    public Controller() {
    }
    public boolean startSensor(){
        //Create thread to handle Sensors
        Sensor = new Thread(new SensorController());
        Sensor.start();
        return true; //TODO - fix a better way to check if it succeeded
    }
}
