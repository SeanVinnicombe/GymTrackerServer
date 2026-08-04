package com.github.seanv.gymtracker.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import com.github.seanv.gymtracker.mappers.ProgramDayMapper;
import com.github.seanv.gymtracker.repositories.ProgramDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ProgramDayService {

    private final ProgramDayRepository repository;
    private final ProgramDayMapper mapper;
    private final ProgramDayExerciseService programDayExerciseService;
    private final ProgramDayMapper programDayMapper;

    @Autowired
    public ProgramDayService(ProgramDayRepository repository,
                             ProgramDayMapper mapper,
                             ProgramDayExerciseService programDayExerciseService, ProgramDayMapper programDayMapper){
        this.repository = repository;
        this.mapper = mapper;
        this.programDayExerciseService = programDayExerciseService;
        this.programDayMapper = programDayMapper;
    }

    /** Set is expected, but due to hashing, result has items in random order, so it is converted first to list
        and then Comparator class is used to compare the Ids of the Class type "ProgramDayDTO" class
     **/
    public List<ProgramDayDto> getProgramDaysByProgramId(Long programWeekId){

        var list = repository.findAllByProgramWeek_Id(programWeekId)
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

    public List<ProgramDayDto> saveProgramDays(List<ProgramDayInputDto> inputDtos, Long programId){
        List<ProgramDay> list = inputDtos.stream().map(mapper::fromInputDto).toList();
        List<ProgramDay> savedList = repository.saveAll(list);
        return savedList.stream().map(mapper::toDto).toList();

    }

    public List<ProgramDay> buildProgramDays(List<ProgramDayInputDto> dtoList, ProgramWeek week){

        List<ProgramDay> days = new ArrayList<>();
        AtomicInteger dayOrder = new AtomicInteger(1);
        for (ProgramDayInputDto dto : dtoList){
            ProgramDay pd = programDayMapper.fromInputDto(dto);
            List<ProgramDayExercise> pdeList = programDayExerciseService.buildProgramDayExercises(dto.programDayExercises(), pd);
            pd.setProgramWeek(week);
            pd.setDayOrder(dayOrder.getAndIncrement());
            pd.getProgramDayExercises().clear();
            pd.setProgramDayExercises(pdeList);
            pd.setIsCompleted(false);
            pd.setUpdatedAt(LocalDateTime.now());
            days.add(pd);
        }

        return days;
    }

}
