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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
