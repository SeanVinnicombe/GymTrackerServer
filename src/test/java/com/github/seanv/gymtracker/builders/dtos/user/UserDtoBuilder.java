package com.github.seanv.gymtracker.builders.dtos.user;

import com.github.seanv.gymtracker.dto.UserDto;

public class UserDtoBuilder {

    private String firstName = "Seano";
    private String lastName = "Neethling";
    private String email = "sn@gmail.com";
    private String password = "12345";
    private String phoneNumber = "0761239876";

    public static UserDtoBuilder aUserDto(){
        return new UserDtoBuilder();
    }

    public UserDto build(){
        return new UserDto(firstName, lastName, email, password, phoneNumber);
    }
}
