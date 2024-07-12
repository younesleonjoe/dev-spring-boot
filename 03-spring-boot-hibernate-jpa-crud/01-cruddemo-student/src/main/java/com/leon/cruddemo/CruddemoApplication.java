package com.leon.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	/*
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;

		GenerationType:
			AUTO - Pick an appropriate strategy for the particular database
			IDENTITY - Assign a primary key using database identity column
			SEQUENCE - Assign primary key using a database sequence
			TABLE - Assign primary keys using an underlying database table to ensure uniqueness


		You can define your own CUSTOM generation strategy (for id)
		* Create implementation of org.hibernate.id.IdentifierGenerator
		* Override the method: public Serializable generate(...)
	 */

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("Hello World!");
		};
	}
}
