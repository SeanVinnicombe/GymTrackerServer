package com.github.seanv.gymtracker.security;

import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.type.UsernameNotFoundException;
import com.github.seanv.gymtracker.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//bridge between spring security and DB
@Service
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(user);
    }
}
