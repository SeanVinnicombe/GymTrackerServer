package com.github.seanv.gymtracker.unit.services;

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

        Exercise one = new Exercise("Bench", MuscleGroup.CHEST);
        Exercise two = new Exercise("Flys", MuscleGroup.CHEST);
        List<Exercise> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        ExerciseDto oneDto = new ExerciseDto("Bench", MuscleGroup.CHEST.getName());
        ExerciseDto twoDto = new ExerciseDto("Flys", MuscleGroup.CHEST.getName());

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

        ExerciseInputDto input = new ExerciseInputDto("Single Arm curls", "CHEST");
        ExerciseDto output = new ExerciseDto("Single Arm curls", "CHEST");


    }
}
