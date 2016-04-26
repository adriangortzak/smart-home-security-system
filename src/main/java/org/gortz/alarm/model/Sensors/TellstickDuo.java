package org.gortz.alarm.model.Sensors;
import net.jstick.api.Device;
import net.jstick.api.Tellstick;
import org.gortz.alarm.model.CommandObject;

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
    public CommandObject[] getConfiguredDevices(int[] list) {
        CommandObject co[] = new CommandObject[list.length];
        Device d;
        int j = 0;
        for (int i : list) {
            d = ts.getDevice(i);
            switch (d.getProto()){
                case "arctech":
                    co[j++] = new CommandObject(d.getProto(),d.getModel(),ts.getDeviceParameter(i,"house", "Null"),ts.getDeviceParameter(i, "unit", "Null"), "Null", "Null","Null"); //TODO koll om det ska vara null eller Null eller NULL
                    break;
                case "sartano":
                    co[j++] = new CommandObject(d.getProto(),d.getModel(),ts.getDeviceParameter(i,"house", "Null"),ts.getDeviceParameter(i, "unit", "Null"), "Null",ts.getDeviceParameter(i,"code", "Null"), "Null"); //TODO koll om det ska vara null eller Null eller NULL
                    break;
            }

        }
        return co;
    }

}
