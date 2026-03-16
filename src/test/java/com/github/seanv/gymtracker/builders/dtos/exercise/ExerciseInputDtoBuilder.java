package com.github.seanv.gymtracker.builders.dtos.exercise;

import com.github.seanv.gymtracker.dto.input.ExerciseInputDto;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;

public class ExerciseInputDtoBuilder {

    private String name = "Single arm curls";
    private String muscleGroup = MuscleGroup.BICEPS.getName();

    public static ExerciseInputDtoBuilder aExerciseInputDto(){
        return new ExerciseInputDtoBuilder();
    }

    public ExerciseInputDto build(){
        return new ExerciseInputDto(name, muscleGroup);
    }
}
