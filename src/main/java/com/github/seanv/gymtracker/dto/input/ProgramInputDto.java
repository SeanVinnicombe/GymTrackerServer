package com.github.seanv.gymtracker.dto.input;

import com.github.seanv.gymtracker.entities.ProgramWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramInputDto(
        @NotBlank String name,
        @NotNull Integer numberOfWeeks,
        List<ProgramWeekInputDto> programWeeks
) {
}
