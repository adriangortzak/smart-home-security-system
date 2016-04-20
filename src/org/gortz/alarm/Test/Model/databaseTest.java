package org.gortz.alarm.Test.Model;

import org.gortz.alarm.model.Alarm;
import org.gortz.alarm.model.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.gortz.alarm.model.Alarm.Status.OFF;
import static org.gortz.alarm.model.Alarm.Status.ON;
import static org.gortz.alarm.model.Alarm.Status.PENDING;

/**
 * Created by adrian on 19/04/16.
 */
public class databaseTest {
    Database db;
    @Before
    public void setUp() throws Exception {
        try {
         db.connect();
        }catch (Exception e){
         fail("Could not create database connection.");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void connection() throws Exception {
    Database db2 = null;
        try {
            db2.connect();
        }catch (Exception e){
            fail("Could not create database connection.");
        }
        try {
            db2.killConnection();
        }catch (Exception e){
            fail("Couldn't close connection to database");
        }
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