package com.github.seanv.gymtracker.exception.type;

public class ProgramDayNotFoundException extends RuntimeException {
    public ProgramDayNotFoundException(Long id) {
        super("Program day with id: " + id + " does not exist!");
    }
}
