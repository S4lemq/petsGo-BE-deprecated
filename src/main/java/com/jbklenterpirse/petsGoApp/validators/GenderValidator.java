package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.util.Objects;

public class GenderValidator implements Validator{
    private Validator next = new WeightValidator();

    @Override
    public ValidatorMessage valid(PetDto dto, ValidatorMessage validatorMessage) {
        if(Objects.isNull(dto.getGender())) {
            validatorMessage.setMessage(ValidatorsPetEnum.NO_GENDER.getMessage());
            validatorMessage.setCode("A77A8D4195CD458B85BB4C42255A042C");
        }
        return next.valid(dto, validatorMessage);
    }
}
