package com.moringaschool.moversapp.models;

import java.util.List;

public class ResponseBookingList {

    private int status;
    private String message;
    private List<Booking> data;

    public ResponseBookingList(int status, String message, List<Booking> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booking> getData() {
        return data;
    }

    public void setData(List<Booking> data) {
        this.data = data;
    }
}
