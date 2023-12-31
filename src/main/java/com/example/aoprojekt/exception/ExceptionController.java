package com.example.aoprojekt.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ErrorDto handleConflictException(ConflictException ex) {
        return new ErrorDto(ex.getMessage());
    }

}
