package com.leon.springmvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
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

		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}password")
				.roles("EMPLOYEE", "MANAGER")
				.build();

		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}password")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(john, mary, susan);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.authorizeHttpRequests(configurer ->
						configurer
								.requestMatchers("/").hasRole("EMPLOYEE")
								.requestMatchers("/leaders/**").hasRole("MANAGER")
								.requestMatchers("/systems/**").hasRole("ADMIN")
								.anyRequest()
								.authenticated()
				)
				.exceptionHandling(configurer ->
						configurer.accessDeniedPage("/access-denied")
				)
				.formLogin(form ->
						form
								.loginPage("/showMyLoginPage")
								.loginProcessingUrl("/authenticateTheUser")
								.permitAll()
				)
				// We can write instead .logout(logout -> logout.permitAll());
				.logout(LogoutConfigurer::permitAll);

		return httpSecurity.build();
	}
}
