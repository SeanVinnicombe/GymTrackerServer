package com.github.seanv.gymtracker.exception.type;

public class ProgramDayExerciseNotFoundException extends RuntimeException {
    public ProgramDayExerciseNotFoundException(Long id) {
        super("There is no Exercise for Program day with id: " + id);
    }
}
