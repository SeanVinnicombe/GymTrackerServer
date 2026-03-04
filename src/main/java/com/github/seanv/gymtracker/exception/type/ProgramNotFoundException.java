package com.github.seanv.gymtracker.exception.type;

public class ProgramNotFoundException extends RuntimeException{

    public ProgramNotFoundException(Long id){
        super("Program with id "+ id + " does not exist");
    }
}

/** When creating custom exception, it is industry standard to extend RuntimeException
 * and to always pass meaningful message
 */