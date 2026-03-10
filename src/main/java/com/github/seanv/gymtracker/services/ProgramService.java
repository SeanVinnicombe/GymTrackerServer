package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;
import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.exception.type.ProgramNotFoundException;
import com.github.seanv.gymtracker.mappers.ExerciseMapper;
import com.github.seanv.gymtracker.mappers.ProgramDayMapper;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.mappers.UserMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final ProgramDayService programDayService;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final UserMapper userMapper;

    @Autowired
    public ProgramService(ProgramRepository programRepository,
                           ProgramMapper mapper,
                           ProgramDayService programDayService,
                           UserService userService,
                           ExerciseService exerciseService,
                           ExerciseMapper exerciseMapper,
                           UserMapper userMapper

    ){
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programDayService = programDayService;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.userMapper = userMapper;
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

    public List<ProgramDayDto> getProgramDays(Long programId){
        return programDayService.getProgramDaysByProgramId(programId);
    }

    public List<ProgramDto> getAllProgramsByUserId(Long userId){
        userService.getUser(userId);
        var list = programRepository.getAllProgramsByUser_Id(userId).stream().map(mapper::toDto).toList();
        list.forEach( i -> {
            i.setProgramDays(getProgramDays(i.getId()));
        });

        return list;
    }

    @Transactional
    public ProgramDto createProgram(ProgramInputDto inputDto){

        Program program = new Program();
        program.setUser(userService.getUserEntity(1L));
        List<ProgramDay> programDays = new ArrayList<>();
        program.setName(inputDto.name());
        program.setProgramLength(inputDto.numberOfWeeks());
        for(ProgramDayInputDto pd : inputDto.programDays()){
            ProgramDay programDay = new ProgramDay();
            programDay.setProgram(program);
            programDay.setMuscleGroup(pd.muscleGroups());
            List<ProgramDayExercise> programDayExercises = new ArrayList<>();
            for(ProgramDayExerciseInputDto pde : pd.programDayExercises()){
                ProgramDayExercise programDayExercise = new ProgramDayExercise();
                programDayExercise.setProgramDay(programDay);
                var exercise = exerciseService.getExercise(pde.exerciseId());
                programDayExercise.setExercise(exerciseMapper.fromDto(exercise));
                programDayExercise.setTargetReps(pde.targetReps());
                programDayExercise.setTargetSets(pde.targetSets());
                programDayExercises.add(programDayExercise);
            }
            programDay.setProgramDayExercises(programDayExercises);
            programDays.add(programDay);
        }
        program.setProgramDays(programDays);

        var result = mapper.toDto(programRepository.save(program));
        result.getProgramDays().forEach(i -> {
            int counter = 0;
            for ( ProgramDayExerciseDto dto : i.getProgramDayExercises()){
                dto.setExerciseNumber(++counter);
            }
        });

        return result;
    }
}
