package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.NotBlank;

public record LoginInputDto(@NotBlank String email, @NotBlank String password) {
}
