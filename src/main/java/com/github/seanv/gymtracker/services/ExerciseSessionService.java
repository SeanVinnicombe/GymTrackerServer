package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.entities.ExerciseSession;
import com.github.seanv.gymtracker.exception.type.ExerciseSessionNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseSessionMapper;
import com.github.seanv.gymtracker.repositories.ExerciseSessionRepository;
import com.github.seanv.gymtracker.repositories.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExerciseSessionService {

    private final ExerciseSessionRepository repository;
    private final ExerciseSessionMapper mapper;
    private final SetService setService;

    @Autowired
    public ExerciseSessionService(ExerciseSessionRepository repository,
                                  ExerciseSessionMapper mapper,
                                  SetService setService){
        this.repository = repository;
        this.mapper = mapper;
        this.setService = setService;
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
}
