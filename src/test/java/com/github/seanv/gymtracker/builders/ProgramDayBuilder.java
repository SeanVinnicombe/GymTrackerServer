package com.github.seanv.gymtracker.builders;

import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgramDayBuilder {

    private String muscleGroup = "CHEST";
    private Program program = ProgramBuilder.aProgram().build();
    private List<ProgramDayExercise> programDayExercises = new ArrayList<>();


    public static ProgramDayBuilder aProgramDay() {
        return new ProgramDayBuilder();
    }

    public ProgramDayBuilder withProgramDayExercises(int count){

        for (int i = 0; i < count ;i++){
            programDayExercises.add(ProgramDayExerciseBuilder.aProgramDayExerciseBuilder().build());
        }

        return this;
    }

    public ProgramDay build(){
        ProgramDay programDay = new ProgramDay();

        programDay.setMuscleGroup(muscleGroup);
        programDay.setProgram(program);
        programDay.setProgramDayExercises(programDayExercises);

        return programDay;
    }


}
