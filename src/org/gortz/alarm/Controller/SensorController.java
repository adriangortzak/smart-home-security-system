package org.gortz.alarm.Controller;

import net.jstick.api.Tellstick;
import org.gortz.alarm.model.Sensor;
import org.gortz.alarm.model.TellstickDuo;

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
