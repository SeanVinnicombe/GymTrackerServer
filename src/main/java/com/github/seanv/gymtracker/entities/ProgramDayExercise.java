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

    /**
     * Orphan removal always belong to parent - tells hibernate if item was removed from collection and has to link to a parent anymore
     * then remove from DB on my behalf
     */
    @OneToMany(mappedBy = "programDayExercise", cascade = CascadeType.ALL, orphanRemoval = true)
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

    /**
     * Helper methods which modification of exerciseSession collection - its important to modify a collection, not assign a new one
     * as Hibernate loses context
     *
     * In this case we are making sure when removing an Exercise session entity, we also remove it link to PDE entity by
     * assigning it null in the place of where parent entity used to be. This allows the Exercise session to be seen
     * as an orphan as it has no parent and therefore Hibernate can remove it from DB for us because even
     * if we remove it from list, that's only half the job, as we need to also severe the link to any parent reference
     * otherwise Hibernate doesn't pick it up as orphan and then keeps it in DB
     */

    public void removeExerciseSession(ExerciseSession es){
        exerciseSession.remove(es);
        es.setProgramDayExercise(null);
    }

    public void addExerciseSession(ExerciseSession es){
        exerciseSession.add(es);
        es.setProgramDayExercise(this);
    }
}
