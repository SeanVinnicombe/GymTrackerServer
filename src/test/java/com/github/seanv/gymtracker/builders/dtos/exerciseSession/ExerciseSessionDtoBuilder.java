package com.github.seanv.gymtracker.builders.dtos.exerciseSession;

import com.github.seanv.gymtracker.builders.dtos.set.SetDtoBuilder;
import com.github.seanv.gymtracker.builders.entities.ExerciseSessionBuilder;
import com.github.seanv.gymtracker.builders.entities.SetBuilder;
import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.dto.SetDto;
import com.github.seanv.gymtracker.entities.ExerciseSession;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSessionDtoBuilder {

    private Long id = 1L;
    private String notes = "Stay";
    private List<SetDto> sets = new ArrayList<>();
    private int setCount = 3;

    public static ExerciseSessionDtoBuilder aExerciseSessionDtoBuilder(){
        return new ExerciseSessionDtoBuilder();
    }

    public ExerciseSessionDtoBuilder withSets(int count){
        this.setCount = count;
        return this;
    }

    public ExerciseSessionDto build(){

        ExerciseSessionDto exerciseSession = new ExerciseSessionDto();

        exerciseSession.setId(id);
        exerciseSession.setNotes(notes);

        for (int i =0; i < setCount; i++){
            sets.add(SetDtoBuilder.aSetDto().build());
        }

        exerciseSession.setSets(sets);

        return exerciseSession;
    }
}
