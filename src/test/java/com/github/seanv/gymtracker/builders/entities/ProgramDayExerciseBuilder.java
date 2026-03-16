package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;

public class ProgramDayExerciseBuilder {

    private Integer targetSets = 3;
    private Integer targetReps = 8;
    private Exercise exercise = ExerciseBuilder.aExercise().build();

    public static ProgramDayExerciseBuilder aProgramDayExerciseBuilder(){
        return new ProgramDayExerciseBuilder();
    }

    public ProgramDayExercise build(){

        ProgramDayExercise programDayExercise = new ProgramDayExercise();

        programDayExercise.setTargetSets(targetSets);
        programDayExercise.setTargetReps(targetReps);
        programDayExercise.setExercise(exercise);

        return programDayExercise;
    }
}
