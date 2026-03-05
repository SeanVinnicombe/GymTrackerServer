package com.github.seanv.gymtracker.exception.type;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super("Invalid input provided");
    }
}
