package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.services.ProgramDayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/program-days")
@Tag(name = "All requests related to ProgramDay")
public class ProgramDayController {

    private final ProgramDayService service;

    public ProgramDayController(ProgramDayService service) {
        this.service = service;
    }


    /**
     *
     *Endpoints should never contain verbs, though the only exception is when referring to state such as "complete",
     * "activate"... This is known as state transition endpoints, and it is a recognized Rest pattern
     */
    @Operation(description = "Completing days workout")
    @PutMapping("/{programId}/{weekNumber}")
    public ResponseEntity<ProgramDayDto> completeProgramDay(@PathVariable String programId, @PathVariable String weekNumber){
        return null;
    }
}
