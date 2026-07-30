package com.github.seanv.gymtracker.services.sync;

import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramWeekInputDto;
import com.github.seanv.gymtracker.dto.update.ProgramDayExerciseUpdateDto;
import com.github.seanv.gymtracker.dto.update.ProgramDayUpdateDto;
import com.github.seanv.gymtracker.dto.update.ProgramUpdateDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import com.github.seanv.gymtracker.exception.type.ProgramDayExerciseNotFoundException;
import com.github.seanv.gymtracker.exception.type.ProgramDayNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramDayExerciseMapper;
import com.github.seanv.gymtracker.mappers.ProgramDayMapper;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.mappers.ProgramWeekMapper;
import com.github.seanv.gymtracker.repositories.ProgramDayExerciseRepository;
import com.github.seanv.gymtracker.repositories.ProgramDayRepository;
import com.github.seanv.gymtracker.services.ProgramService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProgramSynchronizer {

    private final ProgramMapper programMapper;
    private final ProgramDayMapper programDayMapper;
    private final ProgramDayRepository programDayRepository;
    private final ProgramDayExerciseMapper programDayExerciseMapper;
    private final ProgramDayExerciseRepository programDayExerciseRepository;

    public ProgramSynchronizer(ProgramMapper programMapper,
                               ProgramWeekMapper programWeekMapper,
                               ProgramDayMapper programDayMapper,
                               ProgramDayRepository programDayRepository,
                               ProgramDayExerciseMapper programDayExerciseMapper, ProgramDayExerciseRepository programDayExerciseRepository) {
        this.programMapper = programMapper;
        this.programDayMapper = programDayMapper;
        this.programDayRepository = programDayRepository;
        this.programDayExerciseMapper = programDayExerciseMapper;
        this.programDayExerciseRepository = programDayExerciseRepository;
    }

    public void synchronize(Program program, ProgramUpdateDto dto) {
        programMapper.updateProgram(dto, program);
        synchronizeProgramDays(program, dto.programDays());
    }

    void synchronizeProgramDays(Program program, List<ProgramDayUpdateDto> dto) {

        List<ProgramDayUpdateDto> days = new ArrayList<>(dto);
        Map<Long, ProgramDayUpdateDto> existingDays = days.stream()
                .filter(i -> i.programDayId() != null)
                .collect(Collectors.toMap(ProgramDayUpdateDto::programDayId, Function.identity()));

        List<ProgramDay> updatedList = new ArrayList<>();
        for (ProgramDayUpdateDto pd : dto){

            if (pd.programDayId() == null){ // new item
                ProgramDay p = new ProgramDay();
                programDayMapper.updateProgramDay(pd, p);
                updatedList.add(p);
                continue;
            }

            ProgramDay entity = programDayRepository.findById(pd.programDayId()).orElseThrow(() -> new ProgramDayNotFoundException(pd.programDayId()));
            programDayMapper.updateProgramDay(pd, entity);

            synchronizeProgramDayExercise(entity, pd.programDayExercises());

            updatedList.add(entity);
        }

        for (ProgramWeek programWeek : program.getProgramWeeks()) {
            programWeek.getProgramDays().clear();
            programWeek.setProgramDays(updatedList);
        }
    }

    void synchronizeProgramDayExercise(ProgramDay programDay, List<ProgramDayExerciseUpdateDto> dto) {

        List<ProgramDayExerciseUpdateDto> days = new ArrayList<>(dto);
        Map<Long, ProgramDayExerciseUpdateDto> existingDays = days.stream()
                .filter(i -> i.programDayExerciseId() != null)
                .collect(Collectors.toMap(ProgramDayExerciseUpdateDto::programDayExerciseId, Function.identity()));

        List<ProgramDayExercise> updatedList = new ArrayList<>();
        for (ProgramDayExerciseUpdateDto pde : dto) {

            if (pde.programDayExerciseId() == null) { // new item
                ProgramDayExercise newEntity = new ProgramDayExercise();
                programDayExerciseMapper.updateProgramDayExercise(pde, newEntity);
                updatedList.add(newEntity);
                continue;
            }

            ProgramDayExercise entity = programDayExerciseRepository.findById(pde.programDayExerciseId()).orElseThrow(() -> new ProgramDayExerciseNotFoundException(pde.programDayExerciseId()));
            programDayExerciseMapper.updateProgramDayExercise(pde, entity);

            updatedList.add(entity);
        }

        programDay.getProgramDayExercises().clear();
        programDay.setProgramDayExercises(updatedList);
    }
}
