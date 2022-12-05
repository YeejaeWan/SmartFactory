package com.example.smartfactory.Activity.SensorValueAdapter;

public class SensorValueItem {
    long sensorIndex;
    String name;
    String message;
    int resourceId;

    public SensorValueItem(long sensorIndex, String name, String message) {
        this.sensorIndex=sensorIndex;
        this.name = name;
        this.message= message;
    }

    public long getSensorIndex() {
        return sensorIndex;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setSensorIndex(long sensorIndex) {
        this.sensorIndex = sensorIndex;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}