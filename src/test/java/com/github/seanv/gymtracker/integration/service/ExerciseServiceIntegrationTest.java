package com.github.seanv.gymtracker.integration.service;

import com.github.seanv.gymtracker.config.TestContainerConfig;
import com.github.seanv.gymtracker.dto.ExerciseDto;
import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
import com.github.seanv.gymtracker.repositories.ExerciseRepository;
import com.github.seanv.gymtracker.services.ExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ImportTestcontainers(TestContainerConfig.class) // import shared testcontainer, don't spin up new container for each test
@ActiveProfiles("test")
public class ExerciseServiceIntegrationTest {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Test
    void should_return_all_exercises_by_muscle_group(){

        List<ExerciseDto> exercises = exerciseService.getAllExercisesByMuscleGroup(MuscleGroup.CHEST);
        boolean test = exercises.stream().anyMatch(i -> !i.getMuscleGroup().equals(MuscleGroup.CHEST.getName()));

        assertTrue(test);
    }
}
