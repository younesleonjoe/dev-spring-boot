package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /*
    You can add custom code during bean initialization
        Calling custom business logic methods.
        Setting up handles to resources (db, sockets, file etc.)
    You can add custom code during bean destruction
        Calling custom business logic methods.
        Clean up handles to resources (db, sockets, file etc.)
    Check CricketCoach for further implementation

    For Prototype scoped bean spring does not call the destroy method.
    Spring does not manage the lifecycle of prototype bean.
    The container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

    Lifecycle method is called on all objects regardless of scope.

    In the case of prototype bean, the client code must clean up and release expensive resources that the prototype bean is holding

    Prototype bean is lazy initialized by default. No need to put @Lazy annotation on a prototype bean.
     */


    private final Coach coach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach coach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }
}
