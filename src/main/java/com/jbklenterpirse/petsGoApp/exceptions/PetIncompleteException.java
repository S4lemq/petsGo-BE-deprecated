package com.jbklenterpirse.petsGoApp.exceptions;

public class PetIncompleteException extends RuntimeException{

    private String errorCode;

    public PetIncompleteException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
