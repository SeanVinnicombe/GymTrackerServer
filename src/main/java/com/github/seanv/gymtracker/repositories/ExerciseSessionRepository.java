package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ExerciseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseSessionRepository extends JpaRepository<ExerciseSession, Long> {

    Boolean existsByProgramDayExercise_Id(Long id);

    Optional<ExerciseSession> findByProgramDayExercise_IdAndWeekNumber(Long id, int weekNumber);

    /**
     * NB -> Use @Modyfying(JPA import otherwise it will still be treated as query with JDBC import)when deleting
     * otherwise Spring assumes @Query is a select query by default
     * <p>
     * Don't use JPQL Bulk queries " @Query("delete from...") as these ignore JPA relationships,
     * therefore Cascade and orphan removal is ignored - this is because DELETE and UPDATE are executed in the DB,
     * not in through Hibernates entity lifecycle - Use JPA
     * <p>
     * JPA:
     * 1. Loads entity into memory
     * 2. Tracks realtioonships - Very imporant in this scenario
     * 3.Applies rules and config(Cascade & orphanRemoval)
     * 4. Executes sql in correct order
     * <p>
     * Bulk operations:
     * 1. Basically just gives sql to db and tells it to execute, that's it
     */
    @Modifying
    void deleteByProgramDayExercise_IdAndWeekNumber(@Param("id") Long id, @Param("weekNumber") int weekNumber);

    Boolean existsByProgramDayExercise_IdAndWeekNumber(Long id, int weekNumber);
}
