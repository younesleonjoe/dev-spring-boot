package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach coach;

    // If we have multiple dependencies that implements coach we will need to specify which one using Qualifier
    
    // Example For Constructor Injection
    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach coach) {
        this.coach = coach;
    }

    // Example for Setter Injection
    @Autowired
    public void setCoach(@Qualifier("tennisCoach") Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }
}
