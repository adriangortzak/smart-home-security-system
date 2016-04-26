package org.gortz.alarm.model.Sensors;
import net.jstick.api.Tellstick;

/**
 * Created by jimmy on 4/13/16.
 */
public class TellstickDuo implements org.gortz.alarm.model.Sensor {
    Tellstick ts;
    Listen l;

    public TellstickDuo(){
        ts = new Tellstick(false);
    }


    public boolean turnOn(int deviceID) {
        int status = ts.sendCmd(deviceID, "ON");
        if (status == 0) return true;
        else return false;
    }

    public boolean turnOff(int deviceID) {
        int status = ts.sendCmd(deviceID, "OFF");
        if (status == 0) return true;
        else return false;
    }

    public void startListener() {
        l = new Listen(ts);
        l.listen();
    }

    public void stopListener() {
        l.terminate();
        System.out.println("Stopped listener\n");
    }

    public void close(){
        ts.close();
    }

}
