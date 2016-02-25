package com.easypost.model;

public class Error {
    String field;
    String message;

    public String getField() { return field; }
    public void setField(String code) {
        this.field = code;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }
}