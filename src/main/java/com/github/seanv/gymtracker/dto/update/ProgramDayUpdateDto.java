package com.github.seanv.gymtracker.dto.update;

import com.github.seanv.gymtracker.dto.input.ProgramDayExerciseInputDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProgramDayUpdateDto(Long programDayId, @NotBlank String muscleGroup,
                                  List<ProgramDayExerciseUpdateDto> programDayExercises) {
}
