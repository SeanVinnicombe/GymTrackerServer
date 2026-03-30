package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public record ProgramDayExerciseInputDto(
        @NotNull Long exerciseId,
        @NotNull Integer exerciseNumber,
        @NotNull Integer targetSets,
        @NotNull Integer targetReps,
        List<ExerciseSessionInputDto> exerciseSession
        ) {
}
