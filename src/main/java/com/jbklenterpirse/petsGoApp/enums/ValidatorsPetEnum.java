package com.jbklenterpirse.petsGoApp.enums;

public enum ValidatorsPetEnum {

    NO_AGE("age is required"),
    NO_WEIGHT("weight is required"),
    NO_NAME("name is required"),
    NO_GENDER("gender is required"),
    PET_EXIST("pet already exist");

    private final String message;

    ValidatorsPetEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
