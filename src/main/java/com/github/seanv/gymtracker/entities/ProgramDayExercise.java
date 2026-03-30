package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "program_day_exercise")
public class ProgramDayExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_day_exercise_seq")
    @SequenceGenerator(
            name = "program_day_exercise_seq",
            sequenceName = "program_day_exercise_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "exercise_number")
    private Integer exerciseNumber;

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

    @OneToMany(mappedBy = "programDayExercise", cascade = CascadeType.ALL)
    private List<ExerciseSession> exerciseSession;

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
