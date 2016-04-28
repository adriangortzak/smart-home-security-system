package org.gortz.alarm.View;
import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Loggers.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Scanner;
/**
 * Created by adrian on 01/04/16.
 */
public class View {
    Controller myController;
    boolean started = false;
    Thread socket;
    Logger myLogger;
    //Constructor
    public View(){
        start();
    }

    //Called by the constructor to start View
    void start() {

            myLogger = Logger.getInstace();
            String input;
            Scanner in = new Scanner(System.in);
            while (true){

                input = in.next();
                input.toLowerCase();
                switch(input){
                    case "start":
                        if(!started) {
                            myLogger.write("server","Booting up system",3);
                            started = true;
                            myController = new Controller();
                            //Create socket thread to read input from website
                            socket = new Thread(new JavaSocket());
                            socket.start();

                        }else print("System is already running");
                        break;
                    case "exit" :
                        myLogger.write("server", "Shutting down view.", 3);
                        return;
                    case "trigger" :
                        myController.triggerAlarm("Terminal");
                        break;
                    default:
                        print("Invalid input");
                }

        }


    }



    // An Easier way to print
    void print(String message) {
        System.out.println(message);
    }



    /**
     * Created by adrian on 04/04/16.
     */
    public class JavaSocket implements Runnable {
        Logger socketLogger = Logger.getInstace();


        protected java.net.ServerSocket socket;
        protected final int port = 1967;
        protected java.net.Socket connection;
        protected String command = new String();
        protected String responseString = new String();

        @Override
        public void run() {
            socketLogger.write("server","socket is up and running",2);
            try {
                waitForMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void waitForMessage() throws IOException {
            socket = new java.net.ServerSocket(port,0, InetAddress.getByName(null));

            while(true)
            {
                // open socket
                try {
                    connection = socket.accept();
                    String user = null;
                    // get output handler
                    DataOutputStream response = new DataOutputStream(connection.getOutputStream());

                    // get user reader
                    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                    BufferedReader input = new BufferedReader(inputStream);
                    //Get user
                    command = input.readLine();
                    String commandPart[] = command.split(":");
                    user = commandPart[0];
                    command = commandPart[1];
                    // print message
                    socketLogger.write(user,"Command: " + command ,3);
                    responseString = InterpretMessage(command, user);

                    response.writeBytes(responseString+"\n");
                    response.flush();
                    response.close();
                } catch (IOException e) {
                    //Loggers
                    //e.printStackTrace();
                }
            }
        }
        private String InterpretMessage(String input, String user){
            switch(input) {
                case "trigger":
                    myController.triggerAlarm(user);
                    return "triggered";
                case "turn on alarm":
                    if(myController.changeAlarmStatus(Alarm.Status.ON, user) == true) return "succeeded";
                    else return "failed";
                case "turn off alarm":
                    if(myController.changeAlarmStatus(Alarm.Status.OFF, user) == true) return "succeeded";
                    else return "failed";
                case "check alarm status":
                    return myController.checkAlarmStatus();
                case "SirenStatus":
                    return myController.checkAlarmStatus();
                case "ServerStatus":
                    return myController.serverAliveCheck();
                default:
                    return "Error invalid input" + input;
            }
        }
    }





}

