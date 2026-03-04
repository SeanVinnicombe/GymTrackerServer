package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final ProgramDayExerciseService programDayExerciseService;
    private final ProgramDayService programDayService;

    @Autowired
    private ProgramService(ProgramRepository programRepository,
                           ProgramMapper mapper,
                           ProgramDayExerciseService programDayExerciseService,
                           ProgramDayService programDayService){
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programDayExerciseService = programDayExerciseService;
        this.programDayService = programDayService;
    }

    /**
     * When retrieving Program, db calls were split up otherwise it would have resulted in nested collections
     * having to be retried in one call, which is overkill which kills performance and could lead to
     * Cartesian Product Explosion
     * **/
    public ProgramDto getProgram(Long id){
        var m = mapper.toDto(programRepository.findById(id).orElseThrow(RuntimeException::new));
        m.setProgramDays(getProgramDays(m.getId()));
        return m;
    }

    public List<ProgramDayDto> getProgramDays(Long programId){
        return programDayService.getProgramDaysByProgramId(programId);
    }
}
