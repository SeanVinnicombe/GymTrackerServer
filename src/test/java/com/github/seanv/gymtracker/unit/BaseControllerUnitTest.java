package com.github.seanv.gymtracker.unit;

import com.github.seanv.gymtracker.security.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class BaseControllerUnitTest {

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private SecurityService securityService;

    @MockitoBean
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @MockitoBean
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    private  SecurityConfig securityConfig;
}
