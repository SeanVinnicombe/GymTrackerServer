package com.github.seanv.gymtracker.exception.type;

public class ExerciseExistsException extends RuntimeException {
    public ExerciseExistsException(String exercise) {
        super(exercise + " already exists as exercise");
    }
}
