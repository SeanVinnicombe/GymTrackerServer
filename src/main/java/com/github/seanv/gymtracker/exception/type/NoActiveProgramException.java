package com.github.seanv.gymtracker.exception.type;

public class NoActiveProgramException extends RuntimeException {
    public NoActiveProgramException() {
        super("There is currently no active program");
    }
}
