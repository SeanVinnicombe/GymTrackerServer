package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "set")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "set_order")
    private Integer setOrder;

    @Column(name = "achieved_resp")
    private Integer achievedReps;

    @Column(name = "weight_done")
    private Integer weightDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_session_id", nullable = false)
    @NotNull
    private ExerciseSession exerciseSession;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return Objects.equals(id, set.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
