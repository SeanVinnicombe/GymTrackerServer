package com.github.seanv.gymtracker.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public interface TestContainerConfig {

    @Container
    @ServiceConnection // handles auto config, and extracts config details like name and password helping to also reduce boilerplate
    PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
}
