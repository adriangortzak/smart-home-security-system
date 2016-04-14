package org.gortz.alarm.View;

import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Logger;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;


/**
 * Created by adrian on 04/04/16.
 */
public class JavaSocket implements Runnable {
    Logger socketLogger = new Logger("websiteConnectionServer");
    Controller controller = new Controller();

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
                socketLogger.write("Waiting for new connection");
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
                System.out.println(responseString);
                response.writeBytes(responseString);
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
            case "test":
                return "testar";

            default:
                return "Error invalid input";
        }
    }
}
