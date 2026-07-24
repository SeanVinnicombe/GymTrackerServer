package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ExerciseSessionInputDto(@NotNull List<SetInputDto> sets,
                                      String notes,
                                      @NotNull Long programDayExerciseId,
                                      @NotNull LocalDateTime createdAt
) {
}
