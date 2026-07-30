package com.github.seanv.gymtracker.dto.update;

import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramUpdateDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotNull Integer numberOfWeeks,
        List<ProgramDayUpdateDto> programDays) {
}
