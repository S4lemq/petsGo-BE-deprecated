package com.jbklenterpirse.petsGoApp.enums;

public enum ApplicationUserPermission {
    PET_READ("pet:read"),
    PET_WRITE("pet:write"),
    PET_DELETE("pet:delete"),
    PET_UPDATE("pet:update");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
