package com.jbklenterpirse.petsGoApp.enums;

public enum PetExceptionEnum {
    PET_NOT_FOUND("pet not found");

    private final String message;

    PetExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
