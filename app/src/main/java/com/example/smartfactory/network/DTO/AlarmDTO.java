package com.example.smartfactory.network.DTO;

import java.time.LocalDateTime;

public class AlarmDTO {

    Long index; // 알람 구분 인덱스
    Long sensorIndex; // 센서 고유 인덱스
    // on/off알람일경우 ?
    double minimum; // 알람 최저 기준치
    double maximum; // 알람 최고 기준치
    private String createdDate;
    private String modifiedDate;

    @Override
    public String toString() {
        return "AlarmDTO{" +
                "index=" + index +
                ", sensorIndex=" + sensorIndex +
                ", minimum=" + minimum +
                ", maximum=" + maximum +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                '}';
    }

    public AlarmDTO(Long index, Long sensorIndex, double minimum, double maximum, String createdDate, String modifiedDate) {
        this.index = index;
        this.sensorIndex = sensorIndex;
        this.minimum = minimum;
        this.maximum = maximum;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        System.out.println("AlarmDTO.AlarmDTO on 생성자||  "+ this);
    }

    public Long getIndex() {
        return index;
    }

    public Long getSensorIndex() {
        return sensorIndex;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String  getModifiedDate() {
        return modifiedDate;
    }
}
