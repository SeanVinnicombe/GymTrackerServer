package com.github.seanv.gymtracker.services;


import com.github.seanv.gymtracker.dto.input.ExerciseInputDto;
import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
import com.github.seanv.gymtracker.dto.ExerciseDto;
import com.github.seanv.gymtracker.exception.type.ExerciseExistsException;
import com.github.seanv.gymtracker.exception.type.ExerciseNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseMapper;
import com.github.seanv.gymtracker.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;

    @Autowired
    public ExerciseService(ExerciseRepository repository, ExerciseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExerciseDto getExercise(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ExerciseNotFoundException(id)));
    }

    public List<ExerciseDto> getAllExercisesByMuscleGroup(MuscleGroup muscleGroup) {
        return repository.findAllExercisesByMuscleGroup(muscleGroup).stream().map(mapper::toDto).toList();
    }

    public ExerciseDto addNewExercise(ExerciseInputDto dto){
        if (repository.existsByName(dto.name())){
            throw new ExerciseExistsException(dto.name());
        }
        Exercise exercise = mapper.fromDto(dto);
        var result = repository.save(exercise);
        return mapper.toDto(result);
    }
}
