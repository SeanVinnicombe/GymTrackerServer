package com.github.seanv.gymtracker.builders.dtos.programWeek;

import com.github.seanv.gymtracker.builders.dtos.programDay.ProgramDayDtoBuilder;
import com.github.seanv.gymtracker.builders.entities.ProgramWeekBuilder;
import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramWeek;

import java.util.ArrayList;
import java.util.List;

public class ProgramWeekDtoBuilder {

    private Long id = 1L;
    private Long programId = 1L;
    private Integer weekNumber = 1;
    private List<ProgramDayDto> programDays = new ArrayList<>();
    private int programDaysCount = 3;

    public static ProgramWeekDtoBuilder aProgramWeeDtoBuilder(){
        return new ProgramWeekDtoBuilder();
    }

    public ProgramWeekDtoBuilder withProgramDays(int count){
        this.programDaysCount = count;
        return this;
    }

    public ProgramWeekDto build(){

        ProgramWeekDto programWeekDto = new ProgramWeekDto();

        programWeekDto.setId(id);
        programWeekDto.setProgramId(programId);
        programWeekDto.setWeekNumber(weekNumber);

        for (int i = 0; i < programDaysCount; i++){
            programDays.add(ProgramDayDtoBuilder.aProgramDayDto().build());
        }
        programWeekDto.setProgramDays(programDays);

        return programWeekDto;
    }
}
