package org.gortz.alarm.Controller;

import org.gortz.alarm.model.Logger;

/**
 * Created by adrian on 04/04/16.
 */
public class WebController implements Runnable {
    Logger webLogger = new Logger();


    @Override
    public void run() {
        webLogger.write("WebController is up and running");

        webLogger.write("Waiting for signal");

    }
}
