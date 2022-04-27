package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.util.Objects;

public class WeightValidator implements Validator{

    private Validator next = new NameValidator();

    @Override
    public ValidatorMessage valid(PetDto dto, ValidatorMessage validatorMessage) {
        if(Objects.isNull(dto.getWeight())) {
            validatorMessage.setMessage(ValidatorsPetEnum.NO_WEIGHT.getMessage());
            validatorMessage.setCode("2EF7497A950E45A4968A91DE5FF6884F");
        }
        return next.valid(dto, validatorMessage);
    }
}
