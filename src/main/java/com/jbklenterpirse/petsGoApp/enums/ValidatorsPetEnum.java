package com.jbklenterpirse.petsGoApp.enums;

public enum ValidatorsPetEnum {

    NO_AGE("age is null"),
    NO_WEIGHT("weight is null"),
    NO_NAME("name is null");

    private final String message;

    ValidatorsPetEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
