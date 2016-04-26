package org.gortz.alarm.model;

/**
 * Created by jimmy on 4/23/16.
 */
public class CommandObject {
    private String protocol = "Null";
    private String model = "Null";
    private String house = "Null";
    private String unit = "Null";
    private String group = "Null";
    private String code = "Null";
    private String method = "Null";

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

    public void print(){
        System.out.println("------------------Printed CommandObject--------------------");
        System.out.println("Protocol: " + this.getProtocol());
        System.out.println("Model: " + this.getModel());
        if(!this.getHouse().equals("Null")) System.out.println("House: " + this.getHouse());
        if(!this.getUnit().equals("Null")) System.out.println("Unit: " + this.getUnit());
        if(!this.getGroup().equals("Null")) System.out.println("Group: " + this.getGroup());
        if(!this.getCode().equals("Null")) System.out.println("Code: " + this.getCode());
        System.out.println("-----------------------------------------------------------");
    }

    public boolean compareTo(CommandObject co){
        if(!this.getProtocol().equals(co.getProtocol())) {
            return false;
        }
        //Problem with config file requiring model: "selflearning-switch" while tellstick receives only "selflearning" from messages for arctech.
        else if( !(this.getModel().equals("selflearning") && (co.getModel().equals("selflearning-switch") || co.getModel().equals("selflearning"))) ){
            if(!this.getModel().equals(co.getModel())){
                return false;
            }
        }
        switch (this.getProtocol()){
            case "arctech":
                if(!this.getHouse().equals(co.getHouse())){
                    return false;
                }
                else if(!this.getUnit().equals(co.getUnit())){
                    return false;
                }
                return true;
            case "sartano":
                     if(!this.getCode().equals(co.getCode())){
                        return false;
                     }
                return true;
            default:
                return false;

        }
    }
}
