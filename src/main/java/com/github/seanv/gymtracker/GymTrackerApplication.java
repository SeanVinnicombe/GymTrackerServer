package com.github.seanv.gymtracker;

import com.github.seanv.gymtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymTrackerApplication.class, args);


    }

}
