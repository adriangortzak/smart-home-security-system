package org.gortz.alarm.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jimmy on 4/24/16.
 */
public class CommandObjectTest {
    CommandObject c;
    @Before
    public void setUp() throws Exception {
        c = new CommandObject("protocol", "model", "house", "unit", "group", "code", "method");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProtocol() throws Exception {
        if(!c.getProtocol().equals("protocol")) fail("Returned wrong value");
    }

    @Test
    public void getModel() throws Exception {
        if(!c.getModel().equals("model")) fail("Returned wrong value");
    }

    @Test
    public void getHouse() throws Exception {
        if(!c.getHouse().equals("house")) fail("Returned wrong value");
    }

    @Test
    public void getUnit() throws Exception {
        if(!c.getUnit().equals("unit")) fail("Returned wrong value");
    }

    @Test
    public void getGroup() throws Exception {
        if(!c.getGroup().equals("group")) fail("Returned wrong value");
    }

    @Test
    public void getCode() throws Exception {
        if(!c.getCode().equals("code")) fail("Returned wrong value");
    }

    @Test
    public void getMethod() throws Exception {
        if(!c.getMethod().equals("method")) fail("Returned wrong value");
    }
}