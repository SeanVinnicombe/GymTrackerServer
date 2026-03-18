package com.github.seanv.gymtracker.mappers;

import com.github.seanv.gymtracker.dto.SetDto;
import com.github.seanv.gymtracker.entities.Set;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SetMapper {

    SetDto toDto(Set set);
}
