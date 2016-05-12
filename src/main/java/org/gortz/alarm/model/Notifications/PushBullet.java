package org.gortz.alarm.model.Notifications;

import com.github.sheigutn.pushbullet.Pushbullet;
import org.gortz.alarm.model.Notification;

/**
 * Created by adrian on 21/04/16.
 */
public class PushBullet implements Notification {
    String title;
    String message;
    Pushbullet pushbullet;

    public PushBullet(String apiToken){
       pushbullet = new Pushbullet(apiToken);
    }

    @Override
    public void setMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public void run() {
        pushbullet.pushNote(title,message);
    }
}
