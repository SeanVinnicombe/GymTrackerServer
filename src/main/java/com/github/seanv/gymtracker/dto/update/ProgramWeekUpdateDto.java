package com.github.seanv.gymtracker.dto.update;

import com.github.seanv.gymtracker.dto.input.ProgramDayInputDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProgramWeekUpdateDto(
        @NotNull Integer weekNumber,
        List<ProgramDayUpdateDto> programDays) {
}
