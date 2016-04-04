package org.gortz.larm.Controller;
import org.gortz.larm.model.PhpManager;
import org.gortz.larm.model.TellstickManager;

/**
 * Created by adrian on 02/04/16.
 */
public class Controller {
    Thread website;
    Thread Sensor;
    Controller() {
        //Create thread to handle web request
        website = new Thread(new WebController());
        website.start();

        //Create thread to handle Sensors
        Sensor = new Thread(new SensorController());
        Sensor.start();
    }
}
