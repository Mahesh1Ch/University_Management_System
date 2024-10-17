package com.management.user.user_management_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message); // Call the parent class constructor with the message
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause); // Call the parent class constructor with message and cause
    }
}
