package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.builders.dtos.programDay.ProgramDayDtoBuilder;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramWeek;

import java.util.ArrayList;
import java.util.List;

public class ProgramWeekBuilder {

    private Long id = 1L;
    private Integer weekNumber = 1;
    private List<ProgramDay> programDays = new ArrayList<>();
    private int programDaysCount = 3;

    public static ProgramWeekBuilder aProgramWeekBuilder(){
        return new ProgramWeekBuilder();
    }

    public ProgramWeekBuilder withProgramDays(int count){
        this.programDaysCount = count;
        return this;
    }

    public ProgramWeek build(){

        ProgramWeek programWeek = new ProgramWeek();

        programWeek.setId(id);
        programWeek.setWeekNumber(weekNumber);

        for (int i = 0; i < programDaysCount; i++){
            programDays.add(ProgramDayBuilder.aProgramDay().build());
        }
        programWeek.setProgramDays(programDays);

        return programWeek;
    }


}
