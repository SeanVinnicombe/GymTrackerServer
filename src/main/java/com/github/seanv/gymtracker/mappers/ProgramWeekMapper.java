package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {ProgramDayMapper.class})
public interface ProgramWeekMapper {

    ProgramWeek fromDto(ProgramWeekDto dto);

    @Mapping(target = "programId", source = "program.id")
    ProgramWeekDto toDto(ProgramWeek programWeek);
}
