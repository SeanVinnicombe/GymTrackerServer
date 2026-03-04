package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    ProgramDto toDto(Program program);
    Program fromDto(ProgramDto dto);
}
