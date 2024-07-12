package com.leon.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// We will make this component the default one if there exists multiple beans that implement the same interface and in the dependency injection no Qualifier is declared
@Primary
@Component
@Lazy
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice cricket for 15 minutes";
    }
}
