package com.leon.aopdemo;

import com.leon.aopdemo.dao.AccountDAO;
import com.leon.aopdemo.dao.MembershipDAO;
import com.leon.aopdemo.entity.Account;
import com.leon.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner  commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		return runner -> {
//			demoTheBeforeAdvice(accountDAO, membershipDAO);
			demoAroundAdvice(trafficFortuneService);
		};
	}

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {

		try {
			System.out.println(trafficFortuneService.getFortune(true));
		}
		catch (Exception exception) {
			System.out.println("MainApp caught exception: " + exception);
			System.out.println("Done!");
		}
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		// call the business method
		accountDAO.addAccount(new Account(), true);
		try {
			System.out.println(accountDAO.findAccounts(false));
		} catch (Exception exception) {
			System.out.println("MainApp caught exception: " + exception);
			System.out.println("Done!");
		}

//		membershipDAO.addAccount();
//		accountDAO.addSillyMethod();
//		accountDAO.addSillyBooleanMethod();
	}
}

/*
	NOTE:
	*****
	* Keep the code small
	* Keep the code fast
	* Do not perform any expensive / slow operations
	* Get in and out as Quickly as possible
 */

/*
	Pointcut: A predicate expression for where advice should be applied.

	Spring AOP uses the AspectJ pointcut expression language.

	Execution pointcuts (applies to the execution of a given method)

	execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)

	modifiers-pattern: Spring AOP only supports public
	return-type-pattern: void, boolean, String, List<Customer>
	declaring-type-pattern: com.leon.aopdemo.dao.AccountDAO (className)
	method-name-pattern: addAccount() or wildcards (*)
	param-pattern: () or (String, int)
	throws-pattern: () or (Exception), Exception type to match

	Example:
	Match on method names:
	Match only addAccount() method in AccountDAO class:

	@Before("execution(public void com.leon.aopdemo.dao.AccountDAO.addAccount())")
	Modifier: public
	Return type: void
	Declaring type: com.leon.aopdemo.dao.AccountDAO
	Method name: addAccount()
	Parameters: ()

	Example:
	Match on method names:
	Match any addAccount() method in any class:

	@Before("execution(public void addAccount())")
	Modifier: public
	Return type: void
	Declaring type: any
	Method name: addAccount()
	Parameters: ()

	Example:
	Match on method names (using wildcards):
	Match methods starting with add in any class:

	@Before("execution(public void add*())")
	Modifier: public
	Return type: void
	Declaring type: any
	Method name: add*
	Parameters: ()

	Example:
	Match on method names (using wildcards):
	Match methods starting with processCreditCard in any class:

	@Before("execution(public VerificationResult processCreditCard*())")
	Modifier: public
	Return type: VerificationResult
	Declaring type: any
	Method name: processCreditCard*
	Parameters: ()

	Example:
	Match on method names (using wildcards):
	Match methods starting with processCreditCard in any class and return type is any:

	@Before("execution(public * processCreditCard*())")
	Modifier: public
	Return type: any
	Declaring type: any
	Method name: processCreditCard*
	Parameters: ()

 */

/*
	Parameter Pattern Wildcards:

	()    - matches a method with no arguments
	(*)   - matches a method with one argument of any type
	(..)  - matches a method with any number of arguments of any type

	Example:

	@Before("execution(* com.leon.aopdemo.dao.*.*(..))")
	Modifier: any
	Return type: any
	Declaring type: com.leon.aopdemo.dao.*.*	(any class in com.leon.aopdemo.dao package)
	Method name: any
	Parameters: (..)

 */
