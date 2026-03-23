package com.github.seanv.gymtracker.exception.type;

public class ProgramWeekNotFoundException extends RuntimeException {
    public ProgramWeekNotFoundException(Long id) {
        super(
                "Program week by id " + id + " does not exist!"
        );
    }
}
