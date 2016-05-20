package org.gortz.alarm.model;

/**
 * Created by adrian on 21/04/16.
 */
public interface Notification extends Runnable{
    /**
     * Take a message and a title/subject and send it through select channel.
     * @param title subject of message
     * @param message content of message
     */
    void setMessage(String title, String message);
}
