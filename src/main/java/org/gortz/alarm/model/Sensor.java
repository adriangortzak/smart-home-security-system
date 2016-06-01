package org.gortz.alarm.model;


/**
 * Created by Adrian on 4/13/16.
 */
public interface Sensor {
    /**
     * Send turnOn command to specified device
     * @param deviceID - id for device
     * @return status
     */
    boolean turnOn(int deviceID);
    /**
     * Send turnOff command to specified device
     * @param deviceID - id for device
     * @return status
     */
    boolean turnOff(int deviceID);

    /**
     * Start listening for messages.
     */
    void startListener();

    /**
     * Safely close object.
     */
    void close();

    /**
     * Retrieve a specified list of configured devices from config.
     * @param list - identifying device IDs.
     * @return list of configured devices.
     */
    SensorData[] getConfiguredDevices(int[] list);
    void sendCommand(int id,String state);
}
