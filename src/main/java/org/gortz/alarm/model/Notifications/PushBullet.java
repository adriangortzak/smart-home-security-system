package org.gortz.alarm.model.Notifications;

import com.github.sheigutn.pushbullet.Pushbullet;
import org.gortz.alarm.model.Notification;

/**
 * Created by adrian on 21/04/16.
 */
public class PushBullet implements Notification {
    public void sendMessage() {
        String apiToken = "o.xtslVCDHWRdhVvx6Q8OWBkZmyC7z4gq6";
        Pushbullet pushbullet = new Pushbullet(apiToken);
        pushbullet.pushNote("test","www.google.com");
    }
}
