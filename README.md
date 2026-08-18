# Gym Tracker Server

A gym tracking app that allows users to create and log workouts for gym progression
-----------------------

## Project Status

This is currently an MVP (~ 60 hours currently) to first focus on building correct structures and validating endpoints to ensure the flow of data 
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
- Docker
- Kafka

## What's implemented

- Exercise + Program endpoints
- DTO + mapper pattern
- Global Exception handling
- Foundation test coverage for Services and Controller
- Test Builders for testing
- Spring Security with JWT
- Containerization
- Source control
- CI

## To Still be implemented
- Kubernetes
- Cloud Deployment
- More robust Integration tests
- Introduction of Spring AI

## Latest changes
- Added Spring Security configuration with JWT filter chain
- Implementation of UserPrincipal, SecurityService, JwtService
- Added AuthController and AuthService for login/register endpoints
- Implemented CustomAuthenticationEntryPoint and CustomAccessDeniedHandler
- Improved global exception handling for security exceptions
- Introduced profiles for development and production environments
- Added Flyway for DB migrations
- Introduction of containerization via Docker for deployment
- Health checks via Actuator when starting up containers
- Setup version control with CI
- Environment profiles
- Testing using containers

## Next milestones
- Improve test depth
- Look into tools for deployment (Docker, Kafka and Kubernetes)

### Docker

Pull and run application from Docker Hub:

```bash
docker pull seanvinni/gymtracker:1.0.0
```

or run the full stack with Docker Compose:

```bash
docker-compose up
```

**Docker Hub:** https://hub.docker.com/r/seanvinni/gymtracker
