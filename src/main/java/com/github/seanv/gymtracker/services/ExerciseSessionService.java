package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.input.ExerciseSessionInputDto;
import com.github.seanv.gymtracker.entities.ExerciseSession;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.exception.type.ExerciseSessionNotFoundException;
import com.github.seanv.gymtracker.exception.type.ProgramDayExerciseNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseSessionMapper;
import com.github.seanv.gymtracker.repositories.ExerciseSessionRepository;
import com.github.seanv.gymtracker.repositories.SetRepository;
import com.github.seanv.gymtracker.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExerciseSessionService {

    private final ExerciseSessionRepository repository;
    private final ExerciseSessionMapper mapper;
    private final SetService setService;
    private final SecurityService securityService;
    private final ProgramDayExerciseService pdeService;

    @Autowired
    public ExerciseSessionService(ExerciseSessionRepository repository,
                                  ExerciseSessionMapper mapper,
                                  SetService setService,
                                  SecurityService securityService,
                                  ProgramDayExerciseService pdeService){
        this.repository = repository;
        this.mapper = mapper;
        this.setService = setService;
        this.securityService = securityService;
        this.pdeService = pdeService;
    }

    public ExerciseSessionDto getByExerciseSessionId(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ExerciseSessionNotFoundException(id)));
    }

    public Boolean existsByProgramDayExerciseId(Long id){
       return repository.existsByProgramDayExercise_Id(id);
    }

    @Transactional
    public void deleteByProgramDayExerciseIdAndWeekNumberIfExists(Long id, int weekNumber){

        ExerciseSession es = repository.findByProgramDayExercise_IdAndWeekNumber(id, weekNumber).orElse(null);
        if (es != null){
            repository.deleteById(es.getId());
            repository.flush();
        }
    }

    /**
     * Hibernate ensures child entities are deleted, as long as entity configuration is correct as "orphan removal = true"
     * is used allowing child entities to be deleted.
     *
     * the is also no need to use flush() to force transaction to happen to make may for new data as Hibernate also ensures
     * correct order at commit so 1. Delete current, execute current and then insert current data
     *
     * Exists check are also pointless as it creates an extra thread that in the end just does nothing because if detele method
     * executes, and there's no matching data then 0 rows are deleted - no harm no foul
     */

    public Boolean existsByProgramDayExerciseIdAndWeekNumber(Long id, int weekNumber){
        return repository.existsByProgramDayExercise_IdAndWeekNumber(id, weekNumber);
    }

    public ExerciseSessionDto logExerciseSession(ExerciseSessionInputDto dto){

        Boolean correctPDEOwner = securityService.programDayOwnershipCheck(dto.programDayExerciseId());
        ProgramDayExerciseDto pde = pdeService.getProgramDayExercise(dto.programDayExerciseId());

        if (!correctPDEOwner || pde != null){
            throw new ProgramDayExerciseNotFoundException(dto.programDayExerciseId());
        }

        return null;
    }
}
