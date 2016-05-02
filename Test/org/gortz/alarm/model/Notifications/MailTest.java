package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adrian on 02/05/16.
 */
public class MailTest {
    Notification noti;
    @Before
    public void setUp() throws Exception {
        try {
            noti = new Mail("Test@testsson.test");
        }catch (Exception e){

        }
    }

    @Test
    public void sendMessage() throws Exception {
     noti.sendMessage("Test","Testsson");
    }

}