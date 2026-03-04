package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.ProgramDay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramDayMapper {

    ProgramDayDto toDto(ProgramDay programDay);

    ProgramDay fromDto(ProgramDayDto dto);
}
