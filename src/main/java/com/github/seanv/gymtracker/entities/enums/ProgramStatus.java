package com.github.seanv.gymtracker.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProgramStatus {

    COMPLETED("Completed"),
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String name;
}
