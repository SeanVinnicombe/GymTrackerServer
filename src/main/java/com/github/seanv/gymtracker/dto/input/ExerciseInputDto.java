package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotBlank;

public record ExerciseInputDto(
        @NotBlank String name,
        @NotBlank String muscleGroup) {
}

/** records are immutable(can't be changed) thus can be perfect for threading for future and data carriers
 * like dtos that's only job is to move data around, not modify it
 *
 * They also generate boilerplate code for you such as equals(), hashCode(), and toString()
 */