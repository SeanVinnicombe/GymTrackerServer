package com.github.seanv.gymtracker.dto;

import com.github.seanv.gymtracker.entities.Exercise;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramDayExerciseDto {

    private Integer exerciseNumber;
    private String exerciseName;
    private Integer targetSets;
    private Integer targetReps;
    private ExerciseSessionDto exerciseSession;
}
