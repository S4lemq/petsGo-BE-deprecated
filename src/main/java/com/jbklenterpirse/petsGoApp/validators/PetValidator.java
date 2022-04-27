package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.exceptions.PetIncompleteException;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PetValidator {

    public void validate(PetDto dto){
        if(Objects.isNull(dto.getAge())){
            throw new PetIncompleteException(ValidatorsPetEnum.NO_AGE.getMessage(), "B337A330D8CB40D681405A1061D33F04");
        }
        if(Objects.isNull(dto.getWeight())){
            throw new PetIncompleteException(ValidatorsPetEnum.NO_WEIGHT.getMessage(), "2EF7497A950E45A4968A91DE5FF6884F");
        }
    }
}
