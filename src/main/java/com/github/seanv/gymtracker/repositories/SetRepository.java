package com.github.seanv.gymtracker.repositories;

import com.github.seanv.gymtracker.entities.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<Set, Long> {
}
