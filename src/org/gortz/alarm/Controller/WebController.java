package org.gortz.alarm.Controller;

import org.gortz.alarm.model.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by adrian on 04/04/16.
 */
public class WebController implements Runnable {
    Logger webLogger = new Logger("websiteConnectionServer");

    protected ServerSocket socket;
    protected final int port = 9005;
    protected Socket connection;
    protected String command = new String();
    protected String responseString = new String();

    @Override
    public void run() {
        webLogger.write("WebController is up and running");

        webLogger.write("Waiting for signal");
        try {
            waitForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForMessage() throws IOException {
        socket = new ServerSocket(port,0, InetAddress.getByName(null));

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

                // process input
                //Logger.log("Command: " + command);
                responseString = command + " MC2 It Works!";

                response.writeBytes(responseString);
                response.flush();
                response.close();
            } catch (IOException e) {
                //Logger
                //e.printStackTrace();
            }
        }
    }
}
