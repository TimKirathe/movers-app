package com.moringaschool.moversapp.models;

public class Calculate {

    private int id;
    private String latFrom;
    private String longFrom;
    private String latTo;
    private String longTo;

    public Calculate(String latFrom, String longFrom, String latTo, String longTo) {
        this.latFrom = latFrom;
        this.longFrom = longFrom;
        this.latTo = latTo;
        this.longTo = longTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatFrom() {
        return latFrom;
    }

    public void setLatFrom(String latFrom) {
        this.latFrom = latFrom;
    }

    public String getLongFrom() {
        return longFrom;
    }

    public void setLongFrom(String longFrom) {
        this.longFrom = longFrom;
    }

    public String getLatTo() {
        return latTo;
    }

    public void setLatTo(String latTo) {
        this.latTo = latTo;
    }

    public String getLongTo() {
        return longTo;
    }

    public void setLongTo(String longTo) {
        this.longTo = longTo;
    }
}
