package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.mappers.ProgramWeekMapper;
import com.github.seanv.gymtracker.repositories.ProgramWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramWeekService {

    private final ProgramWeekRepository repository;
    private final ProgramWeekMapper mapper;

    @Autowired
    public ProgramWeekService(ProgramWeekRepository repository, ProgramWeekMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProgramWeekDto getProgramWeek(Long programId, Integer weekNumber){

        var result = repository.findByProgram_IdAndWeekNumber(programId, weekNumber);
        return mapper.toDto(result);
    }

    public List<ProgramWeekDto> getProgramWeeksByProgramId(Long programId){
        return repository.findAllByProgram_Id(programId).stream().map(mapper::toDto).toList();
    }
}
