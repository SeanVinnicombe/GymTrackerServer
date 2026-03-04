package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
