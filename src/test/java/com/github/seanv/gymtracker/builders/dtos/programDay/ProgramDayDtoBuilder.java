package com.github.seanv.gymtracker.builders.dtos.programDay;

import com.github.seanv.gymtracker.builders.dtos.programDayExercise.ProgramDayExerciseDtoBuilder;
import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramDayDtoBuilder {

    private Long id = 1L;
    private String muscleGroup = "CHEST";
    private List<ProgramDayExerciseDto> programDayExercises = new ArrayList<>();
    private int programDayExercisesCount = 6;

    public static ProgramDayDtoBuilder aProgramDayDto(){
        return new ProgramDayDtoBuilder();
    }

    public ProgramDayDtoBuilder withProgramDayExercises(int count){
        this.programDayExercisesCount = count;
        return this;
    }

    public ProgramDayDto build(){

        ProgramDayDto dto = new ProgramDayDto();

        dto.setId(id);
        dto.setMuscleGroup(muscleGroup);

        for (int i=0; i < programDayExercisesCount; i++){
            programDayExercises.add(ProgramDayExerciseDtoBuilder.aProgramDayExerciseDtoBuilder().build());
        }

        dto.setProgramDayExercises(programDayExercises);

        return dto;

    }
}
