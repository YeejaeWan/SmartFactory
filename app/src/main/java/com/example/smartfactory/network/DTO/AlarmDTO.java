package com.example.smartfactory.network.DTO;

import java.time.LocalDateTime;

public class AlarmDTO {

    Long index; // 알람 구분 인덱스
    Long sensorIndex; // 센서 고유 인덱스
    // on/off알람일경우 ?
    double minimum; // 알람 최저 기준치
    double maximum; // 알람 최고 기준치
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public AlarmDTO(Long index, Long sensorIndex, double minimum, double maximum, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.index = index;
        this.sensorIndex = sensorIndex;
        this.minimum = minimum;
        this.maximum = maximum;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
