package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ProgramDayExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program-day-exercises")
@Tag(name = "Program Day Exercise", description = "Program day exercises related requests")
public class ProgramDayExerciseController {

    private final ProgramDayExerciseService service;

    @Autowired
    public ProgramDayExerciseController(ProgramDayExerciseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets Exercise details for Exercise on a specific Program day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise details successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProgramDayExerciseDto.class))),
            @ApiResponse(responseCode = "404", description = "Exercise details for Program day does not exist for given id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),

    })
    public ResponseEntity<ProgramDayExerciseDto> getProgramDayExercise(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProgramDayExercise(id));
    }
}
