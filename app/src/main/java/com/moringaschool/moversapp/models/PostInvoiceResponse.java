package com.moringaschool.moversapp.models;

public class PostInvoiceResponse {

    private int status;
    private String message;
    private Invoice data;

    public PostInvoiceResponse(int status, String message, Invoice data) {
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

    public Invoice getData() {
        return data;
    }

    public void setData(Invoice data) {
        this.data = data;
    }
}
