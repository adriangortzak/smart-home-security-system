package org.gortz.alarm.Controller;

import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.Sensor;
import org.gortz.alarm.model.Sensors.TellstickDuo;

/**
 * Created by adrian on 04/04/16.
 */
public class SensorController implements Runnable  {
    Sensor s;
    Logger myLogger;

    @Override
    public void run() {
        myLogger = Logger.getInstace();
        s = TellstickDuo.getInstance();
        s.startListener();
        myLogger.write("server","Am now sniffing for commands and sending the method to the logger!\n", 3);
        s.sendCommand();
    }

}
