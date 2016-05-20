package org.gortz.alarm.View;
import org.gortz.alarm.Controller.Controller;
import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Loggers.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

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
		    shutdown();
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
		startup();
		break;
	    default:
		myLogger.write("Server", "Invalid input", 2);
	    }
	}
    }

    private void shutdown(){
        myLogger.write("server", "Shutting down java server", 5);
        try{
            clientSocket = new Socket("localhost", 1967);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes("server:stop\n");
            clientSocket.close();
        }catch(Exception e){e.printStackTrace();}
        started = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void startup(){
        if(!started) {
            myLogger.write("server","Booting up system",5);
            started = true;
            myController = new Controller();
            //Create socket thread to read input from website
            socket = new Thread(new JavaSocket());
            socket.start();

        }else myLogger.write("server","System is already running",3);
    }
    private void restart(){
        shutdown();
        startup();
    }

    /**
     * Created by adrian on 04/04/16.
     */
    public class JavaSocket implements Runnable {
        Logger socketLogger = Logger.getInstance();
        volatile boolean running;


        protected final int port = 1967;
        protected java.net.Socket connection;
        protected String command = new String();
        protected String responseString = new String();
        protected java.net.ServerSocket socket;

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
        private void updateSettings(){
            myController.updateSettings();
	    myLogger.write("Server", "Settings updated!", 3);

        }

        public void waitForMessage() throws IOException {
	    socket= new java.net.ServerSocket();
            socket.setReuseAddress(true);
	    socket.bind(new InetSocketAddress(InetAddress.getByName(null),port),0);
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
			// toString message
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
            String msg ="";
            switch(input) {
	    case "restart":
		restart();
		break;
	    case "trigger":
		myController.triggerAlarm(user);
		msg = "triggered";
		break;
	    case "turn on alarm":
		if(myController.changeAlarmStatus(Alarm.Status.ON, user) == true) msg = "succeeded";
		else msg = "failed";
		break;
	    case "turn off alarm":
		if(myController.changeAlarmStatus(Alarm.Status.OFF, user) == true) msg = "succeeded";
		else msg = "failed";
		break;
	    case "check alarm status":
		msg = myController.checkAlarmStatus();
		break;
	    case "sirenStatus":
		msg = myController.sirenStatus();
		break;
	    case "ServerStatus":
		msg = myController.serverAliveCheck();
		break;
	    case "reloadSettings":
		updateSettings();
		msg = "Settings updated!";
		break;
	    case "stop":
		try {
		    socket.close();
		    while (!socket.isClosed()){}
		} catch (IOException e) {
		    e.printStackTrace();
		}
		running = false;
		/**
		   try {
		   //socket.close();
		   //socket = null;
		   } catch (IOException e) {
		   myLogger.write("server","Couldn't close socket",3);
		   }
		**/
		msg = "stopping thread.";
		break;
	    default:
		msg = "Error invalid input" + input;
            }
            return msg;
        }
    }





}

