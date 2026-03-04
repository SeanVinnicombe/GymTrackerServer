package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.mappers.ProgramDayMapper;
import com.github.seanv.gymtracker.repositories.ProgramDayExerciseRepository;
import com.github.seanv.gymtracker.repositories.ProgramDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgramDayService {

    private final ProgramDayRepository repository;
    private final ProgramDayMapper mapper;
    private final ProgramDayExerciseService programDayExerciseService;

    @Autowired
    public ProgramDayService(ProgramDayRepository repository,
                             ProgramDayMapper mapper,
                             ProgramDayExerciseService programDayExerciseService){
        this.repository = repository;
        this.mapper = mapper;
        this.programDayExerciseService = programDayExerciseService;
    }

    /** Set is expected, but due to hashing, result has items in random order, so it is converted first to list
        and then Comparator class is used to compare the Ids of the Class type "ProgramDayDTO" class
     **/
    public List<ProgramDayDto> getProgramDaysByProgramId(Long programId){

        var list = repository.findAllByProgram_Id(programId)
                .stream()
                .map(mapper::toDto).distinct().collect(Collectors.toCollection(ArrayList::new));

        list.forEach( i -> {
            i.setProgramDayExercises(getProgramDayExerciseByProgramDayId(i.getId()));
        });

        return list;
    }

    public List<ProgramDayExerciseDto> getProgramDayExerciseByProgramDayId(Long programDayId){
       return programDayExerciseService.getProgramDayExerciseByProgramDayId(programDayId);
    }

}
