package com.github.seanv.gymtracker.unit.services;

import com.github.seanv.gymtracker.builders.dtos.user.UserDtoBuilder;
import com.github.seanv.gymtracker.builders.dtos.user.UserUpdateDtoBuilder;
import com.github.seanv.gymtracker.builders.entities.UserBuilder;
import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.dto.update.UserUpdateDto;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import com.github.seanv.gymtracker.mappers.UserMapper;
import com.github.seanv.gymtracker.repositories.UserRepository;
import com.github.seanv.gymtracker.security.SecurityService;
import com.github.seanv.gymtracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private UserService userService;


    @Test
    void when_updating_user_then_return_updated_user() {

        User user = UserBuilder.aUser().build();

        UserUpdateDto inputDto = UserUpdateDtoBuilder.aUserUpdateDto().build();

        User updated = UserBuilder.aUser().build();
        updated.setFirstName(inputDto.firstName());
        updated.setLastName(inputDto.lastName());
        updated.setEmail(inputDto.email());

        UserDto ex = UserDtoBuilder.aUserDto().build();


        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.toDto(updated)).thenReturn(ex);
        when(securityService.getCurrentUserId()).thenReturn(user.getId());
        when(userRepository.save(updated)).thenReturn(updated);

        UserDto result = userService.updateUser(inputDto);

        assertEquals("Seano", result.getFirstName());
        assertEquals("Neethling", result.getLastName());
        assertEquals("sn@gmail.com", result.getEmail());
        assertEquals("12345", result.getPassword());


    }

}
