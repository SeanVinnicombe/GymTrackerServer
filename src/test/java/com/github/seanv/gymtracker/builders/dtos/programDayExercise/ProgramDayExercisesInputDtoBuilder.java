package com.github.seanv.gymtracker.builders.dtos.programDayExercise;

import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;

public class ProgramDayExercisesInputDtoBuilder {

    private Long exerciseId = 1L;
    private Integer targetSets = 3;
    private Integer targerReps = 6;

    public static ProgramDayExercisesInputDtoBuilder aProgramDayExercisesInputDto(){
        return new ProgramDayExercisesInputDtoBuilder();
    }

    public ProgramDayExerciseInputDto build(){
        return new ProgramDayExerciseInputDto(exerciseId,targetSets,targerReps);
    }
}
