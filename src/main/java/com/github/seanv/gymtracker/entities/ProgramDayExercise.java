package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "program_day_exercise")
public class ProgramDayExercise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "target_reps")
    private Integer targetReps;

    @Column(name = "target_sets")
    private Integer targetSets;

    @ManyToOne
    @JoinColumn(name = "program_day_id", nullable = false)
    @NotNull
    private ProgramDay programDay;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    @NotNull
    private Exercise exercise;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgramDayExercise that = (ProgramDayExercise) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
