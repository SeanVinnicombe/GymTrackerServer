package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;

import java.util.ArrayList;
import java.util.List;

public class ProgramDayBuilder {

    private String muscleGroup = "CHEST";
    private List<ProgramDayExercise> programDayExercises = new ArrayList<>();
    private int programDayExercisesCount = 6;


    public static ProgramDayBuilder aProgramDay() {
        return new ProgramDayBuilder();
    }

    public ProgramDayBuilder withProgramDayExercises(int count){
        this.programDayExercisesCount = count;
        return this;
    }

    public ProgramDay build(){
        ProgramDay programDay = new ProgramDay();

        programDay.setMuscleGroup(muscleGroup);
        for (int i = 0; i < programDayExercisesCount ;i++){
            programDayExercises.add(ProgramDayExerciseBuilder.aProgramDayExerciseBuilder().build());
        }
        programDay.setProgramDayExercises(programDayExercises);

        return programDay;
    }


}
