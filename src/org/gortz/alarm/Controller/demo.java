package org.gortz.alarm.Controller;

/**
 * Created by jimmy on 4/13/16.
 */
import java.util.ArrayList;

import net.jstick.api.Device;
import net.jstick.api.Tellstick;

public class demo {


    public static void main(String[] args) throws InterruptedException {

        // Get a Tellstick object and pass boolean true as argument to enable debugging
        Tellstick ts = new Tellstick(true);

        // Let's send ON command to device with id 1
        int status = ts.sendCmd(1, "ON");
        System.out.println("Turning switch on... Status code: "+status+" The return message was \"" + ts.getErrorString(status) + "\"");
        Thread.sleep(5000);

        // Let's sent OFF command to device with id 1
        status = ts.sendCmd(1, "OFF");
        System.out.println("Turning switch off... Status code: "+status+" The return message was \"" + ts.getErrorString(status) + "\"");



        // Close the communication to Tellstick to allow GC
        ts.close();
    }

}
