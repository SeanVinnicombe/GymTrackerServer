package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    @NotNull
    private Program program;

    @OneToMany(mappedBy = "workout",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ExerciseSession> exerciseSessions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "program_day_id", nullable = false)
    @NotNull
    private ProgramDay programDay;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(id, workout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

/** So hashcode represents the hex number, which can be seen almost as the memory address of an object and that number
 * is used when comparing 2 object to each other to determine whether they are in fact equal as it is comparing
 *  the memory addresses
 *
 *  equals() is method that is now used to compare the inner contents of 2 objects and determine if they are equal in
 *  that regard.
 *
 *  Both methods are inherited from superclass Object, since all classes implement it and if not overridden,
 *  those implementations will be used. In superclass, equals() uses "==" and not inner field or value comparisons
 *  so in that implementation it is comparing memery addresses.
 *
 *  hashCode() must never include a lazy collection, as when trying to generatehashCode(), it could touch a collection
 *  that's still busy populating and therefore incomplete - you wouldn't move a family into a incomplete house.
 *  They should aslo never rely on mutable fields, that's why id is the only neccessary value as its unique and immutable
 */