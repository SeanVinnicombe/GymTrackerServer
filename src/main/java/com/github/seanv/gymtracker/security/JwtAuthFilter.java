package com.github.seanv.gymtracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter { // extending class ensures single execution of this class per request


    private final JwtService jwtService;
    private final SecurityService securityService;
    private final AuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    public JwtAuthFilter(JwtService jwtService, SecurityService securityService, AuthenticationEntryPoint authenticationEntryPoint){
        this.jwtService = jwtService;
        this.securityService = securityService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     *Step by step process of doFilterInternal():
     *
     * 1. Extract the Authorization header from the request
     * 2.Check if it starts with "Bearer" - if not, skip and move on
     * 3.Extract the token (everything after "Bearer")
     * 4.Extract the username from the token using JwtService
     * 5.Check SecurityContext if it doesn't already have authenticated user
     * 6.Load the user from the DB using SecurityService
     * 7.Validate the token using JwtService
     * 8.If valid - create authenticated object and set it in SecurityContext
     * 9.Pass the request along to the filter
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            var token = header.substring(7);
            var username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var user = securityService.loadUserByUsername(username);
                var validToken = jwtService.isTokenValid(token, user);

                if (validToken) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    // set's additional info like audit logging and session tracking. Reason is for completeness. Spring expects it to be there and some components make use of that info
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        try{
            filterChain.doFilter(request, response);
        } catch (BadCredentialsException ex){
            authenticationEntryPoint.commence(request, response, ex);
        }
    }
}
