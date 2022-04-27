package com.jbklenterpirse.petsGoApp.validators;

import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

public interface Validator {

     ValidatorMessage valid(PetDto dto, ValidatorMessage validatorMessage);

}
