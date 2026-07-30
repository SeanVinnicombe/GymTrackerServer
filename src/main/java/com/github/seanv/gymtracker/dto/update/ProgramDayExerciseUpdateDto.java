package com.github.seanv.gymtracker.dto.update;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramDayExerciseUpdateDto(Long programDayExerciseId,
                                          @NotNull Long exerciseId,
                                          @NotNull Integer exerciseNumber,
                                          @NotNull Integer targetSets,
                                          @NotNull Integer targetReps) {
}
