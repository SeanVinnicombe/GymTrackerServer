package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.dto.input.ProgramWeekInputDto;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.mappers.ProgramWeekMapper;
import com.github.seanv.gymtracker.repositories.ProgramWeekRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;

@Service
public class ProgramWeekService {

    private final ProgramWeekRepository repository;
    private final ProgramWeekMapper mapper;
    private final SetService setService;

    @Autowired
    public ProgramWeekService(ProgramWeekRepository repository, ProgramWeekMapper mapper, SetService setService) {
        this.repository = repository;
        this.mapper = mapper;
        this.setService = setService;
    }

    public ProgramWeekDto getProgramWeek(Long programId, Integer weekNumber) {

        var result = repository.findByProgram_IdAndWeekNumber(programId, weekNumber);
        result.getProgramDays().forEach(
                i -> i.setProgramDayExercises(i.getProgramDayExercises()
                        .stream()
                        .sorted(Comparator.comparing(ProgramDayExercise::getExerciseNumber))
                        .toList())
        );
        return mapper.toDto(result);
    }

    public List<ProgramWeekDto> getProgramWeeksByProgramId(Long programId) {
        return repository.findAllByProgram_Id(programId).stream().map(mapper::toDto).toList();
    }

    public ProgramWeekDto updateProgramWeek(ProgramWeekInputDto inputDto){

        ProgramWeek programWeek = mapper.fromDto(inputDto);
        return null;
    }
}
