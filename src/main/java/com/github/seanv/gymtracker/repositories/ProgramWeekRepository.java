package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ProgramWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramWeekRepository extends JpaRepository<ProgramWeek, Long> {

    ProgramWeek findByProgram_IdAndWeekNumber(Long programId, Integer weekNumber);
    List<ProgramWeek> findAllByProgram_Id(Long programId);
}
