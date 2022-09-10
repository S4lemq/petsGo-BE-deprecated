package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.util.Objects;

class AgeValidator implements Validator{
    @Override
    public ValidatorMessage valid(PetDto dto, ValidatorMessage validatorMessage) {
        if(Objects.isNull(dto.getAge())){
            validatorMessage.setMessage(ValidatorsPetEnum.NO_AGE.getMessage());
            validatorMessage.setCode("B337A330D8CB40D681405A1061D33F04");
        }
        return validatorMessage;
    }
}