package com.github.seanv.gymtracker.builders.dtos.programDayExercise;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;

public class ProgramDayExerciseDtoBuilder {

    private Integer exerciseNumber = 1;
    private String exerciseName = "Bench press";
    private Integer targetSets = 3;
    private Integer targerReps = 6;

    public static ProgramDayExerciseDtoBuilder aProgramDayExerciseDtoBuilder(){
        return new ProgramDayExerciseDtoBuilder();
    }

    public ProgramDayExerciseDto build(){
        ProgramDayExerciseDto dto = new ProgramDayExerciseDto();

        dto.setExerciseName(exerciseName);
        dto.setExerciseNumber(exerciseNumber);
        dto.setTargetReps(targerReps);
        dto.setTargetSets(targetSets);

        return dto;
    }
}
