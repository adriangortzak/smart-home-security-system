package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Sensor;
import org.gortz.alarm.model.Sensors.TellstickDuo;

/**
 * Created by root on 02/05/16.
 */
public class TellstickAction implements Notification {
    String message;
    String title;
    Sensor tellstick = TellstickDuo.getInstance();
    int id;
    public TellstickAction(String idFromDb){
        id = Integer.parseInt(idFromDb);
    }

    @Override
    public void setMessage(String title, String message) {
        this.message = message;
        this.title = title;
    }

    @Override
    public void run() {
        switch (title){
            case "Safe":
                tellstick.sendCommand(id,"OFF");
		tellstick.sendCommand(id,"OFF");
		tellstick.sendCommand(id,"OFF");
                break;
            case "Alert":
                tellstick.sendCommand(id, "ON");
		tellstick.sendCommand(id, "ON");
		tellstick.sendCommand(id, "ON");
                break;
        }
    }
}
