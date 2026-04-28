package com.github.seanv.gymtracker.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter,
                          CustomAuthenticationEntryPoint authenticationEntryPoint,
                          CustomAccessDeniedHandler accessDeniedHandler){
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }


    /**
     *UsernamePasswordAuthenticationFilter - This is Spring's built in handler for form based login. It does:
     * 1.Watches for POST/login requests specifically
     * 2. Extracts username and password from request body
     * 3.Calls authenticationManager.authenticate() with those credentials
     * 4. If successful - stores authenticated user in the SecurityContext
     *
     * Though in our case, we are not using form based login, but stateless JWT API and handling login ourselves.
     * So UsernamePasswordAuthenticationFilter isn't actually doing anything useful, it's just in the chain by default
     *
     * Reason for slotting JwtAuthFilter before is about ordering - we need to our filter to run early enough to populate
     * the SecurityContext before any of Spring's filters try to make authorization decisions based on it.
     *
     * Example of chain queue:
     *
     * Request
     *    ↓
     * [ JwtAuthFilter ]         ← our filter — populates SecurityContext
     *    ↓
     * [ UsernamePasswordAuthenticationFilter ]  ← Spring's filter — sees SecurityContext already populated, moves on
     *    ↓
     * [ AuthorizationFilter ]   ← checks SecurityContext against our rules (anyRequest().authenticated())
     *    ↓
     * Controller
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/auth/**").permitAll() // -> "auth" endpoints are public
                        .requestMatchers("/admin/**").hasRole("ADMIN") //"admin" endpoints are private
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .anyRequest().authenticated() //every other request needs to be logged in
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // this method means - build my filter before Spring's built in 'UsernamePasswordAuthenticationFilter'
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // REST is stateless, we're not using
                                                                    // sessions but token

        return http.build();
    }

    /**
     * AuthenticationEntryPoint - This is a Spring interface that defines how to handle unauthorized requests.
     * We can't use custom exceptions as the authentication process happens before the controller is even called,
     * and happens in the filter chain, which Spring handles seperately. So we create custom version of interface so we
     * can have control of how errors are disaplyed
     *
     * AccessDeniedHandler - This is a Spring interface that defines how to handle unauthorized requests. It works the
     * same as the mentioend above, but for access denied.
     *
     * .exceptionHandling() - this is our way of now providing spring with our custom exception handlers for
     * exceptions that occur in the filter chain
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
