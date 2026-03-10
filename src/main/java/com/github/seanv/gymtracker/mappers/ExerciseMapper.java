package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ExerciseDto;
import com.github.seanv.gymtracker.dto.input.ExerciseInputDto;
import com.github.seanv.gymtracker.entities.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto toDto(Exercise exercise);
    Exercise fromDto(ExerciseDto dto);
    Exercise fromDto(ExerciseInputDto dto);
}
