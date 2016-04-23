package org.gortz.alarm.model;

/**
 * Created by jimmy on 4/23/16.
 */
public class CommandObject {
    private String protocol;
    private String model;
    private String house;
    private String unit;
    private String group;
    private String code;
    private String method;

    public CommandObject(String protocol, String model, String house, String unit, String group, String code, String method){
        this.protocol = protocol;
        this.model = model;
        this.house = house;
        this.unit = unit;
        this.group = group;
        this.code = code;
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getModel() {
        return model;
    }

    public String getHouse() {
        return house;
    }

    public String getUnit() {
        return unit;
    }

    public String getGroup() {
        return group;
    }

    public String getCode() {
        return code;
    }

    public String getMethod() {
        return method;
    }
}
