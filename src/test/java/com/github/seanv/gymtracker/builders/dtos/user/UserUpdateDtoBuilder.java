package com.github.seanv.gymtracker.builders.dtos.user;

import com.github.seanv.gymtracker.dto.update.UserUpdateDto;

public class UserUpdateDtoBuilder {

    private String firstName = "Seano";
    private String lastName = "Neethling";
    private String email = "sn@gmail.com";


    public static UserUpdateDtoBuilder aUserUpdateDto(){
        return new UserUpdateDtoBuilder();
    }

    public UserUpdateDto build(){
        return new UserUpdateDto(firstName, lastName, email);
    }

}
