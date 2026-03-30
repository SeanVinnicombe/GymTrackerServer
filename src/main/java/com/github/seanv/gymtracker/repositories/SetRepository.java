package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SetRepository extends JpaRepository<Set, Long> {

}
