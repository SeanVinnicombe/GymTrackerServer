package com.github.seanv.gymtracker.exception.type;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super(
               "User with email: " + email + " already exists!"
        );
    }
}
