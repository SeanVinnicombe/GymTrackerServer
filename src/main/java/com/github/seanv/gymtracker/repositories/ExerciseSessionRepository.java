package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ExerciseSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSessionRepository extends JpaRepository<ExerciseSession, Long> {
}
