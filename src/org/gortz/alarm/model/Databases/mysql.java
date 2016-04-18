package org.gortz.alarm.model.Databases;

import org.gortz.alarm.model.Alarm;
import org.gortz.alarm.model.Database;
import java.sql.*;

/**
 * Created by adrian on 18/04/16.
 */
public class mysql implements Database {
    // DB connection variable
    static protected Connection con;
    // DB access variables
    private String URL = "jdbc:mysql://127.0.0.1:3306/shss";
    private String driver = "com.mysql.jdbc.Driver";
    private String userID = null;
    private String password = null;

    public mysql(String username, String password) {
        this.userID = username;
        this.password = password;
    }

    public boolean connect()
    {
        boolean couldConnect = true;
        try
        {
            // register the driver with DriverManager
            Class.forName(driver);
            //create a connection to the database
            con = DriverManager.getConnection(URL, userID, password);
            // Set the auto commit of the connection to false.
            // An explicit commit will be required in order to accept
            // any changes done to the DB through this connection.
            con.setAutoCommit(false);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            couldConnect = false;
        }
        return couldConnect;
    }

    public boolean tryConnection() {
        boolean tryToConnect = connect();
        try {
            con.close();
        } catch (Exception e) {
            tryToConnect = false;
        }
        return tryToConnect;
    }


    public void killConnection() {
        this.userID = null;
        this.password = null;
        try {
            con.close();
        } catch (SQLException e) {
        }
    }

    @Override
    public Alarm.Status getAlarmStatus() {
        return Alarm.Status.OFF;
    }

}
