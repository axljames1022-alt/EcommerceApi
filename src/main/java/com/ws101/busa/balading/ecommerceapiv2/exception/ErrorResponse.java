package com.ws101.busa.balading.ecommerceapiv2.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> errors;
    private Map<String, String> details;

    // Constructor for validation errors - Task 5 format
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, List<String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }

    // Constructor for simple errors
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public List<String> getErrors() { return errors; }
    public Map<String, String> getDetails() { return details; }
}