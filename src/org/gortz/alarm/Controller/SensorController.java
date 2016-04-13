package org.gortz.alarm.Controller;

import net.jstick.api.Tellstick;

/**
 * Created by adrian on 04/04/16.
 */
public class SensorController implements Runnable {
    @Override
    public void run() {
        Tellstick ts = new Tellstick(true);
    }
}
