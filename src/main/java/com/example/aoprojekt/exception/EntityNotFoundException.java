package com.example.aoprojekt.exception;

public class EntityNotFoundException extends Exception {


    public EntityNotFoundException() {
        super("Wystąpił konflikt danych!");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}
