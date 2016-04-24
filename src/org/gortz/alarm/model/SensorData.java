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

    public SensorData(String protocol, String id, String model, String humidity, String temp){
        this.protocol = protocol;
        this.id = id;
        this.model = model;
        this.humidity = humidity;
        this.temp = temp;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String gethumidity() {
        return humidity;
    }

    public String getTemp() {
        return temp;
    }

}
