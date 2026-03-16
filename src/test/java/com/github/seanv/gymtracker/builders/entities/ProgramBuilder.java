package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ProgramBuilder {

    Long id = 1L;
    private String name = "Strength";
    private Integer programLength = 8;
    private User user = UserBuilder.aProgram().build();
    private List<ProgramDay> programDays = new ArrayList<>();
    private int programDaysCount = 5;

    public static ProgramBuilder aProgram(){
        return new ProgramBuilder();
    }

    public ProgramBuilder withProgramDays(int count){
        this.programDaysCount = count;
         return this;
    }

    public ProgramBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public Program build(){
        Program program = new Program();

        program.setId(id);
        program.setName(name);
        program.setUser(user);
        for (int i = 0; i < programDaysCount; i++){
            programDays.add(ProgramDayBuilder.aProgramDay().build());
        }
        program.setProgramDays(programDays);
        program.setProgramLength(programLength);

        return program;
    }

}
