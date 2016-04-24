package org.gortz.alarm.model.Notifications;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jimmy on 4/24/16.
 */
public class PushoverTest {
    Pushover p;
    @Before
    public void setUp() throws Exception {
        p = new Pushover();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sendMessage() throws Exception {
        p.sendMessage();
    }

}