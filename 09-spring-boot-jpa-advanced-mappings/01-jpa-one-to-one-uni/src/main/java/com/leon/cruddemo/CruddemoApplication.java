package com.leon.cruddemo;

import com.leon.cruddemo.dao.AppDAO;
import com.leon.cruddemo.entity.Instructor;
import com.leon.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// This method is annotated with the bean annotation so there is no need to annotate the method with the @Autowire annotation to inject the DAO.
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("\n\nFinding instructor with id: " + id + "\n");;
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("\n\n" + instructor + "\n");
		System.out.println("\n\nAnd the associate Instructor\n");
		System.out.println("\n\n" + instructor.getInstructorDetail() + "\n");
	}

	private void createInstructor(AppDAO appDAO) {

		// create an instructor
		Instructor instructor = new Instructor("Leon", "Younes", "leon@email.com");

		// create an instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("https://www.leon.com/youtube", "Luv 2 code!!!");

		// associate the instructor with the instructor detail
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the instructor
		// detail because the instructor has a OneToOne
		// relationship with the instructor detail
		// and cascadeType.ALL is set.
		System.out.println("\n\nSaving instructor: " + instructor + "\n");
		appDAO.save(instructor);
	}

}
