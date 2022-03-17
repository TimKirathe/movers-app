package com.moringaschool.moversapp.models;

public class ResponseDistance {

    private int status;
    private String message;
    private Double data;

    public ResponseDistance(int status, String message, Double data) {
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

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }
}
