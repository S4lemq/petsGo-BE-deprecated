package com.jbklenterpirse.petsGoApp.exceptions;

import com.jbklenterpirse.petsGoApp.enums.AuthenticationMessageEnum;

public class UserUnauthorizedException extends RuntimeException{

    public UserUnauthorizedException() {
        super(AuthenticationMessageEnum.USER_UNAUTHORIZED.getMessage());
    }
    //TODO jakoś wyrzucać jak nie ma uprawnień
}
