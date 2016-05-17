package org.gortz.alarm.model;


/**
 * Created by Adrian on 4/13/16.
 */
public interface Sensor {
    boolean turnOn(int deviceID);
    boolean turnOff(int deviceID);
    void startListener();
    void close();
    SensorData[] getConfiguredDevices(int[] list);
    void sendCommand(int id,String state);
}
