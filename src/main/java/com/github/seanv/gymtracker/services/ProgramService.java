package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.exception.type.ProgramNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final ProgramDayService programDayService;

    @Autowired
    private ProgramService(ProgramRepository programRepository,
                           ProgramMapper mapper,
                           ProgramDayService programDayService){
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programDayService = programDayService;
    }

    /**
     * When retrieving Program, db calls were split up otherwise it would have resulted in nested collections
     * having to be retried in one call, which is overkill which kills performance and could lead to
     * Cartesian Product Explosion
     * **/
    public ProgramDto getProgram(Long id){
        var m = mapper.toDto(programRepository.findById(id).orElseThrow(() -> new ProgramNotFoundException(id)));
        m.setProgramDays(getProgramDays(m.getId()));
        return m;
    }

    /**
     * (() -> new ProgramNotFoundException(id)) lambda is used as orElseThrow expects Supplier.get() which expects no args,
     * so we have to wrap in lambda as it needs an arg for exception.
     *
     * if exception didn't take in args we would use RuntimeException::new
     *
     * NB! - we cant use new RuntimeException() as it would create exception even if value exists, that's why we use
     * ()-> new RuntimeException to only create exception when its needed
     * */

    public List<ProgramDto> getAllPrograms(){
        var a =  programRepository.findAll();
        return a.stream().map(mapper::toDto).toList();
    }

    private List<ProgramDayDto> getProgramDays(Long programId){
        return programDayService.getProgramDaysByProgramId(programId);
    }

    public List<ProgramDto> getAllProgramsByUserId(Long userId){
        var list = programRepository.getAllProgramsByUser_Id(userId).stream().map(mapper::toDto).toList();
        list.forEach( i -> {
            i.setProgramDays(getProgramDays(i.getId()));
        });

        return list;
    }
}
