package org.gortz.alarm.Controller;

import org.gortz.alarm.model.Sensor;
import org.gortz.alarm.model.Sensors.TellstickDuo;

/**
 * Created by adrian on 04/04/16.
 */
public class SensorController implements Runnable  {
    Sensor s;

    @Override
    public void run() {
        s = new TellstickDuo();
    }

}
