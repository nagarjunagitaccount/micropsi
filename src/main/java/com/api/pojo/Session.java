package com.api.pojo;

public class Session {
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public Session(String duration) {
        this.duration = duration;
    }

    String duration;
}
