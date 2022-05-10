package com.jbklenterpirse.petsGoApp.exceptions;


import com.jbklenterpirse.petsGoApp.enums.PetNotExist;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException() {
        super(PetNotExist.PET_NOT_FOUND.getMessage());
    }
}
