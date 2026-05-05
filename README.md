# Gym Tracker Server

A gym tracking app that allows users to create and log workouts for gym progression
-----------------------

## Project Status

This is currently an MVP (25–30 hours currently) to first focus on building correct structures and validating endpoints to ensure the flow of data 
is working correctly and the setup of the project was done correctly and according to industry standard before moving onto
more production ready features and implementations.

## Goals for MVP
- Ensure API structures are working with relevant documentation
- Flow of data between API and DB works correctly
- Clear layer: Controller → Service → Repo
- Add DTO mappings and use of DTO's as data carriers
- Set up foundation and initial Unit Tests

## Tech Stack
- Java 22
- Springboot 4
- PostgreSQL
- Hibernate and Spring JPA
- RESTful API
- Open API documentation
- MapStruct
- JUnit + MockMvc

## What's implemented

- Exercise + Program endpoints
- DTO + mapper pattern
- Global Exception handling
- Foundation test coverage for Services and Controller
- Test Builders for testing
- Spring Security with JWT


## To Still be implemented
- Container usage – Docker
- More robust Integration tests
- Introduction of Spring AI

## Reason for missing features and implementations
The goal was to first set up a foundation and ensure structures were in place in which everything can easily be 
implemented and integrated. The first goal was focusing on architecture before introducing production features

## Latest changes
- Added Spring Security configuration with JWT filter chain
- Implementation of UserPrincipal, SecurityService, JwtService
- Added AuthController and AuthService for login/register endpoints
- Implemented CustomAuthenticationEntryPoint and CustomAccessDeniedHandler
- Improved global exception handling for security exceptions
- Introduced profiles for development and production environments
- Added Flyway for DB migrations


## Next milestones
- Improve test depth
- Look into tools for deployment (Docker, Kafka and Kubernetes)
