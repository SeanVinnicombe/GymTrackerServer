package com.github.seanv.gymtracker.exception.type;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with id "+ id + " does not exist!");
    }
}
