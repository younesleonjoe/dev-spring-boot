package com.leon.aopdemo.aspect;

import com.leon.aopdemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    /*
        How to control the order in which advices are executed:
            Refactor: Place advices in separate Aspects
            Control order on Aspects using the @Order annotation
            Guarantees order of when Aspects are applied
     */

    /*
        NOTE:
        @Order annotation
            Lower numbers have higher precedence
                Range Integer.MIN_VALUE to integer.MAX_VALUE
                Negative numbers are allowed
                Does not have to be consecutive
            If multiple aspects have the same order, the order is non-deterministic
     */

    @Before("com.leon.aopdemo.aspect.LeonAopExpressions.forDaoPackageExcludeGetterSetterMethods()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        // Step 1 - Access and display Method Signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature + " method has been called");

        // Step 2 - Access and display Method Arguments
        Object[] args = joinPoint.getArgs();
        Iterator<Object> iterator = Arrays.asList(args).iterator();
        System.out.print("Arguments: ");
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (item instanceof Account) {
                System.out.print("{name=" + ((Account) item).getName() + ", level=" + ((Account) item).getLevel() + "}");
            } else {
                System.out.print(item);
            }
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    @AfterReturning(
            pointcut = "com.leon.aopdemo.aspect.LeonAopExpressions.aFindAccounts()",
            returning = "accounts"/* It can be any name such as result, returnValue instead of accounts */
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println(methodSignature + " method has been called");
        System.out.println(accounts);

        // modify "result" list: add, remove, update, etc...
        if (!accounts.isEmpty()) {

            Account account = accounts.getFirst();
            account.setName("Daffy Duck");
            /*
                NOTE:
                The development team should be aware of AOP being used in app,
                because they will think it is broken because every first account has a name of Daffy Duck
             */
        }
    }

    @AfterThrowing(
            pointcut = "com.leon.aopdemo.aspect.LeonAopExpressions.aFindAccounts()",
            throwing = "exception"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {

        System.out.println("Executing @AfterThrowing advice");
        // Log the exception
        System.out.println(exception);
        // At this point we can only intercept the exception (read it)
        // However, the exception is still propagated to calling program
        // If you want to stop the exception from propagating to the MainApp
        // then use @Around advice
    }

    @After("com.leon.aopdemo.aspect.LeonAopExpressions.aFindAccounts()")
    public void afterFinallyFindAccountsAdvice() {
        /*
            After runs either there is a success or an exception
            And we cannot access the exception in it in case there is one, instead use @AfterThrowing advice
            Does not depend on the happy path or an exception
            Logging / auditing is the easiest case here
         */

        System.out.println("Executing @After (finally) advice");
    }

    /*
        @Around: Like a combination of @Before and @After
        But gives you more fine-grained control

        Most common: logging, auditing, security
        Pre-processing and post-processing data
        Instrumentation / profiling code
            How long does it take for a section of code to run?

        For an exception thrown from proceeding join point
        You can handle / swallow / stop the exception
        Or you can simply rethrow the exception

        This gives you fine-grained control over how the target method is called
     */
    @Around("execution(* com.leon.aopdemo.service.*.getFortune(..))")
    public Object afterGetFortune(
            ProceedingJoinPoint proceedingJoinPoint
    ) throws Throwable {

        // get begin timestamp
        long begin = System.currentTimeMillis();

        Object result = null;
        try {
            // now, let's execute the method
            result = proceedingJoinPoint.proceed();
        } catch (Exception exception) {
            // log the exception
            System.out.println("@Around advice: We have a problem " + exception);

            // handle and give default fortune ... use this approach with caution
            // If it is a small problem handle it,
            // but if it is a severe problem alert the manager to address the root cause
            // alert who ever is calling or asking for it
            // then if you are calling a code that throws an exception every time, then that's a code smell
            // that is a problem that you need to address it
            // You shouldn't hide every problem in the system

            // below we stoped the exception
            result = "Nothing exciting here. Move along!";

            // Or we can rethrow the exception as done below
            // And leave to the calling program how to handle the exception
            throw exception;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("Duration: " + (duration / 1000) + "seconds");

        return result;
    }
}
