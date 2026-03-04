package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Program;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p " +
            "join fetch p.programDays "
            )
    Optional<Program> findByIdWithProgramDays(@Param("id") Long id);

    /**
     * Entity graph is used to reduce boilerplate and automatically assigning join fetch to query, which
     * ensures all relationships tied to entity are called now in one query and overriding lazy load
     * **/
    @EntityGraph(attributePaths = "programDays")
    Optional<Program> findById(@Param("id") Long id);
}
