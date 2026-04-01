package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.dto.input.*;
import com.github.seanv.gymtracker.dto.update.*;
import com.github.seanv.gymtracker.entities.*;
import com.github.seanv.gymtracker.exception.type.ProgramWeekNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramWeekMapper;
import com.github.seanv.gymtracker.repositories.ProgramWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProgramWeekService {

    private final ProgramWeekRepository repository;
    private final ProgramWeekMapper mapper;
    private final ExerciseSessionService exerciseSessionService;
    //private int weekNumber;

    /**
     * For weekNumber, remember Spring beans are singleton so the state is shared across application and so if  you
     * have global variable that has a certain value and 2 threads ( 2 users accessing method at same time)
     * that use class, they both use variable and the one might be on different week which can then cause
     * issue.
     *
     * Remember each method call gets its own stack frame for its local variables and that's why it's better to use it
     * as a local variable and pass it that way instead of instance(global) variable which is shared via the whole
     * class.
     *
     * Instance variables -> live on heap, shared across all threads
     * Local variables -> Belong to the stack, private to that specific method - threads don't get confused
     *
     *
     * Singleton bean + mutable instance field + concurrent requests = a race condition - not good
     */

    @Autowired
    public ProgramWeekService(ProgramWeekRepository repository,
                              ProgramWeekMapper mapper,
                              ExerciseSessionService exerciseSessionService
    ) {
        this.repository = repository;
        this.mapper = mapper;
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

        ProgramWeek programWeek = repository.findById(programWeekId)
                .orElseThrow(
                        () -> new ProgramWeekNotFoundException(programWeekId)
                );

        Map<Long, ProgramDay> dayMap = programWeek.getProgramDays()
                .stream()
                .collect(Collectors.toMap(ProgramDay::getId, Function.identity()));
        List<ProgramDay> programDays = new ArrayList<>();

        for (ProgramDayUpdateDto pdDto : inputDto.programDays()){
            ProgramDay programDay = dayMap.get(pdDto.programDayId());
            ProgramDay result = convertToProgramDay(pdDto, programDay,inputDto.weekNumber());
            programDays.add(result);
        }

        List<ProgramDay> existing = programWeek.getProgramDays();
        existing.clear();
        existing.addAll(programDays);

        var result = repository.save(programWeek);

        return mapper.toDto(result);
    }

    /***
     *Hibernate tracks collections and so when you provide a "new" collection in place of old one,
     * Hibernate see's new list, add disregards old one which means:
     * 1. Orphans are not detected
     * 2.Old rows are not deleted
     * 3.New rows are inserted
     *
     * NB -> NEVER replace collections in JPA. Modify existing collection, do not replace the whole thing
     *
     * When modifying Hibernate see's:
     * Collection changed:
     * - item removed → DELETE (orphanRemoval)
     * - item added → INSERT
     *
     * Therefore, DB stays insync
     */

    public ProgramDay convertToProgramDay(ProgramDayUpdateDto inputDto, ProgramDay programDay, int weekNumber){

        Map<Long, ProgramDayExercise> programDayExerciseMap = programDay.getProgramDayExercises()
                .stream()
                .collect(Collectors.toMap(ProgramDayExercise::getId, Function.identity()));

        List<ProgramDayExercise> programDayExercises = new ArrayList<>();

        for (ProgramDayExerciseUpdateDto pdeDto : inputDto.programDayExercises()){
            ProgramDayExercise pde = programDayExerciseMap.get(pdeDto.programDayExerciseId());
            ProgramDayExercise programDayExercise = convertToProgramDayExercise(pdeDto, pde, weekNumber);
            programDayExercises.add(programDayExercise);
        }

        List<ProgramDayExercise> existing = programDay.getProgramDayExercises();
        existing.clear();
        existing.addAll(programDayExercises);

        programDay.setId(programDay.getId());
        return programDay;
    }

    public ProgramDayExercise convertToProgramDayExercise(ProgramDayExerciseUpdateDto inputDto,
                                                          ProgramDayExercise programDayExercise,
                                                          int weekNumber){

        List<ExerciseSession> sessions = programDayExercise.getExerciseSession();

        if (inputDto.exerciseSession() != null){
            ExerciseSession es = sessions.stream().filter( i -> i.getWeekNumber()
                    .equals(weekNumber))
                    .findFirst()
                    .orElse(null);

            if (es != null){
                programDayExercise.removeExerciseSession(es);
            }
            ExerciseSession exerciseSession = convertToExerciseSession(inputDto.exerciseSession(),
                    programDayExercise,
                    weekNumber);
            programDayExercise.addExerciseSession(exerciseSession);
        }

        return programDayExercise;
    }

    /***
     * When deleting from DB, you have to ensure that the entity doesnt belong to parent collection anymore,  otherwise
     * Hibernate still has it part of its live collection and therefore it will add it back to DB, even though you just delted it.
     * So Its important to remove all traces of the entity when dealiting with realtionships and collections otherwise the
     * deleted data is seen to still as part of the Entity graph and Hibernate will just add it right back making it seems
     * as if it was never deleted
     *
     */

    public ExerciseSession convertToExerciseSession(ExerciseSessionUpdateDto inputDto,
                                                    ProgramDayExercise programDayExercise,
                                                    int weekNumber){


        ExerciseSession exerciseSession = new ExerciseSession();
        exerciseSession.setNotes(inputDto.notes());
        List<Set> sets = new ArrayList<>();
        exerciseSession.setSets(sets);
        exerciseSession.setWeekNumber(weekNumber);

        for (SetUpdateDto set : inputDto.sets()){
            Set result = convertToSet(set, exerciseSession);
            sets.add(result);
        }

        exerciseSession.setProgramDayExercise(programDayExercise);
        return exerciseSession;
    }

    public Set convertToSet(SetUpdateDto inputDto, ExerciseSession exerciseSession){

        Set set = new Set();

        set.setSetOrder(inputDto.setOrder());
        set.setAchievedReps(inputDto.achievedReps());
        set.setWeightDone(inputDto.weightDone());
        set.setExerciseSession(exerciseSession);

        return set;
    }

}
