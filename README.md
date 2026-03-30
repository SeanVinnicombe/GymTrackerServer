# Gym Tracker Server

A gym tracking app which allows users to create and log workouts for gym progression
-----------------------

## Project Status

This is currently an MVP(15-20 hours currently) to first focus on building correct structures and validating endpoints to ensure flow of data 
is working correctly and the setup of project was done correctly and according to industry standard before moving onto
more production ready features and implementations

## Goals for MVP
- Ensure API structures are working with relevant documentation
- Flow of data between API's and DB works correctly
- Clear layer: Controller -> Service -> Repo
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


## To Still be implemented
- Authentication/Authorization with Spring Security
- Environments/profiles
- DB migrations - Flyway
- Container usage - Docker
- More robust Integration tests

## Reason for missing features and implementations
The goal was to first set up a foundation and ensure structures were in place in which everything can easily be 
implemented and integrated. The first goal was focusing on architecture before introducing production features

## Next milestones
- Introduction of auth and  secure endpoints
- Introducing profiles 
- Include DB migration
- Improve test depth
- Look into tools for deployment
