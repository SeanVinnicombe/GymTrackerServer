package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ExerciseSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSessionRepository extends JpaRepository<ExerciseSession, Long> {

    Boolean existsByProgramDayExercise_Id(Long id);

    ExerciseSession findByProgramDayExercise_Id(Long id);
    void deleteByProgramDayExercise_Id(Long id);
}
