package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.ExerciseSession;
import com.github.seanv.gymtracker.entities.Set;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSessionBuilder {

    private Long id = 1L;
    private String notes = "Stay";
    private List<Set> sets = new ArrayList<>();
    private int setCount = 3;

    public static ExerciseSessionBuilder aExerciseSession(){
        return new ExerciseSessionBuilder();
    }

    public ExerciseSessionBuilder withSets(int count){
        this.setCount = count;
        return this;
    }

    public ExerciseSession build(){

        ExerciseSession exerciseSession = new ExerciseSession();

        exerciseSession.setId(id);
        exerciseSession.setNotes(notes);

        for (int i =0; i < setCount; i++){
            sets.add(SetBuilder.aSet().build());
        }exerciseSession.setSets(sets);

        return exerciseSession;
    }

}
