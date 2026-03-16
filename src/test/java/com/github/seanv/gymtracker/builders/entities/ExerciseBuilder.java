package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;

public class ExerciseBuilder {

    private Long id = 1L;
    private String name = "Bench press";
    private MuscleGroup muscleGroup = MuscleGroup.CHEST;

    public static ExerciseBuilder aExercise(){
        return new ExerciseBuilder();
    }

    public ExerciseBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ExerciseBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ExerciseBuilder withMuscleGroup(MuscleGroup muscleGroup){
        this.muscleGroup = muscleGroup;
        return this;
    }

    public Exercise build(){
        Exercise exercise = new Exercise();

        exercise.setId(id);
        exercise.setName(name);
        exercise.setMuscleGroup(muscleGroup);

        return exercise;
    }
}
