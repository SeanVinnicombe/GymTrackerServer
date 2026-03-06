package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ExerciseDto;
import com.github.seanv.gymtracker.dto.input.ExerciseInputDto;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
import com.github.seanv.gymtracker.dto.ExerciseResponse;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Exercise", description = "Exercise related requests")
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    private ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Operation(description = "Get all muscles for certain Muscle Group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercises for Muscle Group found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping("/muscleGroup/{muscleGroup}")
    public ResponseEntity<ExerciseResponse> getExercisesByMuscleGroup(@PathVariable("muscleGroup") MuscleGroup muscleGroup) {
        var list = exerciseService.getAllExercisesByMuscleGroup(muscleGroup);
        var response = new ExerciseResponse(list);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Creating new Exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise successfully created", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExerciseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input was provided", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Exercise already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
    })
    @PostMapping("/add")
    public ResponseEntity<ExerciseDto> addNewExercise(@Valid @org.springframework.web.bind.annotation.RequestBody
          @RequestBody(
                  description = "Exercise to add",
                  required = true,
                  content = @Content(schema = @Schema(implementation = ExerciseInputDto.class))
          ) ExerciseInputDto exercise) {

        var result = exerciseService.addNewExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    //TODO look into using @RequestBody for documentation on how incoming data should look when creating
}
