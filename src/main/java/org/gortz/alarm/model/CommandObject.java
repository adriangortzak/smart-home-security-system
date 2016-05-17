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

    /**
     * Encapsulates Tellstick command.
     * @param protocol Device protocol e.g. arctech, mandolyn, RisingSun etc.
     * @param model Equipment model e.g. codeswitch, selflearning, dimmer etc.
     * @param house Identification of device together with Unit.
     * @param unit Identification of command together with House.
     * @param group Group ID either 0 or 1. Default is 0.
     * @param code Identification of device.
     * @param method Command method 1, 2 or 3 for turnOn, turnOff and Dim
     */
    public CommandObject(String protocol, String model, String house, String unit, String group, String code, String method){
        this.protocol = protocol;
        this.model = model;
        this.house = house;
        this.unit = unit;
        this.group = group;
        this.code = code;
        this.method = method;
    }

    /**
     * Get device protocol.
     * @return protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Get device model.
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Get house identification.
     * @return house
     */
    public String getHouse() {
        return house;
    }

    /**
     * Get unit identification.
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Get group id.
     * @return group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Get identification code.
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get command method
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Outprints information relevant to SHSS for sniffing incoming data.
     */
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

    /**
     * Check if this CommandObject is registered for protocol archtech or sartano and compares that with another CommandOBject
     * to see if it is a valid
     * @param 
     * @return
     */
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
