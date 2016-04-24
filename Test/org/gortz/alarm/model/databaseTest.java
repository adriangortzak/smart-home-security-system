package org.gortz.alarm.model;

import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Databases.mysql;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.gortz.alarm.model.Alarms.Alarm.Status.OFF;
import static org.gortz.alarm.model.Alarms.Alarm.Status.ON;
import static org.gortz.alarm.model.Alarms.Alarm.Status.PENDING;

/**
 * Created by adrian on 19/04/16.
 */
public class databaseTest {
    Database db;
    @Before
    public void setUp() throws Exception {
        try {
            db = new mysql("shss", "APJ4A5M6sXTPBH74");
        }catch (Exception e){
         fail("Could not create database connection.");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AlarmStatus() throws Exception {
        Alarm.Status oldDbStatus = null;

        //Get status atm and save it for later so we don't change the database.
        try {
            oldDbStatus = db.getAlarmStatus();
        }catch (Exception e){
            fail("Couldn't get the alarm status from database");
        }

        try {
            db.updateAlarmStatus(OFF);
            if (db.getAlarmStatus() != OFF) fail("Didn't change the db value or couldn't retrieve the right status");
            db.updateAlarmStatus(ON);
            if (db.getAlarmStatus() != ON) fail("Didn't change the db value or couldn't retrieve the right status");
        }catch (Exception e){
            fail("couldn't change alarm status in database");
        }

        try {
            db.updateAlarmStatus(PENDING);
            fail("It let me change the database alarm status to pending");
        }catch (IllegalArgumentException e){
            //Success
        }

        //Set database status back to the state it had before we tested.
        try {
            db.updateAlarmStatus(oldDbStatus);
        }catch (Exception e){
        }


    }


}