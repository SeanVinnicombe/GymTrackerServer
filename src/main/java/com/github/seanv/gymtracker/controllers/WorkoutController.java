package com.github.seanv.gymtracker.controllers;

import com.github.seanv.gymtracker.services.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workouts")
@Tag(name = "Workout", description = "Workout related requests")
public class WorkoutController {

    private final WorkoutService service;

    @Autowired
    public WorkoutController(WorkoutService service){
        this.service = service;
    }


}
