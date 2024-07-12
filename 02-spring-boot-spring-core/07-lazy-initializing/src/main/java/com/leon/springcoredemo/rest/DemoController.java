package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Lazy Initialisation is done when we do not need to initialize a bean upfront instead we initialize when:
    // 1 - It is needed for dependency injection
    // 2 - Or it is explicitly requested

    // We simply need to add @Lazy annotation for a class

    // instead of adding @Lazy to each bean we can globally configure in application.properties
    // check application.properties

    // Lazy Initialization Advantages
    // 1 - Only create object as needed
    // 2 - May help with faster startup time if you have a large number of components

    // Lazy Initialization Disadvantages
    // 1 - If you have web related components like @RestController, not created until requested
    // 2 - May not discover configuration issues until to late
    // 3 - Need to make sure you have enough memory for all beans once created

    // The cons outweigh the pros this is why it is configured false by default
    // You should profile your application before configuring lazy initialization

    // Avoid the common pitfall of premature optimization

    private Coach coach;

    // Example For Constructor Injection
    @Autowired
    public DemoController(Coach coach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
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
