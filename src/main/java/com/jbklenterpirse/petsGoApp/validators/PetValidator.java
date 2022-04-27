package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.exceptions.PetIncompleteException;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PetValidator {

    private Validator validator = new WeightValidator();

    public void validate(PetDto dto){
        var validatorMessage = validator.valid(dto, new ValidatorMessage());

        if(validatorMessage.getMessage().isEmpty()){
            return;
        }

        throw new PetIncompleteException(validatorMessage.getMessage(),validatorMessage.getCode());
    }
}
