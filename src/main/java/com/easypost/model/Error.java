package com.easypost.model;

import java.util.List;

public class Error {
    String code;
    String message;
    List<String> errors;

    public String getCode() { return code; }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
