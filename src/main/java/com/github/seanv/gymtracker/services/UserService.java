package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import com.github.seanv.gymtracker.mappers.UserMapper;
import com.github.seanv.gymtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService (UserRepository userRepository, UserMapper mapper){

        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDto getUser(Long id){
        return mapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public User getUserEntity(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
