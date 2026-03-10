package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.exception.type.ProgramDayExerciseNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramDayExerciseMapper;
import com.github.seanv.gymtracker.repositories.ProgramDayExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramDayExerciseService {

    private final ProgramDayExerciseRepository repository;
    private final ProgramDayExerciseMapper mapper;

    @Autowired
    public ProgramDayExerciseService(ProgramDayExerciseRepository repository,ProgramDayExerciseMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProgramDayExerciseDto> getProgramDayExerciseByProgramDayId(Long programDayId){
        return repository.findAllByProgramDay_Id(programDayId)
                .stream()
                .map(mapper::toDto).toList();
    }

    public ProgramDayExerciseDto getProgramDayExercise(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ProgramDayExerciseNotFoundException(id)));
    }
}
