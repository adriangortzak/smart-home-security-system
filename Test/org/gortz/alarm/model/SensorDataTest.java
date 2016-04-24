package org.gortz.alarm.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jimmy on 4/24/16.
 */
public class SensorDataTest {
    SensorData s;
    @Before
    public void setUp() throws Exception {
        s = new SensorData("protocol", "id", "model", "humidity", "temp");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProtocol() throws Exception {
        if(! s.getProtocol().equals("protocol")) fail("Returned wrong value.");
    }

    @Test
    public void getId() throws Exception {
        if(! s.getId().equals("id")) fail("Returned wrong value.");
    }

    @Test
    public void getModel() throws Exception {
        if(! s.getModel().equals("model")) fail("Returned wrong value.");
    }

    @Test
    public void gethumidity() throws Exception {
        if(! s.getHumidity().equals("humidity")) fail("Returned wrong value.");
    }

    @Test
    public void getTemp() throws Exception {
        if(! s.getTemp().equals("temp")) fail("Returned wrong value");
    }

}