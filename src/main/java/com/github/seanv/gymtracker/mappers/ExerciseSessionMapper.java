package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.dto.update.ExerciseSessionUpdateDto;
import com.github.seanv.gymtracker.entities.ExerciseSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SetMapper.class)
public interface ExerciseSessionMapper {

    ExerciseSessionDto toDto(ExerciseSession exerciseSession);
    ExerciseSession fromUpdateDto(ExerciseSessionUpdateDto dto);
}
