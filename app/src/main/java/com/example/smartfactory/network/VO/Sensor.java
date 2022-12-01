package com.example.smartfactory.network.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Sensor {
    private long index;
    private String name;
    private String command;

    public Sensor(long index, String name, String command) {
        this.index = index;
        this.name = name;
        this.command = command;
    }

    public long getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }
}