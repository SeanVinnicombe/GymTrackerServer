package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ExerciseSessionInputDto(@NotNull List<SetInputDto> sets, String notes) {
}
