package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ExerciseSessionDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ExerciseSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Exercise Session", description = "Requests related to Exercise session")
@RestController
@RequestMapping("exercise-session")
public class ExerciseSessionController {

    private final ExerciseSessionService service;

    @Autowired
    public ExerciseSessionController(ExerciseSessionService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets Exercise Session by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise session details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseSessionDto.class))),
            @ApiResponse(responseCode = "404", description = "Exercise session does not exist for given id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),

    })
    public ResponseEntity<ExerciseSessionDto> getExerciseSession(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByExerciseSessionId(id));
    }

    @PostMapping()
    public ResponseEntity<ExerciseSessionDto> logExercisesSession(@RequestBody ExerciseSessionDto dto){
        return null;
    }


}
