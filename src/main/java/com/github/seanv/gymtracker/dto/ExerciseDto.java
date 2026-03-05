package com.github.seanv.gymtracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDto {

    private Long id;
    private String name;
    private String muscleGroup;


    public ExerciseDto(String name, String muscleGroup){
        this.name = name;
        this.muscleGroup = muscleGroup;
    }
}
