package com.leon.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyApiAnalyticsAspect {

    @Before("com.leon.aopdemo.aspect.LeonAopExpressions.forDaoPackage()")
    public void performApiAnalytics() {

        System.out.println("performApiAnalytics() method has been called");
    }
}
