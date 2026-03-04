package com.github.seanv.gymtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ExerciseResponse {

    List<ExerciseDto> exercises;
}
