package com.github.seanv.gymtracker.builders;

import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;

public class ExerciseBuilder {

    private String name = "Bench press";
    private MuscleGroup muscleGroup = MuscleGroup.CHEST;

    public static ExerciseBuilder aExercise(){
        return new ExerciseBuilder();
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

        exercise.setName(name);
        exercise.setMuscleGroup(muscleGroup);

        return exercise;
    }
}
