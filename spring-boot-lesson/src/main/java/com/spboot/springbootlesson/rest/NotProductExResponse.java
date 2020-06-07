package com.spboot.springbootlesson.rest;

import java.util.Date;

public class NotProductExResponse {
    private int status;
    private String message;
    private Date date;

    public NotProductExResponse() {
        date = new Date();
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

    public Date getDate() {
        return date;
    }
}
