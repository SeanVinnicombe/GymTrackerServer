package com.github.seanv.gymtracker.unit.controller;

import com.github.seanv.gymtracker.controllers.ProgramController;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.services.ProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@WebMvcTest(ProgramController.class)
@AutoConfigureMockMvc(addFilters = false)
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

    @Test
    void when_getting_all_programs_return_list_of_dtos() throws Exception {

        when(programService.getAllPrograms()).thenReturn(List.of(new ProgramDto(1L), new ProgramDto(2L)));

        mockMvc.perform(get("http://localhost:8087/programs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programs[1].id").value(2));

    }
}

/** @AutoConfigure allows you to disable Spring security as it's still used during unit tests
 *
 * "$" refers to the root object in JSON response
 *
 * @WebMvcTest starts up a minimal spring context/container with only the web("Controller") layer - no other
 * layers are present on start of test as Controllers only test Request -> Controller -> Response
 *
 * @MockMvc simulates http requests without starting real server like tomcat and running "localhost:8087/programs".
 * It pretends to be server asking for requests
 *
 * @MockitoBean creates spring bean and injects that bean into the Spring container - Spring then handles the injection
 * where in Unit tests with mocking, Mockito does that
 * */
