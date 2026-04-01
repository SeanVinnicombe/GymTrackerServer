package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.ProgramsResponse;
import com.github.seanv.gymtracker.dto.input.ProgramInputDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/programs")
@Tag(name = "Program", description = "Program related requests")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    private ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @Operation(description = "Find Program by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Program found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgramDto.class))}),
            @ApiResponse(responseCode = "404", description = "Program not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Invalid Id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),

    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> getProgram(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgram(id));
    }

    @Operation(description = "Get all Programs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All programs found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProgramDto.class))}),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))})
    })
    @GetMapping()
    public ResponseEntity<ProgramsResponse> getAllPrograms() {
        var list = programService.getAllPrograms();
        var response = new ProgramsResponse(list);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Creating new Program")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Program successfully created",
                    content = @Content(mediaType = "application.json",
                            schema = @Schema(implementation = ProgramDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input was provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping()
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramInputDto inputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programService.createProgram(inputDto));
    }


}

/**
 * @Tag is used to describe domain and should always belong to controller, not specific methods.
 * Swagger groups endpoints by Tag name, not by controller - thus all endpoints in the Controller will
 * now appear under "Program" Tag
 * <p>
 * For individual methods it's best to use @Operation, which just describes what that specific endpoint is
 * responsible for.
 */


/**
 * @Content is used for Content Negotiation ( send data back in desired format like JSON, XML,...) and @Schema
 * is used to provide structure in which the ResponseBody should look when sending back the response
 */
