package com.github.seanv.gymtracker.exception.type;

public class ExerciseNotFoundException extends RuntimeException {

    public ExerciseNotFoundException(Long id) {
        super("Exercise with id " + id + " does not exist");
    }
}
