package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private ProgramService programService;

    @Autowired
    private ProgramController(ProgramService programService){
        this.programService = programService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> getProgram(@PathVariable Long id){
        return ResponseEntity.ok(programService.getProgram(id));
    }
}
