package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.AuthResponse;
import com.github.seanv.gymtracker.dto.input.LoginInputDto;
import com.github.seanv.gymtracker.dto.input.SignUpInputDto;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.entities.enums.Role;
import com.github.seanv.gymtracker.exception.type.UserAlreadyExistsException;
import com.github.seanv.gymtracker.repositories.UserRepository;
import com.github.seanv.gymtracker.security.JwtService;
import com.github.seanv.gymtracker.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecurityService securityService;

    @Autowired
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       SecurityService securityService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.securityService = securityService;
    }

    public AuthResponse saveUser(SignUpInputDto inputDto){

        if (userRepository.existsByEmail(inputDto.email())){
            throw new UserAlreadyExistsException(inputDto.email());
        }
        User user  = convertInputToEntity(inputDto);
        User savedUser = userRepository.save(user);

        UserDetails userDetails = securityService.loadUserByUsername(savedUser.getEmail());

        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    public AuthResponse authenticate(LoginInputDto inputDto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(inputDto.email(), inputDto.password());
        authenticationManager.authenticate(authToken);

        UserDetails userDetails = securityService.loadUserByUsername(inputDto.email());
        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    /**
     * Make mapping explicit from general UserMapper as it's only used once, and we have unique functionality that Mapstruct
     * can't directly apply.
     *
     * Defualt must always give users the User role when a creation process occurs, otherwise null will occur later
     * when getAuthorities call happens later
     *
     * We also use explicit mapping as using Mapstruct would set the password as is to the entity field, and then we would have
     * to overwrite it with the encoded value after that, so no point changing the field twice in quick succession when you
     * can just do it once
     */
    private User convertInputToEntity(SignUpInputDto input){
        User user = new User();

        user.setFirstName(input.firstName());
        user.setLastName(input.lastName());
        user.setPhoneNumber(input.phoneNumber());
        user.setEmail(input.email());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(Role.USER);

        return user;
    }
}
