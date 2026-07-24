package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Program;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {


    /**
     * Entity graph is used to reduce boilerplate and automatically assigning join fetch to query, which
     * ensures all relationships tied to entity are called now in one query and overriding lazy load
     * **/
    List<Program> getAllProgramsByUser_Id(Long userId);

    @Query(
            """
                select distinct p from Program p 
                left join p.programWeeks
                where p.id = :id
                            
            """
    )
    Optional<Program> findByIdWithProgramWeeks(@Param("id")Long id);

    Optional<Program> findProgramByUser_IdAndStatus_Active(Long id);
}
