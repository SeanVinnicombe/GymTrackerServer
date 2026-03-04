package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.ProgramsResponse;
import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.entities.User;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.services.ProgramService;
import com.github.seanv.gymtracker.services.UserService;
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

import java.util.List;

@RestController
@Tag(name = "User", description = "User related requests")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ProgramService programService;

    @Autowired
    public UserController(UserService userService, ProgramService programService) {

        this.userService = userService;
        this.programService = programService;
    }

    @Operation(description = "Find all Programs by specific User Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Programs found for specific User",
                    content = @Content(mediaType = "application.json",
                            schema = @Schema(implementation = ProgramsResponse.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application.json",
                            schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}/programs")
    public ResponseEntity<ProgramsResponse> getProgramsByUser(@PathVariable Long id) {
        List<ProgramDto> programs = programService.getAllProgramsByUserId(id);
        var response = new ProgramsResponse(programs);
        return ResponseEntity.ok(response);
    }
}
