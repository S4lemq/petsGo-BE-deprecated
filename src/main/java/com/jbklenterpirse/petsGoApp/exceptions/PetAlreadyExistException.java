package com.jbklenterpirse.petsGoApp.exceptions;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;

public class PetAlreadyExistException extends RuntimeException{

    public PetAlreadyExistException() {
        super(ValidatorsPetEnum.PET_EXIST.getMessage());
    }
}
