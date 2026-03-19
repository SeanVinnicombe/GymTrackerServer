package com.github.seanv.gymtracker.builders.dtos.set;

import com.github.seanv.gymtracker.dto.SetDto;
import com.github.seanv.gymtracker.dto.input.SetInputDto;

public class SetInputDtoBuilder {

    private Integer setOrder = 1;
    private Integer achivedReps = 6;
    private Integer weightDone = 50;

    public static SetInputDtoBuilder aSetInputDtoBuilder(){
        return new SetInputDtoBuilder();
    }

    public SetInputDto build(){

        return new SetInputDto(setOrder, achivedReps, weightDone);
    }
}
