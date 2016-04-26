package org.gortz.alarm.model.Notifications;

import com.github.sheigutn.pushbullet.Pushbullet;
import org.gortz.alarm.model.Notification;

/**
 * Created by adrian on 21/04/16.
 */
public class PushBullet implements Notification {
    Pushbullet pushbullet;

    public PushBullet(String apiToken){
       pushbullet = new Pushbullet(apiToken);
    }

    public void sendMessage(String title,String message) {
        pushbullet.pushNote(title,message);
    }
}
