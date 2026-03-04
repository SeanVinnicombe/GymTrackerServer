package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramDayExerciseMapper {

    @Mapping(source = "exercise.name", target = "exerciseName")
    ProgramDayExerciseDto toDto(ProgramDayExercise programDayExercise);

    ProgramDayExercise fromDto(ProgramDayExerciseDto dto);
}
