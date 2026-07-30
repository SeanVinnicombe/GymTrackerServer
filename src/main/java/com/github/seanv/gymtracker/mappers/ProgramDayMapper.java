package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import com.github.seanv.gymtracker.dto.update.ProgramDayUpdateDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ProgramDayExerciseMapper.class})
public interface ProgramDayMapper {

    ProgramDayDto toDto(ProgramDay programDay);

    ProgramDay fromInputDto(ProgramDayInputDto dto);

    void updateProgramDay(ProgramDayUpdateDto dto, @MappingTarget ProgramDay programDay);

//    ProgramDay fromUpdateDto(ProgramDayUpdateDto dto);
}
