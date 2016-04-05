package org.gortz.alarm.Controller;

/**
 * Created by adrian on 02/04/16.
 */
public class Controller {
    Thread website;
    Thread Sensor;
    public Controller() {
        //Create thread to handle web request
        website = new Thread(new WebController());
        website.start();

        //Create thread to handle Sensors
        Sensor = new Thread(new SensorController());
        Sensor.start();
    }
}
