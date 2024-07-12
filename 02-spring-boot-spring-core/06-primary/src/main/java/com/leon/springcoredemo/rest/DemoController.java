package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach coach;

    // Primary is used as an annotation on top of the Component annotation to specify if there is no Qualifier in the dependency injection we will use this default one
    // And each component that implements the same interface only one of them should have the Primary annotation
    
    // Example For Constructor Injection
    @Autowired
    public DemoController(Coach coach) {
        this.coach = coach;
    }

    // Example for Setter Injection
    @Autowired
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }
}
