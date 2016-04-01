package org.gortz.larm;
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

        //Take input for testing purposes
    }

    //Called by the constructor to start View
    void start() {
        print("Booting up system");

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
        }
    }

    // An Easier way to print
    void print(String message) {
        System.out.println(message);
    }
}

