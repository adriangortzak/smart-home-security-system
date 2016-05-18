package org.gortz.alarm.model;

/**
 * Created by root on 17/05/16.
 */
public interface SensorData extends Comparable{
    Object get(String attribute);
    //boolean isSame(Object o);
}
