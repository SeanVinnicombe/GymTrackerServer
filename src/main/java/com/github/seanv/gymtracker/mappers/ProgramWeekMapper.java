package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.dto.input.ProgramWeekInputDto;
import com.github.seanv.gymtracker.dto.update.ProgramWeekUpdateDto;
import com.github.seanv.gymtracker.entities.ProgramWeek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {ProgramDayMapper.class})
public interface ProgramWeekMapper {

    ProgramWeek fromDto(ProgramWeekDto dto);

    ProgramWeek fromDto(ProgramWeekInputDto dto);

    ProgramWeek fromDto(ProgramWeekUpdateDto dto);

    @Mapping(target = "programId", source = "program.id")
    ProgramWeekDto toDto(ProgramWeek programWeek);
}
