package org.gortz.alarm.model.Alarms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adrian on 02/05/16.
 */
public class AlarmTest {
    Alarm alarm;
    Alarm.Status startStatus;

    @Before
    public void setUp() throws Exception {
    try {
        alarm = Alarm.getInstance();

    }catch (Exception e){
        fail("Could'nt create alarm object");
    }try {
            startStatus = alarm.getStatus();
    }catch (Exception e){
        fail("Could'nt get alarm status");
    }

    }

    @After
    public void tearDown() throws Exception {
    alarm.changeStatus(startStatus,"Test");
    }

    @Test
    public void getSirenStatus() throws Exception {
        try{
          String  res = alarm.getSirenStatus();
            switch (res){
                case "ON": break;
                case "OFF": break;
                default: fail("Not valid response from alarm after getSirenStatus");
            }
        }catch (Exception e){
            fail("Could'nt call getSirenStatus in alarm");
        }
    }



    @Test
    public void changeStatus() throws Exception {

    try {
    alarm.changeStatus(Alarm.Status.ON,"Test");
        if(!(alarm.getStatus() == Alarm.Status.ON)) fail("Alarm status did'nt change to ON");
    }catch (Exception e){
        fail("Could'nt change och get alarm status");
    }
    try {
        alarm.changeStatus(Alarm.Status.OFF,"Test");
        if(!(alarm.getStatus() == Alarm.Status.OFF)) fail("Alarm status did'nt change to ON");
    }catch (Exception e){
        fail("Could'nt change och get alarm status");
    }
    }

    @Test
    public void trigger() throws Exception {
    try {
        alarm.changeStatus(Alarm.Status.OFF, "Test");
        Thread.sleep(3000);// Requires a sleep because it tries to read the value before it is updated
        //while(!alarm.getSirenStatus().equals("ON"))

        if(alarm.getSirenStatus() == "ON") fail("Siren can't be on if alarm system is OFF");
        alarm.changeStatus(Alarm.Status.ON,"Test");
        try {
            System.out.println("Siren status:");
            System.out.println(alarm.getSirenStatus());
            alarm.trigger("Test");
            if(!alarm.getSirenStatus().equals("ON")) fail("Siren did'nt started after trigger");
            alarm.changeStatus(Alarm.Status.OFF, "Test");
            //while(!alarm.getSirenStatus().equals("OFF")){}
            Thread.sleep(3000);// Requires a sleep because it tries to read the value before it is updated
            if(alarm.getSirenStatus().equals("ON")) fail("Siren still on after turn off");
        }catch (Exception e){
            fail("Could'nt call to trigger och getSirenStatus");
        }
    }catch (Exception e){
        fail("Could'nt change alarm status");
    }
    }

}