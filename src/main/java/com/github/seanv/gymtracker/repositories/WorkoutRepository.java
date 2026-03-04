package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
