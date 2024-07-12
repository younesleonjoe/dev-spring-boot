package com.leonspringdemo.mvc.model;

import com.leonspringdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@NotNull(message = "is required")
	@Min(value = 0, message = "must be greater than or equal to zero")
	@Max(value = 10, message = "must be less than or equal to 10")
	/*
		Int is used instead of the primitive type int to avoid a runtime error: Failed to convert property value of type java.lang.String to required type int for property freePasses; For input string: "a"
	 */
	private Integer freePasses;

	@Pattern(
			regexp = "^[a-zA-Z0-9]{5}$",
			message = "must be 5 characters long and contain only letters and numbers"
	)
	/*

	@NotBlank
		Validates that the string field is not null and the trimmed length is greater than 0.
		Checks for non-empty strings, trimming whitespace.
	@NotEmpty
		Validates that the string field is not null and the length is greater than 0.
		Allows whitespace.
	@Size
		Validates the length of the string field.
		Has min and max parameters to specify size range.

	Some key differences:
		@NotBlank only allows non-empty strings after trim, @NotEmpty allows whitespace.
		@Size checks length range, @NotBlank and @NotEmpty only check lower bound.
		@NotBlank and @NotEmpty apply to String fields, @Size can be used on arrays and collections also.

	So in summary:
		@NotBlank - non-empty after trim
		@NotEmpty - non-empty allowing whitespace
		@Size - length range check
		@NotBlank is best for validating required text input fields to prevent only whitespace. @Size is useful for validating precise length range.
	 */

	/*
		Here all three work the same because we wrote a initBinder in the Controller so it can turn any input fields to null if it contains only whitespaces
	 */
	@NotEmpty(message = "is required")
	private String postalCode;

	@CourseCode(value = "CSC", message = "must start CSC")
	private String courseCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
}

