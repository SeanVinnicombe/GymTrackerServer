package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.ProgramDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramDayRepository extends JpaRepository<ProgramDay, Long> {

    List<ProgramDay> findAllByProgramWeek_Id(Long programWeekId);

}
