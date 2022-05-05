package com.example.library;

public record ExceptionResponseEntity(
        int status,
        String statusName,
        Object details
) {
    public Object getDetails() {
        return details;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getStatus() {
        return status;
    }
}
