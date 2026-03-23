package com.github.seanv.gymtracker.dto.update;

import com.github.seanv.gymtracker.dto.input.SetInputDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ExerciseSessionUpdateDto(@NotNull List<SetUpdateDto> sets, String notes) {
}
