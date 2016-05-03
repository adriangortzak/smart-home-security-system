package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Sensor;
import org.gortz.alarm.model.Sensors.TellstickDuo;

/**
 * Created by root on 02/05/16.
 */
public class TellstickAction implements Notification {
    Sensor tellstick = TellstickDuo.getInstance();
    int id;
    Logger myLogger;
    public TellstickAction(String idFromDb){
        id = Integer.parseInt(idFromDb);
    }


    @Override
    public void sendMessage(String title, String message) {
    switch (title){
        case "Safe":
            tellstick.sendCommand(id,"OFF");
            break;
        case "Alert":
            tellstick.sendCommand(id, "ON");
            break;
    }
    }
}
