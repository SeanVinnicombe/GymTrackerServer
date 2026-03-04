package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program_day")
public class ProgramDay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "muscle_group")
    private String muscleGroup;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    @NotNull
    private Program program;

    @OneToMany(mappedBy = "programDay")
    private Set<Workout> workouts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "program_day_exercise",
            joinColumns = @JoinColumn(name = "program_day_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises = new HashSet<>();

    @OneToMany(mappedBy = "programDay")
    private Set<ProgramDayExercise> programDayExercises;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgramDay that = (ProgramDay) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
