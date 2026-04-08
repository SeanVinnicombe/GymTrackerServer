package com.github.seanv.gymtracker.entities;

import com.github.seanv.gymtracker.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
