package org.gortz.alarm.model;

/**
 * Created by jimmy on 4/13/16.
 */
public interface Sensor {
    boolean turnOn(int deviceID);
    boolean turnOff(int deviceID);
    void startListener();
    void stopListener();
    void close();
    CommandObject[] getConfiguredDevices(int[] list);
}
