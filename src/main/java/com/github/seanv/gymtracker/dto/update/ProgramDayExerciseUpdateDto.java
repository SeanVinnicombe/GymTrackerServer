package com.github.seanv.gymtracker.dto.update;

import java.util.List;

public record ProgramDayExerciseUpdateDto(Long programDayExerciseId, ExerciseSessionUpdateDto exerciseSession) {
}
