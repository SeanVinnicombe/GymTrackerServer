package com.github.seanv.gymtracker.builders;

import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;

public class ProgramDayExerciseBuilder {

    private Integer targetSets = 3;
    private Integer targetReps = 8;
    private ProgramDay programDay = ProgramDayBuilder.aProgramDay().build();
    private Exercise exercise = ExerciseBuilder.aExercise().build();

    public static ProgramDayExerciseBuilder aProgramDayExerciseBuilder(){
        return new ProgramDayExerciseBuilder();
    }

    public ProgramDayExercise build(){

        ProgramDayExercise programDayExercise = new ProgramDayExercise();

        programDayExercise.setTargetSets(targetSets);
        programDayExercise.setTargetReps(targetReps);
        programDayExercise.setProgramDay(programDay);
        programDayExercise.setExercise(exercise);

        return programDayExercise;
    }
}
