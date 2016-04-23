package org.gortz.alarm.model.Sensors;

import net.jstick.api.RawEvent;
import net.jstick.api.RawEventListener;
import net.jstick.api.Tellstick;
import org.gortz.alarm.model.CommandObject;
import org.gortz.alarm.model.Loggers.Logger;
import org.gortz.alarm.model.SensorData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jimmy on 4/22/16.
 */
public class Listen implements Runnable{
    Tellstick ts;
    private volatile boolean listen = true;
    Logger myLogger;


    public Listen(Tellstick ts) {
        this.ts = ts;
        myLogger = new Logger("CommandsReceived.txt");
    }

    public void terminate(){
        listen = false;
    }
    @Override
    public void run() {
        listen();
    }

    public void listen(){

        RawEventListener rel = new RawEventListener() {
            @Override
            public void eventReceived(RawEvent rawEvent) {
                String temp = rawEvent.getData();
                String regexPattern = "class:(?<type>command|sensor);protocol:(?<protcol>\\w+);(?:id:(?<id>\\d+);)?model:(?<model>\\w+);(?:(?:house:(?<house>\\d+);unit:(?<unit>\\d);)?(?:group:(?<group>\\d);)?(?:code:(?<code>\\w+);)?method:(?<method>[a-z]+);)?(?:humidity:(?<humidity>\\d+);temp:(?<temp>\\d+\\.\\d+);)?";
                Pattern pattern = java.util.regex.Pattern.compile(regexPattern);
                Matcher matcher = pattern.matcher(temp);
                if(matcher.matches()){
                    if(matcher.group(1).equals("command")){
                        CommandObject c = new CommandObject(matcher.group(2),matcher.group(4),matcher.group(5),matcher.group(6),matcher.group(7),matcher.group(8), matcher.group(9));
                        myLogger.write(c.getMethod(),3);
                    }
                    else if(matcher.group(1).equals("sensor")){
                        SensorData s = new SensorData(matcher.group(2),matcher.group(3),matcher.group(4),matcher.group(10), matcher.group(11));
                        myLogger.write("Current temp: " + s.getTemp() + " degrees Celsius and current humidity: " + s.gethumidity() + "%",3);
                    }
                    else{
                        myLogger.write("Received unknown sensor input", 3);
                    }

                }
            }
        };

        ts.addRawEventListener(rel);

        while(listen){

        }
        try{
            ts.removeRawEventListener(rel);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
