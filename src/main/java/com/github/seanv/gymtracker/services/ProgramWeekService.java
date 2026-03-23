package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.dto.input.*;
import com.github.seanv.gymtracker.dto.update.*;
import com.github.seanv.gymtracker.entities.*;
import com.github.seanv.gymtracker.exception.type.ProgramWeekNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramDayMapper;
import com.github.seanv.gymtracker.mappers.ProgramWeekMapper;
import com.github.seanv.gymtracker.repositories.ExerciseSessionRepository;
import com.github.seanv.gymtracker.repositories.ProgramWeekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProgramWeekService {

    private final ProgramWeekRepository repository;
    private final ProgramWeekMapper mapper;
    private final SetService setService;
    private final ProgramDayMapper programDayMapper;
    private final ExerciseService exerciseService;
    private final ExerciseSessionService exerciseSessionService;

    @Autowired
    public ProgramWeekService(ProgramWeekRepository repository,
                              ProgramWeekMapper mapper,
                              SetService setService,
                              ProgramDayMapper programDayMapper,
                              ExerciseService exerciseService,
                              ExerciseSessionService exerciseSessionService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.setService = setService;
        this.programDayMapper = programDayMapper;
        this.exerciseService = exerciseService;
        this.exerciseSessionService = exerciseSessionService;
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

    @Transactional
    public ProgramWeekDto updateProgramWeek(Long programWeekId, ProgramWeekUpdateDto inputDto){

        ProgramWeek programWeek = mapper.fromDto(inputDto);
        ProgramWeek programWeekDB = repository.findById(programWeekId)
                .orElseThrow(
                        () -> new ProgramWeekNotFoundException(programWeekId)
                );
        List<ProgramDay> programDays = new ArrayList<>();

        for (ProgramDayUpdateDto pd : inputDto.programDays()){
            ProgramDay programDay = convertToProgramDay(pd);
            programDays.add(programDay);
        }

        programWeek.setProgramDays(programDays);

        var result = repository.save(programWeek);

        return mapper.toDto(result);
    }

    public ProgramDay convertToProgramDay(ProgramDayUpdateDto inputDto){

        ProgramDay programDay = new ProgramDay();
        List<ProgramDayExercise> programDayExercises = new ArrayList<>();

        for (ProgramDayExerciseUpdateDto pde : inputDto.programDayExercises()){
            ProgramDayExercise programDayExercise = convertToProgramDayExercise(pde);
            programDayExercises.add(programDayExercise);
        }

        programDay.setProgramDayExercises(programDayExercises);

        return programDay;
    }

    public ProgramDayExercise convertToProgramDayExercise(ProgramDayExerciseUpdateDto inputDto){

        ProgramDayExercise programDayExercise = new ProgramDayExercise();

        var programDayExerciseId = 1L;

        var exerciseSessionExists = exerciseSessionService.existsByProgramDayExerciseId(programDayExerciseId);
        if (exerciseSessionExists){
            exerciseSessionService.deleteByProgramDayExerciseId(programDayExerciseId);
        }

        if (inputDto.exerciseSession() != null){
            ExerciseSession exerciseSession = convertToExerciseSession(inputDto.exerciseSession());
            programDayExercise.setExerciseSession(exerciseSession);
        } else {
            programDayExercise.setExerciseSession(null);
        }

        return programDayExercise;
    }

    public ExerciseSession convertToExerciseSession(ExerciseSessionUpdateDto inputDto){



        ExerciseSession exerciseSession = new ExerciseSession();
        exerciseSession.setNotes(inputDto.notes());
        List<Set> sets = new ArrayList<>();

        for (SetUpdateDto set : inputDto.sets()){
            Set result = convertToSet(set);
            sets.add(result);
        }

        exerciseSession.setSets(sets);

        return exerciseSession;
    }

    public Set convertToSet(SetUpdateDto inputDto){

        Set set = new Set();

        set.setSetOrder(inputDto.setOrder());
        set.setAchievedReps(inputDto.achievedReps());
        set.setWeightDone(inputDto.weightDone());

        return set;
    }

}
