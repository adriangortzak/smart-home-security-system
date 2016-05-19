package org.gortz.alarm.model;

/**
 * Created by root on 17/05/16.
 */
public interface SensorData extends Comparable{
    /**
     * Retrieve internal attribute specified by string.
     * @param attribute name of attribute.
     * @return specified attribute.
     */
    Object get(String attribute);
}
