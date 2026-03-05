package com.github.seanv.gymtracker.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MuscleGroup {

    CHEST ("Chest"),
    BACK ("Back"),
    SHOULDERS("Shoulders"),
    TRICEPS("Triceps"),
    BICEPS("Biceps"),
    QUADS("Quads"),
    HAMSTRINGS("Hamstrings"),
    CALVES("Calves"),
    GLUTES("Glutes"),
    ;

    private final String name;
}
