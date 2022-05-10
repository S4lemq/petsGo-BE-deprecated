package com.jbklenterpirse.petsGoApp.enums;

public enum PetNotExist {
    PET_NOT_FOUND("pet not found");

    private final String message;

    PetNotExist(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
