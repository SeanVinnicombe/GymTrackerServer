package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.relational.core.sql.In;

public record ProgramDayExerciseInputDto(
        @NotNull Long exerciseId,
        @NotNull Integer targetSets,
        @NotNull Integer targetReps
        ) {
}
