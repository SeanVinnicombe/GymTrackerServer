package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.dto.ProgramsResponse;
import com.github.seanv.gymtracker.dto.UserDto;
import com.github.seanv.gymtracker.dto.update.UserUpdateDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "Update profile")
    @PatchMapping()
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserUpdateDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(dto));
    }

    @Operation(description = "Delete profile")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProfile(@PathVariable Long id){
        return null;
    }
}
