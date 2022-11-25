package com.example.smartfactory.network.DTO;

import java.time.LocalDateTime;

public class SensorValueDTO{
    String sensorName;
    String sensorValue;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public SensorValueDTO(String sensorName, String sensorValue, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.sensorName = sensorName;
        this.sensorValue = sensorValue;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
