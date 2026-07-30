package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_session_seq_gen")
    @SequenceGenerator(name = "exercise_session_seq_gen", sequenceName = "exercise_session_seq", allocationSize = 1)
    private Long id;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "exerciseSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Set> sets = new ArrayList<>();

    @Column(name = "performed_at")
    private LocalDateTime performedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "program_day_exercise_id")
    private ProgramDayExercise programDayExercise ;

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
