package org.gortz.alarm.Controller;

import org.gortz.alarm.model.Alarms.Alarm;

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
    public void triggerAlarm(){
        try {
            myController.triggerAlarm("Test");
        }catch (Exception e){
            fail("Controller could'nt trigger alarm");
        }

    }
    @org.junit.Test
    public void serverAliveCheck(){
        try {
            if(!myController.serverAliveCheck().equals("OK!")) fail("Wrong response from ServerAliveCheck");
        }catch (Exception e){
            fail("Could'nt call serverAliveCheck from Controller");
        }
    }

    @org.junit.Test
    public void sirenStatus(){
        try {
            String resp = myController.sirenStatus();
            switch (resp){
                case "ON": break;
                case "OFF":break;
                    default: fail("Not allowed response from function sirenStatus from Controller");
            }
        }catch (Exception e){
            fail("Could'nt call sirenStatus from Controller");
        }
    }

    @org.junit.Test
    public void changeAlarmStatus() throws Exception {
        if(!myController.changeAlarmStatus(Alarm.Status.ON, "test") == true){
            fail("Cant change status on alarm from Controller");
        }

    }

    @org.junit.Test
    public void checkAlarmStatus() throws Exception {

        //TODO no way to check pending

        myController.changeAlarmStatus(Alarm.Status.OFF, "test");
        if(myController.checkAlarmStatus() != "OFF") fail("Setting Status to OFF is not returning OFF when checked");

        myController.changeAlarmStatus(Alarm.Status.ON, "test");
        if(myController.checkAlarmStatus() != "ON") fail("Setting Status to ON is not returning ON when checked");

    }

}