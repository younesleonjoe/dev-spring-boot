package com.leonspringdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
	
	// define default course code
	public String value() default "CSC";

	// define default error message
	public String message() default "must start with CSC";

	// define defaults groups
	public Class<?>[] groups() default {};

	// define default payloads
	// Payloads: provide custom details about validation failure (severity level, error code etc)
	public Class<? extends Payload>[] payload() default {};
}
