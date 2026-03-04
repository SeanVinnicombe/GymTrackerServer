package com.github.seanv.gymtracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDto {

    private Long id;
    private String exerciseName;
    private String muscleGroup;
}
