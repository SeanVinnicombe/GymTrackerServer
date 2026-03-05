package com.github.seanv.gymtracker.unit.controller;

import com.github.seanv.gymtracker.controllers.ProgramController;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.services.ProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@WebMvcTest(ProgramController.class)
public class ProgramControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProgramService programService;

    @Test
    void when_getting_program_by_id_then_return_program_dto() throws Exception {

        ProgramDto program = new ProgramDto(1L);

        when(programService.getProgram(1L)).thenReturn(program);

        mockMvc.perform(get("http://localhost:8087/programs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

    }
}
