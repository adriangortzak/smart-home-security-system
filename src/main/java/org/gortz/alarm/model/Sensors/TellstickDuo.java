package org.gortz.alarm.model.Sensors;
import net.jstick.api.Device;
import net.jstick.api.RawEvent;
import net.jstick.api.RawEventListener;
import net.jstick.api.Tellstick;
import org.gortz.alarm.model.Alarms.Alarm;
import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.SensorData;
import org.gortz.alarm.model.Setting.Settings;

import java.lang.invoke.WrongMethodTypeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jimmy on 4/13/16.
 */
public class TellstickDuo implements org.gortz.alarm.model.Sensor {
    Tellstick ts;
    Listen l;
    static TellstickDuo tsD = null;

    private TellstickDuo(){
        ts = new Tellstick(false);
    }

    public static TellstickDuo getInstance(){
        if(tsD == null) {
            tsD = new TellstickDuo();
        }
        return tsD;
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

    @Override
    public SensorData[] getConfiguredDevices(int[] list) {
        CommandObject co[] = new CommandObject[list.length];
        Device device;
        int j = 0;
        for (int i : list) {
            device = ts.getDevice(i);
            switch (device.getProto()){
                case "arctech":
                    co[j++] = new CommandObject(device.getProto(),device.getModel(),ts.getDeviceParameter(i,"house", "Null"),ts.getDeviceParameter(i, "unit", "Null"), "Null", "Null","Null");
                    break;
                case "sartano":
                    co[j++] = new CommandObject(device.getProto(),device.getModel(),ts.getDeviceParameter(i,"house", "Null"),ts.getDeviceParameter(i, "unit", "Null"), "Null",ts.getDeviceParameter(i,"code", "Null"), "Null");
                    break;
            }
        }
        return co;
    }
    public void sendCommand(int id,String state){
        ts.sendCmd(id,state);
        //ts.sendRawCommand("S$k$k$k$k$k$k$k$k$k$k$k$k$k$k$k$k$k$k$kk$$kk$$kk$$}+");
    }

    public class CommandObject implements SensorData {
        private String protocol;
        private String model;
        private String house;
        private String unit;
        private String group;
        private String code;
        private String method;
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
        protected String getProtocol() {
            return protocol;
        }

        /**
         * Get device model.
         * @return model
         */
        protected String getModel() {
            return model;
        }

        /**
         * Get house identification.
         * @return house
         */
        protected String getHouse() {
            return house;
        }

        /**
         * Get unit identification.
         * @return unit
         */
        protected String getUnit() {
            return unit;
        }

        /**
         * Get group id.
         * @return group
         */
        protected String getGroup() {
            return group;
        }

        /**
         * Get identification code.
         * @return code
         */
        protected String getCode() {
            return code;
        }

        /**
         * Get command method
         * @return method
         */
        protected String getMethod() {
            return method;
        }

        /**
         * Outprints information relevant to SHSS for sniffing incoming data.
         */
        public String toString(){ //TODO Return this outprint in string format
            System.out.println("------------------Printed CommandObject--------------------");
            System.out.println("Protocol: " + this.getProtocol());
            System.out.println("Model: " + this.getModel());
            if(!(getHouse() == null)) System.out.println("House: " + this.getHouse());
            if(!(getUnit() == null)) System.out.println("Unit: " + this.getUnit());
            if(!(getGroup() == null)) System.out.println("Group: " + this.getGroup());
            if(!(getCode() == null)) System.out.println("Code: " + this.getCode());
            System.out.println("-----------------------------------------------------------");
            return "";
        }

        /**
         * Check if this CommandObject is registered for protocol archtech or sartano and compares that with another CommandOBject
         * to see if it is a valid
         * @param
         * @return
         */
        public boolean isSame(Object o){
            try{
                return isSame((CommandObject) o);
            }
            catch(Exception e){
                throw new WrongMethodTypeException("Wrong version of isSame is being called");
            }
        }
        public boolean isSame(CommandObject co){
            if(!this.getProtocol().equals(co.getProtocol())) {
                return false;
            }
            //Problem with config file requiring model: "selflearning-switch" while tellstick receives only "selflearning" from messages for arctech.
            else if( !(this.getModel().equals("selflearning") && (co.getModel().equals("selflearning-switch") || co.getModel().equals("selflearning"))) ){
                if(!this.getModel().equals(co.getModel())){
                    return false;
                }
            }
            else{
                System.out.println("Matched on protocol and model!");
            }
            switch (this.getProtocol()){
                case "arctech":
                    if(!this.getHouse().equals(co.getHouse())){
                        return false;
                    }
                    else if(!this.getUnit().equals(co.getUnit())){
                        return false;
                    }
                    System.out.println("Matched also on house and unit");
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


    private class Listen {
        Tellstick ts;
        Logger myLogger;
        RawEventListener rel;
        Settings sett;

        public Listen(Tellstick ts) {
            this.ts = ts;
            myLogger = Logger.getInstance();
            sett = Settings.getInstance();
        }

        public void terminate(){
            try{
                ts.removeRawEventListener(rel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        public void listen(){

            rel = new RawEventListener() {
                @Override
                public void eventReceived(RawEvent rawEvent) {
                    String temp = rawEvent.getData();
                    String regexPattern = "class:(?<type>command|sensor);protocol:(?<protocol>\\w+);(?:id:(?<id>\\d+);)?model:(?<model>\\w+);(?:(?:house:(?<house>\\d+);unit:(?<unit>\\d);)?(?:group:(?<group>\\d);)?(?:code:(?<code>\\w+);)?method:(?<method>[a-z]+);)?(?:humidity:(?<humidity>\\d+);temp:(?<temp>\\d+\\.\\d+);)?";
                    Pattern pattern = java.util.regex.Pattern.compile(regexPattern);
                    Matcher matcher = pattern.matcher(temp);
                    if(matcher.matches()){
                        if(matcher.group(1).equals("command")){
                            SensorData c = new CommandObject(matcher.group(2),matcher.group(4),matcher.group(5),matcher.group(6),matcher.group(7),matcher.group(8), matcher.group(9));
                            for(SensorData curr : sett.getTriggerObject()){
                                //System.out.println(c.toString());
                                //System.out.println("----------------From Settings:-----------------");
                                //curr.toString();
                                //System.out.println("----------------From Settings end:-------------");
                                if(c.isSame(curr)){
                                    myLogger.write("test", "Objects were equal!", 2);
                                    //myLogger.write("server",c.getMethod(),3);
                                    Alarm alarm = Alarm.getInstance();
                                    alarm.trigger("sensor");
                                    break;
                                }else  {
                                    myLogger.write("Server","Sensor but not a trigger",1);
                                }

                            }

                        }
                        /**else if(matcher.group(1).isSame("sensor")){
                         SensorData s = new SensorData(matcher.group(2),matcher.group(3),matcher.group(4),matcher.group(10), matcher.group(11));
                         myLogger.write("server","Current temp: " + s.getTemp() + " degrees Celsius and current humidity: " + s.getHumidity() + "%",3);
                         }
                         else{
                         myLogger.write("server","Received unknown sensor input", 3);
                         }**/
                    }
                }
            };
            ts.addRawEventListener(rel);
        }
    }

}
