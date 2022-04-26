package com.jbklenterpirse.petsGoApp.controllers.handlers;

import com.jbklenterpirse.petsGoApp.controllers.handlers.dtos.ErrorMessage;
import com.jbklenterpirse.petsGoApp.exceptions.PetIncompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PetControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage petIncompleteExceptionHandler(PetIncompleteException exception){
         return ErrorMessage.ErrorMessageBuilder.anErrorMessage()
                 .withErrorCode(exception.getErrorCode())
                 .withErrorDescription(exception.getMessage())
                 .build();
    }

}
