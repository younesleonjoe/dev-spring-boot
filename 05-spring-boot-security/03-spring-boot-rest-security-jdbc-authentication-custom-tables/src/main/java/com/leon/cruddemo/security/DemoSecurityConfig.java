package com.leon.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

	/*
	For custom tables, we need to provide JDBC with:
		Query to find user by username
		Query to find authorities / roles by username
	 */

	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(datasource);

		// define a query to retrieve a user by username
		userDetailsManager.setUsersByUsernameQuery(
				"SELECT user_id, pw, active FROM members WHERE user_id=?"
		);

		// define a query to retrieve the authorities/roles by username
		userDetailsManager.setAuthoritiesByUsernameQuery(
				"SELECT user_id, role FROM roles WHERE user_id=?"
		);

		return userDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
						.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
						.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
						.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
						.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
						.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
		);

		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());

		// disable Cross Site Request Forgery (CSRF)
		// in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
		http.csrf(AbstractHttpConfigurer::disable);
		// or http.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
