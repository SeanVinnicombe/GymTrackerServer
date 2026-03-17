package com.github.seanv.gymtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDto {

    private Long id;
    private Integer programLength;
    private List<ProgramWeekDto> programWeeks;

    public ProgramDto(Long id){
        this.id = id;
    }
    public ProgramDto(Long id, Integer programLength){
        this.id = id;
        this.programLength = programLength;
    }

}
