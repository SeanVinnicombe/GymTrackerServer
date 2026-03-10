package com.github.seanv.gymtracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProgramDto {

    private Long id;
    private Integer programLength;
    private List<ProgramDayDto> programDays;

    public ProgramDto(Long id){
        this.id = id;
    }

}
