package com.leon.cruddemo;

import com.leon.cruddemo.entity.Student;
import com.leon.cruddemo.dao.StudentDAO;
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

	/*
	@Transactional
		Automatically begin and end a transaction for your JPA code
		No need for you to explicitly do this in your code
		The spring magic will happen behind the scenes
	 */

	/*
	@Repository which is a sub annotation of @Component

		@RestController --> @Component
		@Repository --> @Component
		... --> @Component

	It is applied to DAO implementations
	Spring will automatically register the DAO implementation
		thanks to component scanning
	Spring also provides translation of any JDBC related exceptions
	(if you have any checked jdbc exception, spring will translate them to unchecked exception)
	 */

	/*
	TRUNCATE student_tracker.student will empty the database and restart the index to 1
	 */

	@Bean
	public CommandLineRunner commandLineRunner(/* Inject the Student DAO */StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);

			// createMultipleStudents(studentDAO);

			readStudent(studentDAO);
		};
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student tmpStudent = new Student("Daffy", "Duck", "daffy@email.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tmpStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tmpStudent.getId());

		// retrieve a student based on the id: primary key
		System.out.println("\nRetrieving student the id: " + tmpStudent.getId());

		Student student = studentDAO.findById(tmpStudent.getId());

		System.out.println("Found the student: " + student);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating a student objects ...");

		Student student1 = new Student("John", "Doe", "john@luv2code.com");
		Student student2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student student3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

	}

	/*
	Step 3 - Update main app
	 */
	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student student = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student);

		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
