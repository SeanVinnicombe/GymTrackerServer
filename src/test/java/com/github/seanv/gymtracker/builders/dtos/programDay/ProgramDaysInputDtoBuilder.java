package com.github.seanv.gymtracker.builders.dtos.programDay;

import com.github.seanv.gymtracker.builders.dtos.programDayExercise.ProgramDayExercisesInputDtoBuilder;
import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramDaysInputDtoBuilder {

    private String muscleGroup = "CHEST";
    private List<ProgramDayExerciseInputDto> programDayExercises = new ArrayList<>();
    private int programDayExercisesCount = 6;

    public static ProgramDaysInputDtoBuilder aProgramDaysInputDto(){
        return new ProgramDaysInputDtoBuilder();
    }

    public ProgramDaysInputDtoBuilder withProgramDayExercises(int count){
        this.programDayExercisesCount = count;
        return this;
    }

    public ProgramDayInputDto build(){

        for (int i = 0; i < programDayExercisesCount; i++){
            programDayExercises.add(ProgramDayExercisesInputDtoBuilder.aProgramDayExercisesInputDto().build());
        }

        return new ProgramDayInputDto(muscleGroup,programDayExercises);
    }
}
