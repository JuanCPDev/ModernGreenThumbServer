package com.jcpdev.ModernGreenThumb.Model;

public class MoistureTracker {

    public MoistureTracker(String name) {
        this.name = name;
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    private int value;

    public String getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(String lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }

    private String lastTimeChecked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
