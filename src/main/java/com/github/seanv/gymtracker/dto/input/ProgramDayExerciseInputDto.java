package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramDayExerciseInputDto(
        @NotNull Long exerciseId,
        @NotNull Integer exerciseNumber,
        @NotNull Integer targetSets,
        @NotNull Integer targetReps,
        List<ExerciseSessionInputDto> exerciseSession
        ) {
}
