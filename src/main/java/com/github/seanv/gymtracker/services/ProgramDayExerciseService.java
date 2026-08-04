package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;
import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.exception.type.ProgramDayExerciseNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseMapper;
import com.github.seanv.gymtracker.mappers.ProgramDayExerciseMapper;
import com.github.seanv.gymtracker.repositories.ProgramDayExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProgramDayExerciseService {

    private final ProgramDayExerciseRepository repository;
    private final ProgramDayExerciseMapper mapper;
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    public ProgramDayExerciseService(ProgramDayExerciseRepository repository, ProgramDayExerciseMapper mapper, ExerciseService exerciseService, ExerciseMapper exerciseMapper){
        this.repository = repository;
        this.mapper = mapper;
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }

    public List<ProgramDayExerciseDto> getProgramDayExerciseByProgramDayId(Long programDayId){
        return repository.findAllByProgramDay_Id(programDayId)
                .stream()
                .map(mapper::toDto).toList();
    }

    public ProgramDayExerciseDto getProgramDayExercise(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ProgramDayExerciseNotFoundException(id)));
    }

    public List<ProgramDayExercise> buildProgramDayExercises(List<ProgramDayExerciseInputDto> dtoList, ProgramDay programDay){

        List<ProgramDayExercise> list = new ArrayList<>();
        AtomicInteger order = new AtomicInteger(1);
        for (ProgramDayExerciseInputDto dto : dtoList){
            ProgramDayExercise pde = mapper.fromInputDto(dto);
            pde.setExerciseOrder(order.getAndIncrement());
            pde.setCreatedAt(LocalDateTime.now());
            pde.setUpdatedAt(LocalDateTime.now());
            Exercise exercise = exerciseMapper.fromDto(exerciseService.getExercise(dto.exerciseId()));
            pde.setExercise(exercise);
            pde.setProgramDay(programDay);
            list.add(pde);
        }

        return list;
    }
}
