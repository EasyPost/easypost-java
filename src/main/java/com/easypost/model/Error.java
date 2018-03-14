package com.easypost.model;

public class Error {
    String field;
    String message;
    String suggestion;
    String code;

    public String getField() { return field; }
    public void setField(String field) {
        System.out.println("SHOBHA with field");
        this.field = field;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuggestion() { return suggestion; }
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getCode() { return code; }
    public void setCode(String code) {
        this.code = code;
    }
}
