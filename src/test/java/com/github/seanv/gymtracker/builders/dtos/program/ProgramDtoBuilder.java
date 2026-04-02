package com.github.seanv.gymtracker.builders.dtos.program;

import com.github.seanv.gymtracker.builders.dtos.programDay.ProgramDayDtoBuilder;
import com.github.seanv.gymtracker.builders.dtos.programWeek.ProgramWeekDtoBuilder;
import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.ProgramWeekDto;

import java.util.ArrayList;
import java.util.List;

public class ProgramDtoBuilder {

    private Long id = 1L;
    private Integer programLength = 8;
    private List<ProgramWeekDto> programWeeks = new ArrayList<>();
    private int programDaysCount = 5;

    public static ProgramDtoBuilder aProgramDto(){
        return new ProgramDtoBuilder();
    }

    public ProgramDtoBuilder withProgramDayDtos(int count){
        this.programDaysCount = count;
        return this;
    }

    public ProgramDtoBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ProgramDto build(){

        ProgramDto dto = new ProgramDto();

        dto.setId(id);
        dto.setProgramLength(programLength);

        for (int i = 0; i < programDaysCount ; i++){
            programWeeks.add(ProgramWeekDtoBuilder.aProgramWeeDtoBuilder().build());
        }
        dto.setProgramWeeks(programWeeks);

        return dto;
    }
}
