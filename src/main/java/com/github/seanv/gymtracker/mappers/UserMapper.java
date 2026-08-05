package com.github.seanv.gymtracker.mappers;


import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.dto.input.SignUpInputDto;
import com.github.seanv.gymtracker.dto.update.UserUpdateDto;
import com.github.seanv.gymtracker.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User fromDto(UserDto dto);

    void updateUser(UserUpdateDto dto, @MappingTarget User user);
}
