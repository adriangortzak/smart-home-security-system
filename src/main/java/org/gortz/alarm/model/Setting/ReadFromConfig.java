package org.gortz.alarm.model.Setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by root on 07/06/16.
 */
public class ReadFromConfig {


    public static String getSetting(String setting){
        String configSetting = null;

        //to load application's properties, we use this class
        Properties mainProperties = new Properties();

        FileInputStream file;

        //the base folder is ./, the root of the main.properties file
        File jarPath=new File(ReadFromConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();
        System.out.println(" propertiesPath-"+propertiesPath);
        String path = propertiesPath+"/server.properties";

        //load the file handle for main.properties
        try {
            file = new FileInputStream(path);
            //load all the properties from this file
            mainProperties.load(file);

            //load all the properties from this file
            mainProperties.load(file);

            //we have loaded the properties, so close the file handle
            file.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            //TODO print that we cant find the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        //retrieve the property we are intrested in
        configSetting = mainProperties.getProperty(setting);

        return configSetting;

    }
}
