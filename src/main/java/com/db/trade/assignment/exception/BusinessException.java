package com.db.trade.assignment.exception;

import java.util.Map;

public class BusinessException extends RuntimeException {

    private static final String message = BusinessException.class.getName();
    private Map<String, Object> response;
    private String reason;

    public BusinessException(String message, Map<String, Object> response, String reason) {
        super(message);
        this.response = response;
        this.reason = reason;
    }

    public BusinessException(Map<String, Object> response) {
        super(message);
        this.response = response;
        this.reason = reason;
    }

    public BusinessException(String reason) {
        super(message);
        this.response = response;
        this.reason = reason;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public String getReason() {
        return reason;
    }
}
