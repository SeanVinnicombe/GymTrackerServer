package com.github.seanv.gymtracker.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"status\": 401, \"error\": \"UNAUTHORIZED\", \"message\": \"Invalid email or password\"}");
    }
}

/**
 *
 * So the reason why GlobalExceptionHanlder doesn't work when 'loadByUsername' throws an exception is because that method
 * gets executed internally by 'DaoAuthenticationProvider' during 'authenticationManger.authenticate()'. The problem is when
 *  a custom exception is thrown there, it doesn't propagate upwards, and this is a deliberate Spring design.
 *
 * DaoAuthenticationProvider internally catches the customException and then converts it to BadCredentialsException
 * before rethrowing it. It does this so invalid username and invalid password throw the same error messages otherwise
 * attackers can distinguish between them and know when they have the correct one. So custom exception in globalhanlder
 * will never get triggered as BadCredentialsExceptions is what propagates upwards instead.
 *
 * Exceptions that happen in the security chain never reach controller level, as that's where the GlobalExceptionHandler
 *  lives, and that's why none of the custom exceptions are never seen. This is because Spring Security handles the filter
 * chain exceptions separately through component AuthenticationEntryPoint. There we create CustomAuthenticationEntryPoint
 * to handle this and create custom exception responses for Exceptions that occur in the filter chain
 *
 *
 * We have to write directly to HttpServlet instead of using object like in GlobalExceptionHandler as this code runs
 * part of the filter chain, before Spring MVC's handling kicks in. There is no ResponseEntitys, message converters,
 * no SpringMVC machinery available. You have to write raw JSON to response yourself
 */