package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
import com.github.seanv.gymtracker.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    public List<Exercise> findAllExercisesByMuscleGroup(MuscleGroup muscleGroup);
    public Boolean existsByName(String name);
}
