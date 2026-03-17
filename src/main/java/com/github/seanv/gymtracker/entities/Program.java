package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_seq_gen")
    @SequenceGenerator(
            name = "program_seq_gen",
            sequenceName = "program_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "program_length")
    private Integer programLength;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<ProgramWeek> programWeeks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Objects.equals(id, program.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Program(Long id){
        this.id = id;
    }

    public Program(Long id, User user){
        this.id = id;
        this.user = user;
    }
}
