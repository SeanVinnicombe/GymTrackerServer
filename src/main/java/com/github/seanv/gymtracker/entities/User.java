package com.github.seanv.gymtracker.entities;

import com.github.seanv.gymtracker.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @NotBlank
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Program> programs = new HashSet<>();

    @Override
    public boolean equals(Object that) {
        if (that == null || getClass() != that.getClass()) return false;

        User user = (User) that;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
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