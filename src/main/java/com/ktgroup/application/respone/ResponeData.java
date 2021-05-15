package com.ktgroup.application.respone;

import java.util.HashMap;

public class ResponeData {
    private String status;
    private String message;
    private Object result;

    public ResponeData() {
        result = new HashMap<>();
    }

    public ResponeData(String status, String message, Object result) {
        super();
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
