package com.jbklenterpirse.petsGoApp.exceptions;

import com.jbklenterpirse.petsGoApp.enums.AuthenticationMessageEnum;

public class UserAlreadyExistInDatabase extends RuntimeException{

    public UserAlreadyExistInDatabase() {
        super(AuthenticationMessageEnum.USER_ALREADY_EXIST.getMessage());
    }
}
