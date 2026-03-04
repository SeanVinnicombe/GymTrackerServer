package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise_session")
public class ExerciseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "target_reps")
    private Integer targetReps;

    @Column(name = "target_sets")
    private Integer targetSets;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    @NotNull
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @NotNull
    private Workout workout;

    @OneToMany(mappedBy = "exerciseSession")
    private List<Set> sets = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseSession that = (ExerciseSession) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
