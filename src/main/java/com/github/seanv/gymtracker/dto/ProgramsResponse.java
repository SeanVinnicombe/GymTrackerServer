package com.github.seanv.gymtracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProgramsResponse {
    private List<ProgramDto> programs;
}


/** important to have getters in models that are used in APIs as Jackson relies on getter methods
 * to serialize data from java -> JSON
 */