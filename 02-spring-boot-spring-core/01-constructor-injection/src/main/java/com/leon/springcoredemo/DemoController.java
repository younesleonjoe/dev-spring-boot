package com.leon.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach coach;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }
}
