package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;

/**
 * Created by root on 07/06/16.
 */
public class ShellCommand implements Notification {

    private String pid;
    private String title;

    @Override
    public void setMessage(String title, String message) {
        this.title = title;
    }

    @Override
    public void run() {
        switch (title){
            case "Safe":

                break;
            case "Alert":

                break;
        }
    }
}
