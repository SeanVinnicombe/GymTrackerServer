package com.github.seanv.gymtracker.dto.update;

public record LoggingDto(
        Integer weekNumber,
        Long programDayId,
        Long programDayExerciseId,
        ExerciseSessionUpdateDto exerciseSessionUpdateDto
) {
}
