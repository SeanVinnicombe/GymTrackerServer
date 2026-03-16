package com.github.seanv.gymtracker.builders.entities;

import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserBuilder {
    private String firstName = "Sean";
    private String lastName = "Vinnicombe";
    private String phoneNumber = "0761239876";
    private String email = "sv@gmail.com";
    private String password = "12345";
    private Set<Program> programs = new HashSet<>();

    public static UserBuilder aProgram(){
        return new UserBuilder();
    }

    public UserBuilder withPrograms(int count){
        for (int i = 0; i<count;i++){
            programs.add(ProgramBuilder.aProgram().build());
        }
        return this;
    }

    public User build(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);
        user.setPrograms(programs);

        return user;
    }
}
