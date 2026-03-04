package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProgramDayExerciseRepository extends JpaRepository<ProgramDayExercise, Long> {

    List<ProgramDayExercise> findAllByProgramDay_Id( Long programDayId);
}
