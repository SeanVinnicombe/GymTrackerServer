package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProgramDayInputDto(
        @NotBlank String muscleGroup,
        List<ProgramDayExerciseInputDto> programDayExercises
) {
}
