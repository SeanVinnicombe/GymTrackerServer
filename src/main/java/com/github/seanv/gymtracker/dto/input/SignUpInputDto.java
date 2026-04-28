package com.github.seanv.gymtracker.dto.input;

import jakarta.validation.constraints.*;

public record SignUpInputDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 12) String password,
        @NotBlank String phoneNumber
) {
}
