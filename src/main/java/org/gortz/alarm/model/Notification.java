package org.gortz.alarm.model;

/**
 * Created by adrian on 21/04/16.
 */
public interface Notification {
    /**
     * Take a message and a title/subject and send it through select channel.
     * @param title subject of message
     * @param message content of message
     */
     void sendMessage(String title, String message);


}
