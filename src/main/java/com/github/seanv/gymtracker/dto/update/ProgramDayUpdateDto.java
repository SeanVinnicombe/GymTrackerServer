package com.github.seanv.gymtracker.dto.update;

import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;

import java.util.List;

public record ProgramDayUpdateDto(Long programDayId, List<ProgramDayExerciseUpdateDto> programDayExercises) {
}
