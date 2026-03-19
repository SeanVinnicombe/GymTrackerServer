package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.Set;

public class SetBuilder {

    private Long id = 1L;
    private Integer setOrder = 1;
    private Integer achivedReps = 6;
    private Integer weightDone = 50;
    private int setCount = 3;

    public static SetBuilder aSet(){
        return new SetBuilder();
    }

    public SetBuilder withSets(int count){
        this.setCount = count;
        return this;
    }

    public SetBuilder withId(int id){
        this.id = (long) id;
        return this;
    }

    public Set build(){
        Set set = new Set();

        set.setId(id);
        set.setSetOrder(setOrder);
        set.setAchievedReps(achivedReps);
        set.setWeightDone(weightDone);

        return set;
    }
}
