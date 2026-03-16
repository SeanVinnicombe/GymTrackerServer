package com.github.seanv.gymtracker.builders.dtos.program;

import com.github.seanv.gymtracker.builders.dtos.programDay.ProgramDaysInputDtoBuilder;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramInputDtoBuilder {

    private String name = "Strength";
    private Integer numberOfWeeks = 8;
    private List<ProgramDayInputDto> programDays = new ArrayList<>();
    private int programDaysCount = 5;


    public static ProgramInputDtoBuilder aProgramDayInputDto(){
        return new ProgramInputDtoBuilder();
    }

    public ProgramInputDtoBuilder withProgramDays(int count){
        this.programDaysCount = count;
        return this;
    }

    public ProgramInputDto build(){

        for (int i = 0 ; i < programDaysCount; i++){
            programDays.add(ProgramDaysInputDtoBuilder.aProgramDaysInputDto().build());
        }

        return new ProgramInputDto(name,numberOfWeeks,programDays);
    }

}
