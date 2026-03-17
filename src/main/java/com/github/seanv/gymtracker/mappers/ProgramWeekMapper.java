package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = {ProgramDayMapper.class})
public interface ProgramWeekMapper {

    ProgramWeek fromDto(ProgramWeekDto dto);
    ProgramWeekDto toDto(ProgramWeek programWeek);
}
