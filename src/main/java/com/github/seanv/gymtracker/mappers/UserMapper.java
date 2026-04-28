package com.github.seanv.gymtracker.mappers;


import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.dto.input.SignUpInputDto;
import com.github.seanv.gymtracker.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User fromDto(UserDto dto);
}
