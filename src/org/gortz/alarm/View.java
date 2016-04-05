package org.gortz.alarm;
import org.gortz.alarm.Controller.Controller;

import java.util.Scanner;
/**
 * Created by adrian on 01/04/16.
 */
public class View {

    Controller myController;
    Boolean testing = true;

    //Constructor
    View(){
        myController = new Controller();
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
}

