package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;
import com.github.seanv.gymtracker.dto.update.ProgramUpdateDto;
import com.github.seanv.gymtracker.entities.*;
import com.github.seanv.gymtracker.entities.enums.ProgramStatus;
import com.github.seanv.gymtracker.exception.type.ProgramNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseMapper;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import com.github.seanv.gymtracker.security.SecurityService;
import com.github.seanv.gymtracker.services.sync.ProgramSynchronizer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final ProgramDayService programDayService;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final SecurityService securityService;
    private final ProgramSynchronizer programSynchronizer;
    private final ProgramWeekService programWeekService;

    @Autowired
    public ProgramService(ProgramRepository programRepository,
                           ProgramMapper mapper,
                           ProgramDayService programDayService,
                           UserService userService,
                           ExerciseService exerciseService,
                           ExerciseMapper exerciseMapper,
                          SecurityService securityService,
                          ProgramSynchronizer programSynchronizer,

                          ProgramWeekService programWeekService){
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programDayService = programDayService;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.securityService = securityService;
        this.programSynchronizer = programSynchronizer;
        this.programWeekService = programWeekService;
    }

    /**
     * When retrieving Program, db calls were split up otherwise it would have resulted in nested collections
     * having to be retried in one call, which is overkill that kills performance and could lead to
     * Cartesian Product Explosion
     * **/
    @Transactional
    public ProgramDto getProgram(Long id){
        Program program = programRepository.findByIdWithProgramWeeks(id).orElseThrow(() -> new ProgramNotFoundException(id));
        return mapper.toDto(program);
    }

    /**
     * (() -> new ProgramNotFoundException(id)) lambda is used as orElseThrow expects Supplier.get() which expects no args,
     * so we have to wrap in lambda as it needs an arg for exception.
     *
     * if exception didn't take in args, we would use RuntimeException::new
     *
     * NB! - we can't use new RuntimeException() as it would create an exception even if the value exists, that's why we use
     * ()-> new RuntimeException to only create an exception when it's needed
     * */


    /**
     * So we use @Transactional here, because we need session to stay open, even whilst mapping happens because without
     *  it, we would be hit with LazyLoadingException as we are trying to load a collection that is not yet loaded(programWeeks)
     *
     *  So using @Transactional wraps the entire method into a single transaction - keeping the session open until the method
     *  returns. Now the mapper can access Lazy collections as the session is still open and doesn't close after db call
     *  and before mapping happens
     */

    @Transactional
    public List<ProgramDto> getAllPrograms(){
        Long userId = securityService.getCurrentUserId();
        List<Program> programs =  programRepository.getAllProgramsByUser_Id(userId);
        return programs.stream().map(mapper::toDto).toList();
    }

    /**
     * Instead of using passed param to get all programs for a specific user, we make use of the SecurityContext to
     * extract the necessary user info for us to make the call. This is better than having the user id used in the url
     * as a user could change the id and view other user programs. The issue that this method solves is
     * Insecure Direct Object Reference(IDOR) - one of the most common API vulnerabilities. You would need to check id
     * in url against the one in context, but the above approach of not using url and just extracting the id from
     * SecurityContext is better
     */

    public List<ProgramDayDto> getProgramDays(Long programId){
        return programDayService.getProgramDaysByProgramId(programId);
    }

    @Transactional
    public ProgramDto createProgram(ProgramInputDto inputDto){

        Program program = new Program();
        program.setUser(userService.getUserEntity(1L));
        List<ProgramWeek> programWeeks = new ArrayList<>();

        program.setName(inputDto.name());
        program.setStatus(ProgramStatus.INACTIVE);
        program.setProgramLength(inputDto.numberOfWeeks());
        program.setCreatedAt(LocalDateTime.now());
        program.setUpdatedAt(LocalDateTime.now());
        AtomicInteger weekCount = new AtomicInteger(0);


        for(int i = 0; i < inputDto.numberOfWeeks(); i++){
            ProgramWeek programWeek = new ProgramWeek();
            programWeek.setProgram(program);
            programWeek.setWeekNumber(weekCount.getAndIncrement() + 1);

            List<ProgramDay> pdList = programDayService.buildProgramDays(inputDto.programDays(), programWeek);
            programWeek.setProgramDays(pdList);
            programWeek.setCreatedAt(LocalDateTime.now());
            programWeek.setUpdatedAt(LocalDateTime.now());
            programWeeks.add(programWeek);
        }
        program.setProgramWeeks(programWeeks);

        var a = programRepository.save(program);

        return mapper.toDto(a);
    }

    public ProgramDto updateProgram(ProgramUpdateDto inputDto){
        Program program = programRepository.findById(inputDto.id()).orElseThrow(() -> new ProgramNotFoundException(inputDto.id()));
        programSynchronizer.synchronize(program, inputDto);
        return mapper.toDto(program);
    }



    public Boolean activateProgram(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException(programId));
        program.setStatus(ProgramStatus.ACTIVE);
        var a = programRepository.save(program);

        return a.getStatus() == ProgramStatus.ACTIVE;
    }
}