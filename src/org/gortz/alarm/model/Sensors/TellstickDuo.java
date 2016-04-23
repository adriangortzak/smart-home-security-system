package org.gortz.alarm.model.Sensors;
import net.jstick.api.Tellstick;

/**
 * Created by jimmy on 4/13/16.
 */
public class TellstickDuo implements org.gortz.alarm.model.Sensor {
    Tellstick ts;
    Thread listener;
    Listen l;

    public TellstickDuo(){
        ts = new Tellstick(false);
    }

    @Override
    public boolean turnOn(int deviceID) {
        int status = ts.sendCmd(1, "ON");
        if (status == 0) return true;
        else return false;
    }

    @Override
    public boolean turnOff(int deviceID) {
        int status = ts.sendCmd(1, "OFF");
        if (status == 0) return true;
        else return false;
    }

    @Override
    public void startListener() {
        l = new Listen(ts);
        listener = new Thread(l);
        listener.start();
    }

    @Override
    public void stopListener() {
        l.terminate();
        try {
            listener.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopped listener and joined threads...\n");
    }

    @Override
    public void close(){
        ts.close();
    }
}
