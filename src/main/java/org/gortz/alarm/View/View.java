package org.gortz.alarm.View;
import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Loggers.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/**
 * Created by adrian on 01/04/16.
 */
public class View {
    Controller myController;
    boolean started = false;
    Thread socket;
    Logger myLogger;
    protected Socket clientSocket;
    //Constructor
    public View(String[] args){
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                myLogger.write("server", "Shutting down java server", 5);
                try{
                    clientSocket = new Socket("localhost", 1967);
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    outToServer.writeBytes("server:stop\n");
                    clientSocket.close();
                }catch(Exception e){e.printStackTrace();}
            }
        });

        start(args);
    }

    //Called by the constructor to start View
    void start(String[] args) {
        myLogger = Logger.getInstance();

        if(args.length == 0){
            myLogger.write("Server", "No argument supplied", 3);
            return;
        }


            String input = "";

            for( int i = 0; i< args.length; i++){
                input = args[i].toLowerCase();
                switch(input){
                    case "start":
                        if(!started) {
                            myLogger.write("server","Booting up system",5);
                            started = true;
                            myController = new Controller();
                            //Create socket thread to read input from website
                            socket = new Thread(new JavaSocket());
                            socket.start();

                        }else myLogger.write("server","System is already running",3);
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
        Logger socketLogger = Logger.getInstance();
        volatile boolean running;

        protected java.net.ServerSocket socket;
        protected final int port = 1967;
        protected java.net.Socket connection;
        protected String command = new String();
        protected String responseString = new String();

        @Override
        public void run() {
            socketLogger.write("server","socket is up and running",2);
            try {
                running = true;
                waitForMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void waitForMessage() throws IOException {
            socket = new java.net.ServerSocket(port,0, InetAddress.getByName(null));

            while(running)
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
            myLogger.write("server", "Stopping thread waitForMessage.", 3);
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
                    return myController.SirenStatus();
                case "ServerStatus":
                    return myController.serverAliveCheck();
                case "stop":
                    running = false;
                    return "stopping thread.";
                default:
                    return "Error invalid input" + input;
            }
        }
    }





}

