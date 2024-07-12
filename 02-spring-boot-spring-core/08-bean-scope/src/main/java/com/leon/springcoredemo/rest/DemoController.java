package com.leon.springcoredemo.rest;

import com.leon.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Bean Scope
    // Refers to the lifecycle of a bean
    // How long does the bean live?
    // How many instances are created?
    // How is the bean shared?

    // The default scope is singleton

    // What is singleton?
    // Spring container creates only one instance of the bean, by default.
    // It is cached in memory
    // All dependency injections for the bean will reference the same bean

    // We can explicitly specify bean scope using the @Scope on top of @Component
    // example: @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)

    // Singleton - Create a single shared instance of the bean. Default scope.
    // Prototype - Creates a new bean instance for each container request.
    // Request - Scoped to an HTTP web request. Only used for web apps.
    // Session - Scoped to an HTTP web session. Only used for web apps.
    // Global-Session - Scoped to a global HTTP web session. Only used for web apps.

    // Prototype scope: new object instance for each injection
    // implemented using @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private Coach coach;
    private Coach anotherCoach;

    // Example For Constructor Injection
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach coach,
            @Qualifier("cricketCoach") Coach anotherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/")
    public String getDailyWorkout() {
        return this.coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: coach == anotherCoach, " + (this.coach == this.anotherCoach);
    }
}
