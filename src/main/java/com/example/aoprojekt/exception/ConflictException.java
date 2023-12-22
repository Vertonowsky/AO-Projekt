package com.example.aoprojekt.exception;

public class ConflictException extends Exception {


    public ConflictException() {
        super("Wystąpił konflikt danych!");
    }

    public ConflictException(String message) {
        super(message);
    }

}
