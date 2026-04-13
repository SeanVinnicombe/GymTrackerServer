package com.github.seanv.gymtracker.exception.type;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("User with E-mail: " + username + " does not exist!");
    }
}
