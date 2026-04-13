package com.github.seanv.gymtracker.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("User"),
    ADMIN("Admin");


    private final String name;

}
