package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Notifications.lib.PushoverLib;

import java.io.IOException;

/**
 * Created by jimmy on 4/24/16.
 */
public class Pushover implements Notification {

    @Override
    public void sendMessage() {
        PushoverLib p = new PushoverLib("aVgvjLMsjK9LLaeGasGPNYkpCdhgJe", "upWz3p8PCYaLqUvykUNAWzZXQHsgtf");
        try {
            p.sendMessage("Hello, www.google.com!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

