package com.github.seanv.gymtracker.security;

import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.type.NoActiveProgramException;
import com.github.seanv.gymtracker.repositories.ProgramDayExerciseRepository;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import com.github.seanv.gymtracker.repositories.UserRepository;
import com.github.seanv.gymtracker.services.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

//bridge between spring security and DB
@Service
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ProgramRepository programRepository;
    private final ProgramDayExerciseRepository pdeRepository;

    @Autowired
    public SecurityService(UserRepository userRepository, UserService userService, ProgramRepository programRepository, ProgramDayExerciseRepository pdeRepository){
        this.userRepository = userRepository;
        this.userService = userService;
        this.programRepository = programRepository;
        this.pdeRepository = pdeRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(user);
    }

    public UserPrincipal getCurrentUserPrincipal(){
        var holder = SecurityContextHolder.getContext();
        return (UserPrincipal) Objects.requireNonNull(holder.getAuthentication()).getPrincipal();
    }

    public Long getCurrentUserId(){
        UserPrincipal principal = getCurrentUserPrincipal();
        assert principal != null;
        String email = principal.getUsername();
        return userService.getUserIdByEmail(email);
    }

    public Boolean programDayOwnershipCheck(Long pdeId){
        UserPrincipal principal = getCurrentUserPrincipal();
        Program activeUserProgram = programRepository
                .findProgramByUser_IdAndStatus_Active(principal.getId())
                .orElseThrow(NoActiveProgramException::new);

        return pdeRepository.existsByIdAndProgramId(pdeId, activeUserProgram.getId());

    }
}
