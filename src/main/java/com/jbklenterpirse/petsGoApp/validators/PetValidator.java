package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.exceptions.PetIncompleteException;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.util.Objects;

public class PetValidator {

    public void validate(PetDto dto){
        if(Objects.isNull(dto.getAge())){
            throw new PetIncompleteException(ValidatorsPetEnum.NO_AGE.getMessage());
        }
    }

}
