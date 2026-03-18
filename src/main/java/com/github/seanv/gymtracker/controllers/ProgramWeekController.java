package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramWeekDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ProgramService;
import com.github.seanv.gymtracker.services.ProgramWeekService;
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

        return ResponseEntity.ok(service.getProgramWeek(programId, weekNumber));
    }

}
