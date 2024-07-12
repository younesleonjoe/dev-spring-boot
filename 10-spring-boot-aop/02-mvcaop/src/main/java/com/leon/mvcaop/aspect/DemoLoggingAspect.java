package com.leon.mvcaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    /*
	Development Process:
	1 - Add Spring Boot AOP Starter to Maven pom file
	2 - Create Aspect
		1 - Add logging support
		2 - Setup pointcut declarations
		3 - Add @Before advice
		4 - Add @AfterReturning advice

	Only match expression for Controller, DAO, and service, exclude Entity
 */

    // setup logger
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.leon.mvcaop.controller.*.*(..))")
    public void pointcutController() {}

    @Pointcut("execution(* com.leon.mvcaop.dao.*.*(..))")
    public void pointcutAop() {}

    @Pointcut("execution(* com.leon.mvcaop.service.*.*(..))")
    public void pointcutService() {}

    @Pointcut("pointcutController() || pointcutAop() || pointcutService()")
    public void pointcutApp() {}

    @Before("pointcutApp()")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // display method we are calling
        logger.info("=====> in @Before: calling method: " + methodSignature.toShortString());

        // display the arguments to the method
        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for (Object arg : args) {
            logger.info("=====> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "pointcutApp()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display the method we are returning from
        logger.info("=====> in @AfterReturning: calling method: " + joinPoint.getSignature().toShortString());

        // display the data returned
        logger.info("=====> result: " + result);
    }
}
