package com.github.seanv.gymtracker.dto;

import com.github.seanv.gymtracker.entities.Exercise;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProgramDayDto {

    private Long id;
    private String muscleGroup;
    private List<ProgramDayExerciseDto> programDayExercises;
}
