package com.example.smartfactory.network.DTO;

public class SensorValue {
    String sensorName;
    String sensorValue;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }



    public SensorValue(String sensorName, String sensorValue) {
        this.sensorName = sensorName;
        this.sensorValue = sensorValue;
    }
}
