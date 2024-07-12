package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach coach;

    // This method is called Constructor Injection
    // It is used when we have required dependencies
    // This is recommended by the spring.io development team as a first choice
    // define a constructor for dependency injection
    /*
    @Autowired
    public DemoController(Coach coach) {
        this.coach = coach;
    }
    */

    // This method is called Setter Injection
    // It is used when we have optional
    // It is recommended by the spring.io development team only for optional dependencies
    // define a setter for dependency injection
    // @Autowired is used for Inversion of control
    @Autowired
    private void setCoach(Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }
}
