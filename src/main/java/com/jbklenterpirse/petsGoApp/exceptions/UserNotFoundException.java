package com.jbklenterpirse.petsGoApp.exceptions;

import com.jbklenterpirse.petsGoApp.enums.AuthenticationMessageEnum;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super(AuthenticationMessageEnum.USER_NOT_FOUND.getMessage());
    }
}
