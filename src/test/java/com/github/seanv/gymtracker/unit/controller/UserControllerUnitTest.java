package com.github.seanv.gymtracker.unit.controller;

import com.github.seanv.gymtracker.builders.entities.UserBuilder;
import com.github.seanv.gymtracker.controllers.UserController;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.exception.type.UserNotFoundException;
import com.github.seanv.gymtracker.security.JwtService;
import com.github.seanv.gymtracker.security.SecurityService;
import com.github.seanv.gymtracker.security.UserPrincipal;
import com.github.seanv.gymtracker.services.ProgramService;
import com.github.seanv.gymtracker.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private JwtService jwtService;

    @MockitoBean
    private SecurityService securityService;

    //TODO fix tests with now added Spring Security functionality

    @MockitoBean
    ProgramService programService;

    @BeforeEach
    void setUp() {

        UserPrincipal mockPrincipal = new UserPrincipal(UserBuilder.aUser().build());

        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(
                mockPrincipal,
                mockPrincipal.getPassword()
        );

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

//    @Test
//    void when_getting_programs_by_user_id_then_return_list_of_programs() throws Exception{
//
//
//
//        when(programService.getAllPrograms()).thenReturn(List.of(
//                new ProgramDto(1L),
//                new ProgramDto(2L))
//        );
//
//        mockMvc.perform(get("http://localhost:8087/users/1/programs"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.programs[1].id").value(2));
//    }
//
//    @Test
//    void when_invalid_user_supplied_return_correct_response() throws Exception {
//
//        when(programService.getAllPrograms()).thenThrow(
//                new UserNotFoundException(999L)
//        );
//
//        mockMvc.perform(get("http://localhost:8087/users/999/programs"))
//                .andExpect(status().isNotFound());
//    }
}
