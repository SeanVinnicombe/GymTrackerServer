package com.github.seanv.gymtracker.builders;

import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ProgramBuilder {

    private String name = "Strength";
    private Integer programLength = 8;
    private User user = UserBuilder.aProgram().build();
    private List<ProgramDay> programDays = new ArrayList<>();

    public static ProgramBuilder aProgram(){
        return new ProgramBuilder();
    }

    public ProgramBuilder withProgramDays(int count){
         for (int i = 0; i < count; i++){
             programDays.add(ProgramDayBuilder.aProgramDay().build());
         }

         return this;
    }

    public Program build(){
        Program program = new Program();

        program.setName(name);
        program.setUser(user);
        program.setProgramDays(programDays);
        program.setProgramLength(programLength);

        return program;
    }

}
