package com.github.seanv.gymtracker.exception.type;

public class ExerciseSessionNotFoundException extends RuntimeException {
    public ExerciseSessionNotFoundException(Long id) {
        super("Exercise session by id:" + id + " does not exists!");
    }
}
