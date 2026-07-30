package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.update.ProgramDayExerciseUpdateDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ExerciseSessionMapper.class)
public interface ProgramDayExerciseMapper {

    @Mapping(source = "exercise.name", target = "exerciseName")
    ProgramDayExerciseDto toDto(ProgramDayExercise programDayExercise);

    ProgramDayExercise fromDto(ProgramDayExerciseDto dto);

    void updateProgramDayExercise(ProgramDayExerciseUpdateDto dto, @MappingTarget ProgramDayExercise programDayExercise);

//    ProgramDayExercise fromUpdateDto(ProgramDayExerciseUpdateDto dto);
}
