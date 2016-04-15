package org.gortz.alarm.View;
import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Alarm;
import org.gortz.alarm.model.Logger;
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
    Boolean testing = true;
    Thread socket;
    //Constructor
    public View(){
        myController = new Controller();

        //Create socket thread to read input from website
        socket = new Thread(new JavaSocket());
        socket.start();
        start();
    }

    //Called by the constructor to start View
    void start() {
        print("Booting up system");

        //If testing is set to true, we will be given a prompt to enter action manually.
        if(testing){
            boolean exit = false;
            String input;
            Scanner in = new Scanner(System.in);
            while (!exit) {
                print("Enter Input");
                input = in.next();
                input.toLowerCase();

                switch(input){
                    case "exit" :
                        return;

                    default:
                        print("Invalid input");
                }
            }
        } // End of testing part


    }

    // An Easier way to print
    void print(String message) {
        System.out.println(message);
    }



    /**
     * Created by adrian on 04/04/16.
     */
    public class JavaSocket implements Runnable {
        Logger socketLogger = new Logger("websiteConnectionServer");


        protected java.net.ServerSocket socket;
        protected final int port = 1967;
        protected java.net.Socket connection;
        protected String command = new String();
        protected String responseString = new String();

        @Override
        public void run() {
            socketLogger.write("socket is up and running");
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

                    // get output handler
                    DataOutputStream response = new DataOutputStream(connection.getOutputStream());

                    // get input reader
                    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                    BufferedReader input = new BufferedReader(inputStream);
                    //Get input
                    command = input.readLine();

                    // print message
                    socketLogger.write("Command: " + command);
                    responseString = InterpretMessage(command);

                    response.writeBytes(responseString+"\n");
                    response.flush();
                    response.close();
                } catch (IOException e) {
                    //Logger
                    //e.printStackTrace();
                }
            }
        }
        private String InterpretMessage(String input){
            switch(input) {
                case "turn on alarm":
                    if(myController.changeAlarmStatus(Alarm.Status.ON) == true) return "succeeded";
                    else return "failed";
                case "turn off alarm":
                    if(myController.changeAlarmStatus(Alarm.Status.OFF) == true) return "succeeded";
                    else return "failed";
                case "check alarm status":
                    return myController.checkAlarmStatus();
                default:
                    return "Error invalid input";
            }
        }
    }





}

