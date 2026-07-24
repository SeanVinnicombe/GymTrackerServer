package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SetInputDto(@NotNull Integer setOrder, @NotNull @Min(value = 1) Integer achievedReps, @Min(value = 1) @NotNull Integer weightDone) {
}
