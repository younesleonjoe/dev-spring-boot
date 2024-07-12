package com.leon.cruddemo;

import com.leon.cruddemo.entity.Student;
import com.leon.cruddemo.dao.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

	/*
	JPA has JPQL JPA Query Language	for retrieving objects
	Similar concept to SQL
	where, like, order by, join, in, etc...

	However, JPQL is based on entity name and entity fields
	example: entityManager.createQuery("FROM Student", Student.class);
	the Student in FROM Student is not the name of the database table instead is the name of the entity

	All JPQL syntax is based on entity name and entity fields

	 */

	/*
	Create database tables using java
	Java Code --> JPA/Hibernate --> SQL --> Database
	Steps:
	Configure application.properties
			spring.jpa.hibernate.dll-auto=create
			(When you run the app, JPA will drop the tables then create them)
			(Based on JPA/Hibernate annotations in your java code)

		spring.jpa.hibernate.dll-auto=PROPERTY-VALUE
			Property Value:
				none - No action will be performed
				create-only - Database tables are only created
				drop - Database tables are dropped
				create - Database tables are dropped followed by database tables creation
				create-drop - Database tables are dropped followed by database tables creation.
							  On application shutdown, drop the database tables.
							  This is useful for unit testing; when testing is finished, it drops the tables related to testing.
				validate - Validate the database tables schema
				update - Update the database tables schema
	 */

	/*
	For basic projects, can use auto configuration - spring.jpa.hibernate.dll-auto=create
	Database tables are dropped first and then created from scratch (all data is lost)
	 */

	/*
	If you want to create tables once...and then keep data, use:update
	spring.jpa.hibernate,dll-auto=update
	However will alter the database schema based on the latest code updates
	Be very careful here ... only use it for basic projects (because automatically changing the database schema may affect other applications using that given database)
	 */

	/*
	Don't do this on production databases
	You don't want to drop your production data
		All data is deleted which will cause some problems with the client and your manager

	Instead, for production, you should have DBAs to run SQL scripts and let them manage the production data you need to stay hands off as much as possible
	 */

	/*
	Use Case spring.jpa.hibernate.dll-auto=create
	Automatic table generation is useful for
		Database integration testing with in-memory databases
		Basic, small hobby projects
	 */

	/*
	Recommendation
	In general it isn't recommended to use auto generation for enterprise, real-time projects
	Because you can very easily drop PRODUCTION data if you are not careful

	It is recommended to use instead SQL scripts
		Corporate DBAs prefer SQL scripts for governance and code review
		The SQL scripts can be customized and fine-tuned for complex database designs
		The SQL scripts can be version-controlled
		Can also work with schema migration tools such as Liquibase and Flyway
	 */

	@Bean
	public CommandLineRunner commandLineRunner(/* Inject the Student DAO */StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 4;

		System.out.println("Deleting student id: " + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);

		Student student = studentDAO.findById(studentId);

		System.out.println("Updating student...");

		// Change firstName to "Scooby"
		student.setFirstName("Scooby");
		studentDAO.update(student);

		System.out.println("Updated student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Doe");

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> students = studentDAO.findAll();

		// display list of students
		for (Student student : students) {
			System.out.println(student);
		}
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
