package com.github.seanv.gymtracker.dto;

import java.util.List;

public class ProgramWeekDto {

    private Long id;
    private Long programId;
    private Integer weekNumber;
    private List<ProgramDayDto> programDays;
}
