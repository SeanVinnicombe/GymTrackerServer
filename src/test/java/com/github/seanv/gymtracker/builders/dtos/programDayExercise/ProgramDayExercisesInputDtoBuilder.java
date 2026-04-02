package com.github.seanv.gymtracker.builders.dtos.programDayExercise;

import com.github.seanv.gymtracker.builders.dtos.exerciseSession.ExerciseSessionInputDtoBuilder;
import com.github.seanv.gymtracker.dto.input.ExerciseSessionInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramDayExercisesInputDtoBuilder {

    private Long exerciseId = 1L;
    private int exerciseNumber = 1;
    private Integer targetSets = 3;
    private Integer targerReps = 6;
    private int sessionsCount = 3;


    public static ProgramDayExercisesInputDtoBuilder aProgramDayExercisesInputDto(){
        return new ProgramDayExercisesInputDtoBuilder();
    }

    public ProgramDayExercisesInputDtoBuilder withExerciseSessions(int count){
        this.sessionsCount = count;
        return this;
    }

    public ProgramDayExerciseInputDto build(){
        List<ExerciseSessionInputDto> sessions = new ArrayList<>();

        for (int i = 0; i < sessionsCount; i++){
            sessions.add(ExerciseSessionInputDtoBuilder.aExerciseSessionInputDtoBuilder().build());
        }
        return new ProgramDayExerciseInputDto(exerciseId,exerciseNumber, targetSets,targerReps, sessions);
    }
}
