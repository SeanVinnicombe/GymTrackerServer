package com.github.seanv.gymtracker.exception.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;

}
/** important to create model in which api will return data as it provides proper formating and grouping of
 * important information client side needs:
 *
 * example:
 * {
 *   "status": 404,
 *   "error": "NOT_FOUND",
 *   "message": "Exercise with id 10 was not found",
 *   "timestamp": "2026-03-04T10:15:30"
 * }
 */