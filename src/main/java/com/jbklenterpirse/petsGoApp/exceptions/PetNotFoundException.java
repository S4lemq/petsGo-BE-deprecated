package com.jbklenterpirse.petsGoApp.exceptions;

import com.jbklenterpirse.petsGoApp.enums.PetExceptionEnum;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException() {
        super(PetExceptionEnum.PET_NOT_FOUND.getMessage());
    }
}
