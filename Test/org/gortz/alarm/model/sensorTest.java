package org.gortz.alarm.model;

import org.gortz.alarm.model.Sensors.TellstickDuo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.fail;

/**qwerty
 * Created by adrian on 18/04/16.
 */
public class sensorTest {
    TellstickDuo ts;
    @Before
    public void setUp() throws Exception {
        try{
            ts = new TellstickDuo();
        }
        catch(Exception e){
            fail("Couldn't create TellstickDuo object");
        }
    }

    @After
    public void tearDown() throws Exception {
        try{
            ts.turnOff(1);
            ts.close();
        }
        catch(Exception e){
            fail("Couldn't make clean exit of TellstickDuo");
        }
    }

    @Test
    public void turnOn() throws Exception {
        if( !ts.turnOn(1) ) fail("Couldn't turn on switch");
    }

    @Test
    public void turnOff() throws Exception {
        if( !ts.turnOff(1)) fail("Couldn't turn off switch");
    }

    @Test
    public void close() throws Exception {
        try{
            TellstickDuo ts2 = new TellstickDuo();
            ts2.close();
        }
        catch(Exception e){
            fail("Couldn't make clean exit of TellstickDuo");
        }
    }

}