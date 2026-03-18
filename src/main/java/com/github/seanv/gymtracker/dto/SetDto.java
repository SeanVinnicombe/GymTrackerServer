package com.github.seanv.gymtracker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetDto {

    private Long id;
    private Integer setOrder;
    private Integer weightDone;
    private Integer achievedReps;
}
