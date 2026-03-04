package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "muscle_group")
    private String muscleGroup;

    @ManyToMany(mappedBy = "exercises")
    private Set<ProgramDay> programDays = new HashSet<>();

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseSession> exerciseSession = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ProgramDayExercise> programDayExercise;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
