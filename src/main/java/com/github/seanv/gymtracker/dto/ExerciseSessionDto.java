package com.github.seanv.gymtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSessionDto {

    private Long id;
    private List<SetDto> sets;
    private String notes;

}
