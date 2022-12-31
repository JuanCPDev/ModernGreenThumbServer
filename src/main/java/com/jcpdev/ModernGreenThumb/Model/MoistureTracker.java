package com.jcpdev.ModernGreenThumb.Model;

public class MoistureTracker {
    private String thumbnailUrl;
    private String name;
    private int value;
    private String lastTimeChecked;

    public MoistureTracker(String name) {
        this.name = name;
        value=0;
        lastTimeChecked="Never";
        thumbnailUrl="none";
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(String lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
