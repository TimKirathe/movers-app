package com.moringaschool.moversapp.models;

public class PostBookingResponse {

    private int status;
    private String message;
    private Booking data;

    public PostBookingResponse(int status, String message, Booking data) {
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

    public Booking getData() {
        return data;
    }

    public void setData(Booking data) {
        this.data = data;
    }
}
