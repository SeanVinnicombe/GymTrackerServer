package com.github.seanv.gymtracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "program_week")
public class ProgramWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_week_seq_gen")
    @SequenceGenerator(name = "program_week_seq_gen", sequenceName = "program_week_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(name = "week_number")
    private Integer weekNumber;

    @OneToMany(mappedBy = "programWeek")
    private List<ProgramDay> programDays;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
