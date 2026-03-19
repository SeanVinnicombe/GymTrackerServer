package com.github.seanv.gymtracker.builders.dtos.programWeek;

import com.github.seanv.gymtracker.builders.dtos.programDay.ProgramDaysInputDtoBuilder;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramWeekInputDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramWeekInputDtoBuilder {

    private Integer weekNumber = 1;
    private List<ProgramDayInputDto> programDays = new ArrayList<>();
    private int programDaysCount = 3;

    public static ProgramWeekInputDtoBuilder aProgramWeekInputDtoBuilder(){
        return new ProgramWeekInputDtoBuilder();
    }

    public ProgramWeekInputDtoBuilder withProgramDays(int count){
        this.programDaysCount = count;
        return this;
    }

    public ProgramWeekInputDto build(){

        for (int i = 0; i < programDaysCount ; i++){
            programDays.add(ProgramDaysInputDtoBuilder.aProgramDaysInputDto().build());
        }

        return new ProgramWeekInputDto(weekNumber,programDays);
    }
}
