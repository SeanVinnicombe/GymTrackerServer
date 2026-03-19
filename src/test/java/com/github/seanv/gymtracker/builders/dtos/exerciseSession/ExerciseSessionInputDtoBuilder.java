package com.github.seanv.gymtracker.builders.dtos.exerciseSession;

import com.github.seanv.gymtracker.builders.dtos.set.SetInputDtoBuilder;
import com.github.seanv.gymtracker.dto.input.ExerciseSessionInputDto;
import com.github.seanv.gymtracker.dto.input.SetInputDto;

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

        return new ExerciseSessionInputDto(sets, notes);
    }
}
