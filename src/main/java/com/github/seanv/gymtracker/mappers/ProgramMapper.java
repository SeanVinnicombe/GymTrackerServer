package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;
import com.github.seanv.gymtracker.entities.Program;
import org.mapstruct.Mapper;

/** You have to instruct MapStruct to make use of custom mappings otherwise it will create its own generated
 mappers which might miss the mappings you want
 */
@Mapper(componentModel = "spring",
uses = {ProgramWeekMapper.class})
public interface ProgramMapper {

    ProgramDto toDto(Program program);
    Program fromInputDto(ProgramInputDto dto);
}
