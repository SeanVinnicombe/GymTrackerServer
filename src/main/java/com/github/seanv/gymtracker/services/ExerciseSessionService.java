package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.entities.ExerciseSession;
import com.github.seanv.gymtracker.exception.type.ExerciseSessionNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseSessionMapper;
import com.github.seanv.gymtracker.repositories.ExerciseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseSessionService {

    private final ExerciseSessionRepository repository;
    private final ExerciseSessionMapper mapper;

    @Autowired
    public ExerciseSessionService(ExerciseSessionRepository repository, ExerciseSessionMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExerciseSessionDto getByExerciseSessionId(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ExerciseSessionNotFoundException(id)));
    }

    public Boolean existsByProgramDayExerciseId(Long id){
       return repository.existsByProgramDayExercise_Id(id);
    }

    public ExerciseSession findByProgramDayExerciseId(Long id){
        return repository.findByProgramDayExercise_Id(id);
    }

    public void deleteByProgramDayExerciseId(Long id){
        repository.deleteByProgramDayExercise_Id(id);
    }
}
