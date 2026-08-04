package com.github.seanv.gymtracker.dto;

import com.github.seanv.gymtracker.entities.Exercise;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProgramDayExerciseDto {

    private Integer exerciseOrder;
    private String exerciseName;
    private Integer targetSets;
    private Integer targetReps;
}
