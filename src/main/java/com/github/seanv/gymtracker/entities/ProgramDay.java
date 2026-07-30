package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_day_seq_gen")
    @SequenceGenerator(name = "program_day_seq_gen", sequenceName = "program_day_seq", allocationSize = 1)
    private Long id;

    @Column(name = "muscle_group")
    private String muscleGroup;

    @Column(name = "day_order")
    private Integer dayOrder;

    @ManyToOne
    @JoinColumn(name = "program_week_id", nullable = false)
    @NotNull
    private ProgramWeek programWeek;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "programDay", cascade = CascadeType.ALL)
    private List<ProgramDayExercise> programDayExercises;

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
