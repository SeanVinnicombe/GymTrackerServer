package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.dto.input.ProgramWeekInputDto;
import com.github.seanv.gymtracker.dto.update.ProgramWeekUpdateDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ProgramWeekService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Program week", description = "Request related to Program week")
@RequestMapping("/program-weeks")
public class ProgramWeekController {

    private final ProgramWeekService service;

    @Autowired
    public ProgramWeekController(ProgramWeekService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program week found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProgramWeekDto.class))),
            @ApiResponse(responseCode = "404", description = "Program week not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...", content = @Content(mediaType = "Application.json", schema = @Schema(implementation = ApiError.class)))
    })
    @Operation(description = "Getting Program week by Program Id and week number")
    @GetMapping("/{programId}/week/{weekNumber}")
    public ResponseEntity<ProgramWeekDto> getProgramWeek(@PathVariable("programId") Long programId,
                                                         @PathVariable("weekNumber") Integer weekNumber) {

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getProgramWeek(programId, weekNumber));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program week successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProgramWeekDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ApiError.class)))
    })
    @Operation(description = "Request responsible for updating specific program week")
    @PutMapping("{programWeekId}/log")
    public ResponseEntity<ProgramWeekDto> updateProgramWeek(
            @PathVariable("programWeekId") Long programWeekId,
            @RequestBody @Valid ProgramWeekUpdateDto updateDto)
    {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateProgramWeek(programWeekId, updateDto));

    }

}
