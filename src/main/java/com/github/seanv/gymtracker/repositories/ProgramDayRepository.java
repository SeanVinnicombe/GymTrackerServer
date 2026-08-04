package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ProgramDay;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramDayRepository extends JpaRepository<ProgramDay, Long> {

    List<ProgramDay> findAllByProgramWeek_Id(Long programWeekId);

    @EntityGraph(attributePaths = "programDayExercises")
    Optional<ProgramDay> findById(@NonNull Long id);

}
