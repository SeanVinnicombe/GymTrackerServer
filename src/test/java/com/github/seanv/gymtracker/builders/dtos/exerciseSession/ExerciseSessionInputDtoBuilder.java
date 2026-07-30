package com.github.seanv.gymtracker.builders.dtos.exerciseSession;

import com.github.seanv.gymtracker.builders.dtos.set.SetInputDtoBuilder;
import com.github.seanv.gymtracker.builders.entities.ProgramDayExerciseBuilder;
import com.github.seanv.gymtracker.dto.input.ExerciseSessionInputDto;
import com.github.seanv.gymtracker.dto.input.SetInputDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExerciseSessionInputDtoBuilder {

    private List<SetInputDto> sets = new ArrayList<>();
    private String notes = "Stay";
    private int setCount = 3;

    public static ExerciseSessionInputDtoBuilder aExerciseSessionInputDtoBuilder(){
        return new ExerciseSessionInputDtoBuilder();
    }

    public ExerciseSessionInputDtoBuilder withSets(int count){
        this.setCount = count;
        return this;
    }

    public ExerciseSessionInputDto build(){

        for (int i = 0; i < setCount; i++){
            sets.add(SetInputDtoBuilder.aSetInputDtoBuilder().build());
        }

        var pde = ProgramDayExerciseBuilder.aProgramDayExerciseBuilder().build();
        return new ExerciseSessionInputDto(sets, notes, pde.getId(), LocalDateTime.now());
    }
}
