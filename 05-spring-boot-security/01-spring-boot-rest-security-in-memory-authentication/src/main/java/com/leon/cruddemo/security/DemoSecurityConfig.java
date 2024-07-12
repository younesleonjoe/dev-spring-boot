package com.leon.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}password")
				.roles("EMPLOYEE")
				.build();
		UserDetails marry = User.builder()
				.username("marry")
				.password("{noop}password")
				.roles("EMPLOYEE", "MANAGER")
				.build();
		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}password")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(john, marry, susan);
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
		http.csrf(csrf -> csrf.disable());
		// or http.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}
}
