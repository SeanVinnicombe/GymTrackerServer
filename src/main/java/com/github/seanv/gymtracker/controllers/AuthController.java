package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.AuthResponse;
import com.github.seanv.gymtracker.dto.input.LoginInputDto;
import com.github.seanv.gymtracker.dto.input.SignUpInputDto;
import com.github.seanv.gymtracker.exception.model.ApiError;
import com.github.seanv.gymtracker.exception.type.UserAlreadyExistsException;
import com.github.seanv.gymtracker.services.AuthService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(description = "Authentication related requests", name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @Operation(description = "Registers a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "409", description = "User already exists",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody SignUpInputDto inputDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.saveUser(inputDto));
    }

    @Operation(description = "Logs in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Not a valid user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Something went wrong...",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginInputDto inputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(inputDto));
    }
}
