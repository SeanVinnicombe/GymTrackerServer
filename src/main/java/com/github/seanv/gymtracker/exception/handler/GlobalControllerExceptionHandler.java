package com.github.seanv.gymtracker.exception.handler;

import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.exception.type.ExerciseNotFoundException;
import com.github.seanv.gymtracker.exception.type.ProgramNotFoundException;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    // ==================== GENERAL ====================

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleGeneralException(Exception ex){
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Something went wrong...",
                LocalDateTime.now()
        );
    }

    // ==================== PROGRAM ====================

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFound(UserNotFoundException ex){
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    // ==================== PROGRAM ====================

    @ExceptionHandler(ProgramNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleProgramNotFound(ProgramNotFoundException ex){
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );
    }


    // ==================== EXERCISE ====================

    @ExceptionHandler(ExerciseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleExerciseNotFound(ExerciseNotFoundException ex){

        return new ApiError(
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}
