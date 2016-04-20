package org.gortz.alarm.Test.Controller;

import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Alarm;

import static junit.framework.TestCase.fail;

/**
 * Created by adrian on 18/04/16.
 */
public class ControllerTest {
    Controller myController;
    @org.junit.Before
    public void setUp() throws Exception {
        myController = new Controller();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void startSensor() throws Exception {

        try{myController = new Controller();}catch (Exception e){
            fail("Could not create Controller");
        };
    }

    @org.junit.Test
    public void changeAlarmStatus() throws Exception {
        if(!myController.changeAlarmStatus(Alarm.Status.ON) == true){
            fail("Cant change status on alarm from Controller");
        }

        if(!myController.changeAlarmStatus(Alarm.Status.PENDING) == false){
            fail("Pending is not a state that the Controller can set. So the Alarm shouldn't let it.");
        }

    }

    @org.junit.Test
    public void checkAlarmStatus() throws Exception {

        //TODO no way to check pending

        myController.changeAlarmStatus(Alarm.Status.OFF);
        if(myController.checkAlarmStatus() != "OFF") fail("Setting Status to OFF is not returning OFF when checked");

        myController.changeAlarmStatus(Alarm.Status.ON);
        if(myController.checkAlarmStatus() != "ON") fail("Setting Status to ON is not returning ON when checked");

    }

}