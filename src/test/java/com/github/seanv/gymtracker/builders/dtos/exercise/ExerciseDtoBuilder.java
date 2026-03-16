package com.github.seanv.gymtracker.builders.dtos.exercise;

import com.github.seanv.gymtracker.dto.ExerciseDto;

public class ExerciseDtoBuilder {

    private Long id = 1L;
    private String name = "Bench";
    private String muscleGroup = "Chest";

    public static ExerciseDtoBuilder aExerciseDto(){
        return new ExerciseDtoBuilder();
    }

    public ExerciseDtoBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ExerciseDtoBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ExerciseDtoBuilder withMuscleGroup(String muscleGroup){
        this.muscleGroup = muscleGroup;
        return this;
    }

    public ExerciseDto build(){
        ExerciseDto dto = new ExerciseDto();

        dto.setId(id);
        dto.setName(name);
        dto.setMuscleGroup(muscleGroup);

        return dto;
    }

}
