package com.github.seanv.gymtracker.unit.services;

import com.github.seanv.gymtracker.builders.dtos.exercise.ExerciseDtoBuilder;
import com.github.seanv.gymtracker.builders.dtos.exercise.ExerciseInputDtoBuilder;
import com.github.seanv.gymtracker.builders.entities.ExerciseBuilder;
import com.github.seanv.gymtracker.dto.input.ExerciseInputDto;
import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
import com.github.seanv.gymtracker.dto.ExerciseDto;
import com.github.seanv.gymtracker.entities.Exercise;
import com.github.seanv.gymtracker.mappers.ExerciseMapper;
import com.github.seanv.gymtracker.repositories.ExerciseRepository;
import com.github.seanv.gymtracker.services.ExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceUnitTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private ExerciseMapper mapper;

    @InjectMocks
    private ExerciseService service;

    @Test
    void when_getting_exercises_by_muscle_return_valid_list(){

        Exercise one = ExerciseBuilder.aExercise().build();
        Exercise two = ExerciseBuilder.aExercise().withName("Flys").build();
        List<Exercise> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        ExerciseDto oneDto = ExerciseDtoBuilder.aExerciseDto().build();
        ExerciseDto twoDto = ExerciseDtoBuilder.aExerciseDto().withName("Flys").build();

        when(exerciseRepository.findAllExercisesByMuscleGroup(MuscleGroup.CHEST)).thenReturn(list);
        when(mapper.toDto(one)).thenReturn(oneDto);
        when(mapper.toDto(two)).thenReturn(twoDto);


        var result = service.getAllExercisesByMuscleGroup(MuscleGroup.CHEST);

        for (ExerciseDto dto : result){
            assertEquals(MuscleGroup.CHEST.getName(),dto.getMuscleGroup());
        }
    }

    @Test
    void when_creating_new_exercise_then_return_new_exercise(){

        ExerciseInputDto input = ExerciseInputDtoBuilder.aExerciseInputDto().build();
        Exercise exercise = ExerciseBuilder
                .aExercise()
                .withId(50L)
                .withName("Single arm curls")
                .withMuscleGroup(MuscleGroup.BICEPS)
                .build();

        ExerciseDto dto = ExerciseDtoBuilder
                .aExerciseDto()
                .withId(50L)
                .withName("Single arm biceps curls")
                .withMuscleGroup(MuscleGroup.BICEPS.getName())
                .build();

        when(exerciseRepository.save(exercise)).thenReturn(exercise);
        when(mapper.toDto(exercise)).thenReturn(dto);
        when(mapper.fromDto(input)).thenReturn(exercise);

        var result = service.addNewExercise(input);

        assertEquals(result.getId(), dto.getId());

    }
}
