package com.github.seanv.gymtracker.builders.dtos.set;

import com.github.seanv.gymtracker.builders.entities.SetBuilder;
import com.github.seanv.gymtracker.dto.SetDto;
import com.github.seanv.gymtracker.entities.Set;

public class SetDtoBuilder {


    private Long id = 1L;
    private Integer setOrder = 1;
    private Integer achivedReps = 6;
    private Integer weightDone = 50;
    private int setCount = 3;

    public static SetDtoBuilder aSetDto(){
        return new SetDtoBuilder();
    }

    public SetDtoBuilder withSets(int count){
        this.setCount = count;
        return this;
    }

    public SetDtoBuilder withId(int id){
        this.id = (long) id;
        return this;
    }

    public SetDto build(){
        SetDto set = new SetDto();

        set.setId(id);
        set.setSetOrder(setOrder);
        set.setAchievedReps(achivedReps);
        set.setWeightDone(weightDone);

        return set;
    }
}
