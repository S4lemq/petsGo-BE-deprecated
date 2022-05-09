package com.jbklenterpirse.petsGoApp.enums;

public enum AuthenticationMessageEnum {

    USER_NOT_FOUND("user not found");

    private final String message;

    AuthenticationMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
