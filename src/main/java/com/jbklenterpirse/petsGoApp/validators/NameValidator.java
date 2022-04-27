package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.util.Objects;

public class NameValidator implements Validator{

    private Validator next = new AgeValidator();

    @Override
    public ValidatorMessage valid(PetDto dto, ValidatorMessage validatorMessage) {
        if(Objects.isNull(dto.getName())) {
            validatorMessage.setMessage(ValidatorsPetEnum.NO_NAME.getMessage());
            validatorMessage.setCode("1447C738185A47F599806FA749253C54");
        }
        return next.valid(dto, validatorMessage);
    }
}
