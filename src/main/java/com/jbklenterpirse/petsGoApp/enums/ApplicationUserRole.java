package com.jbklenterpirse.petsGoApp.enums;

public enum ApplicationUserRole {
    ADMIN(ApplicationUserRole.APP_ADMIN),
    PET_SITTER(ApplicationUserRole.APP_PET_SITTER),
    PET_SEARCHER(ApplicationUserRole.APP_PET_SEARCHER);

    public static final String APP_ADMIN = "ADMIN";
    public static final String APP_PET_SITTER = "PET_SITTER";
    public static final String APP_PET_SEARCHER = "PET_SEARCHER";

    ApplicationUserRole(String myAdmin) {}
}
