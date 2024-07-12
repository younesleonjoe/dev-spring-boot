package com.leon.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class MyCloudLogAsyncAspect {

    @Before("com.leon.aopdemo.aspect.LeonAopExpressions.forDaoPackage()")
    public void logToCloudAsync() {

        System.out.println("logToCloudAsync() method has been called");
    }
}
