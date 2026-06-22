package com.github.seanv.gymtracker.events;

import java.time.LocalDateTime;

public record UserRegisteredEvent(
        Long id,
        String email,
        String firstName,
        LocalDateTime registeredAt
) {
}
