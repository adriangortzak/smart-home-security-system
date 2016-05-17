package org.gortz.alarm.model;

/**
 * Created by jimmy on 4/23/16.
 */
public class SensorData {
    private String protocol;
    private String id;
    private String model;
    private String humidity;
    private String temp;

    /**
     * Encapsulates Tellstick sensor data.
     * @param protocol Tellstick sensor protocol e.g. //TODO find name of tellstick sensor protocols etc.
     * @param id Sensor ID
     * @param model Sensor model e.g. TemperatureHumidity
     * @param humidity Humidity data
     * @param temp Temperature data
     */
    public SensorData(String protocol, String id, String model, String humidity, String temp){
        this.protocol = protocol;
        this.id = id;
        this.model = model;
        this.humidity = humidity;
        this.temp = temp;
    }

    /**
     * Get sensor protocol.
     * @return protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Get sensor id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get sensor model
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Get sensor humidity data
     * @return humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Get sensor temperature data
     * @return temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * Compare two SensorData objects.
     * @param sd Other SensorData object to compare to.
     * @return true or false
     */
    public boolean compareTo(SensorData sd){
        if(!this.getProtocol().equals(sd.getProtocol())) return false;
        else if(!this.getId().equals(sd.getId())) return false;
        else if(!this.getModel().equals(sd.getModel())) return false;
        else if(!this.getHumidity().equals(sd.getHumidity())) return false;
        else if(!this.getTemp().equals(sd.getTemp())) return false;
        else return true;
    }
}
