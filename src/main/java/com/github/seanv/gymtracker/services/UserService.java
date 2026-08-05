package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.dto.update.UserUpdateDto;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import com.github.seanv.gymtracker.mappers.UserMapper;
import com.github.seanv.gymtracker.repositories.UserRepository;
import com.github.seanv.gymtracker.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final SecurityService securityService;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper mapper, SecurityService securityService, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.mapper = mapper;
        this.securityService = securityService;
        this.userMapper = userMapper;
    }

    public UserDto getUser(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public User getUserEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Long getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
        return user.getId();
    }

    public UserDto updateUser(UserUpdateDto inputDto){

        Long userId = securityService.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        userMapper.updateUser(inputDto, user);

        User updatedUser = userRepository.save(user);

        return mapper.toDto(updatedUser);
    }
}
