package com.github.seanv.gymtracker.unit.controller;

import com.github.seanv.gymtracker.controllers.UserController;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import com.github.seanv.gymtracker.services.ProgramService;
import com.github.seanv.gymtracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    ProgramService programService;

    @Test
    void when_getting_programs_by_user_id_then_return_list_of_programs() throws Exception{

        when(programService.getAllProgramsByUserId(1L)).thenReturn(List.of(
                new ProgramDto(1L),
                new ProgramDto(2L))
        );

        mockMvc.perform(get("http://localhost:8087/users/1/programs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programs[1].id").value(2));
    }

    @Test
    void when_invalid_user_supplied_return_correct_response() throws Exception {

        when(programService.getAllProgramsByUserId(999L)).thenThrow(
                new UserNotFoundException(999L)
        );

        mockMvc.perform(get("http://localhost:8087/users/999/programs"))
                .andExpect(status().isNotFound());
    }
}
