package com.github.seanv.gymtracker.entities;

import com.github.seanv.gymtracker.entities.enums.MuscleGroup;
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
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;

    /**
     * @Enumerated tells application how db should use enums as enums exists in java, but not in db so we cant use
     * actual Enum value as db won't know what it is, that's why we use string version
     */

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

    public Exercise(String name, MuscleGroup muscleGroup){
        this.name = name;
        this.muscleGroup = muscleGroup;
    }
}
