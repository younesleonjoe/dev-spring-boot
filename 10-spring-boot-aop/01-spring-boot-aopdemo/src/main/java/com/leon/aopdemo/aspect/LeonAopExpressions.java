package com.leon.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
    NOTE:
    If you only have pointcuts the @Aspect is optional
    Only required if you add advices in this class:
        @Before, @After etc
 */

public class LeonAopExpressions {

        /*
        How to apply multiple pointcut expressions to a single advice:
        Execute an advice only if certain conditions are met
        Example: All methods in a package EXCEPT getter/setter methods

        Combine pointcut expressions using logic operators AND(&&) OR(||) NOT(!)
        Example:
        @Before("expressionOne() && expressionTwo()")
        @Before("expressionOne() || expressionTwo()")
        @Before("expressionOne() && !expressionTwo()")
        @Before("expressionOne() || !expressionTwo()")
        @Before("!expressionOne()")

     */

    // create pointcut for getter methods
    @Pointcut("execution(* com.leon.aopdemo.dao.*.get*(..))")
//    private void getter() {} if used in its own class it should be private
//    but here it is used by other classes than it should be public
    public void getter() {}

    // create pointcut for setter methods
    @Pointcut("execution(* com.leon.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // Solved our problem of reusing pointcut expressions across multiple advices
    // Create a pointcut declaration once and apply it to multiple advices
    @Pointcut("execution(* com.leon.aopdemo.dao.*.*(..))")
    public void forAllDaoPackage() {}

    @Pointcut("forAllDaoPackage() && !aFindAccounts()")
    public void forDaoPackage() {}

    // Combine pointcut: include package ... exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter() || aFindAccounts())")
    public void forDaoPackageExcludeGetterSetterMethods() {}

    @Pointcut("execution(* com.leon.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void aFindAccounts() {}
}
