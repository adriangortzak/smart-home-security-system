package org.gortz.alarm.model.Notifications;

import org.gortz.alarm.model.Notification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 02/05/16.
 */
public class PushBulletTest {
    Notification noti;
    @Before
    public void setUp() throws Exception {
        try {
            noti = new PushBullet("o.ugvDhUb1uuqSeudnf2sQPNyri0qiFBjG");
        }catch (Exception e){
            fail("Could'nt create a PushBullet object");
        }
    }

    @Test
    public void sendMessage() throws Exception {
        try{
            noti.setMessage("Test","testings");
            noti.run();
        }catch (Exception e){
            fail("Could'nt send push notification by Pushbullet");
        }

    }

}