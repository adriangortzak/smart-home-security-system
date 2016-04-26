package org.gortz.alarm.model.Databases;

import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Database;
import org.gortz.alarm.model.Notification;
import org.gortz.alarm.model.Notifications.PushBullet;

import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.sql.*;

import static org.gortz.alarm.model.Alarms.Alarm.Status.OFF;


/**
 * Created by adrian on 18/04/16.
 */
public class mysql implements Database {
    // DB connection variable
    static protected Connection con;
    // DB access variables
    private String URL = "jdbc:mysql://127.0.0.1:3306/SHSS";
    private String driver = "com.mysql.jdbc.Driver";
    private String userID = null;
    private String password = null;

    public mysql(String username, String password) {
        this.userID = username;
        this.password = password;
    }
    /**
     * Create a connection to the database and returns true on success.
     * @return succeeded
     *
     */
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
            e.printStackTrace();
            couldConnect = false;
        }
        return couldConnect;
    }

    /**
     * Ends the connection to the database.
     */
    public void killConnection() {
        try {
            con.close();
        } catch (SQLException e) {
        }
    }

    /**
     * Retrivse the last saved alarm status from database
     * @return alarmStatus
     */
    public boolean getAlarmStatus() throws NoConnectionPendingException {
        if(connect()) {
            boolean result = false;
            Statement stmt;
            //-------------------query-----------------------
            String  query = "SELECT activeAlarm FROM alarmStatus";
            //-----------------------------------------------
            byte svar = -1;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                svar = rs.getByte("activeAlarm");

            }catch (SQLException e){
                e.printStackTrace();
            }
           switch (svar){
                case 0:
                    result = false;
                    break;
               case 1:
                   result = true;
                   break;
               case -1:
                   throw new NotYetConnectedException();
            }
            killConnection();
            return result;
        }
        else throw new NoConnectionPendingException();
    }


    /**
     * Changes the saved status to the new status.
     * @param newStatus
     */
    public void updateAlarmStatus(Alarm.Status newStatus) {

        if(connect()) {
            String newState = null;
            switch (newStatus){
                case OFF: newState = "0";
                    break;
                case ON: newState = "1";
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            PreparedStatement statment;
            String prepareStatement = "UPDATE `alarmStatus` SET `activeAlarm`=? WHERE `id`=?";
            try {
                statment = con.prepareStatement(prepareStatement);
                statment.setString(1, newState);
                statment.setString(2, "1");
                statment.executeUpdate();
                con.commit();
                killConnection();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else throw new NoConnectionPendingException();
    }


    public void writeHistory(String user, String message) {
        if(connect()){
            PreparedStatement statment;
            //-------------------query-----------------------
            String  prepareStatement = "INSERT INTO `history` (`id`, `date`, `user`, `message`) VALUES (NULL, CURRENT_TIMESTAMP, ?, ?);";
            //-----------------------------------------------
            try {
                statment = con.prepareStatement(prepareStatement);
                statment.setString(1, user);
                statment.setString(2, message);
                statment.executeUpdate();
                con.commit();
                killConnection();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getServerSettingInt(String setting) {
        if(connect()) {
            PreparedStatement statment;

            //-------------------query-----------------------
            String  sql  = "SELECT Value FROM `serverSettings` WHERE Setting ="+'"'+setting+'"';
            //-----------------------------------------------

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                return rs.getInt("value");
            } catch (SQLException e) {
                e.printStackTrace();
              throw new NoConnectionPendingException();
            }
        }
        else throw new NoConnectionPendingException();

    }

    @Override
    public Notification[] getNotifications() {
        String query;
        Notification[] notifications;
        if (connect()) {

            try {
                query ="SELECT COUNT(*) FROM `notifications` WHERE active = 1";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                  int notificationCount = rs.getInt("count(*)");
                if(notificationCount !=0){
                    notifications = new Notification[notificationCount];
                    query = "SELECT type,token FROM `notifications` WHERE active = 1";
                    rs = stmt.executeQuery(query);
                    while (rs.next()){
                        notificationCount--;
                        if(rs.getString("type").equals("pushbullet"))  notifications[notificationCount] = new PushBullet(rs.getString("token"));
                    }
                    return notifications;
                }else return new Notification[0];
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NoConnectionPendingException();
            }
        } else throw new NoConnectionPendingException();
    }

    @Override
    public int[] getTriggerDevices() {
        int[] a = {1};
        //TODO fix
        return a;
    }

}
