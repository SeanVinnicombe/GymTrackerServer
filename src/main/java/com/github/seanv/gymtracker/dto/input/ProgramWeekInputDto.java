package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramWeekInputDto(
        @NotNull Integer weekNumber,
        List<ProgramDayInputDto> programDays
) {
}
