package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramInputDto(
        @NotBlank String name,
        @NotNull Integer numberOfWeeks,
        List<ProgramDayInputDto> programDays
) {
}
