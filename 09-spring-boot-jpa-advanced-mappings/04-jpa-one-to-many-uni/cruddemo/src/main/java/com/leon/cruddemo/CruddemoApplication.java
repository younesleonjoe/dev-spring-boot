package com.leon.cruddemo;

import com.leon.cruddemo.dao.CourseRepository;
import com.leon.cruddemo.dao.InstructorRepository;
import com.leon.cruddemo.entity.Course;
import com.leon.cruddemo.entity.Instructor;
import com.leon.cruddemo.entity.InstructorDetail;
import com.leon.cruddemo.entity.Review;
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

	@Bean
	public CommandLineRunner commandLineRunner(InstructorRepository instructorRepository, CourseRepository courseRepository) {

		return runner -> {

//			createCourseAndReviews(courseRepository);

//			retrieveCourseAndReviews(courseRepository);

			deleteCourseAndReviews(courseRepository);

		};
	}

	private void deleteCourseAndReviews(CourseRepository courseRepository) {

		int id = 12;
		System.out.println("Deleting course id: " + id);
		courseRepository.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(CourseRepository courseRepository) {

		int id = 12;
        System.out.println("Finding course id: " + id);

		Course course = courseRepository.findCourseAndReviewsByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(CourseRepository courseRepository) {

		// create course
		Course course = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		course.add(new Review("Great course ... love it!"));
		course.add(new Review("Cool course, job well done."));
		course.add(new Review("Awful course, hate it"));

		// save course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());

		courseRepository.save(course);

		System.out.println("Done!");
	}

	private void deleteCourseById(CourseRepository courseRepository) {

		int id = 12;
		System.out.println("Deleting the course id: " + id);

		courseRepository.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void updateCourse(CourseRepository courseRepository) {

		int id = 12;

		System.out.println("Finding course id: " + id);
		Course course = courseRepository.findCourseById(id);

		System.out.println("Updating course id: " + id);
		course.setTitle("Tester Title");

		courseRepository.update(course);
		System.out.println("Done!");
	}

	private void updateInstructor(InstructorRepository instructorRepository) {

		int id = 1;

		System.out.println("Finding instructor id: " + id);
		Instructor instructor = instructorRepository.findInstructorById(id);

		System.out.println("Updating instructor id: " + id);
		instructor.setLastName("Tester");

		instructorRepository.update(instructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(InstructorRepository instructorRepository) {
		int id = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = instructorRepository.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("The associated instructor detail: " + instructor.getInstructorDetail());
		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructors(InstructorRepository instructorRepository, CourseRepository courseRepository) {
		int id = 1;

		// find instructor
		Instructor instructor = instructorRepository.findInstructorById(id);
		System.out.println("Instructor: " + instructor);

		// find courses for instructor
		List<Course> courses = courseRepository.findCoursesByInstructorId(id);

		// associate the objects
		instructor.setCourses(courses);

		System.out.println("The associated courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(InstructorRepository instructorRepository) {

		int id = 1;
		System.out.println("Finding the instructor id: " + id);

		Instructor instructor = instructorRepository.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(InstructorRepository instructorRepository) {

		// create instructor
		Instructor instructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

		// create instructor details
		InstructorDetail instructorDetail = new InstructorDetail("htttp://www.susan.com/youtube", "Video Games");

		instructor.setInstructorDetail(instructorDetail);

		// create courses
		Course course1 = new Course("Spring Boot");
		Course course2 = new Course("Spring MVC");
		Course course3 = new Course("Spring Security");
		Course course4 = new Course("Spring Data");
		Course course5 = new Course("Spring Batch");

		// add courses to instructor
		instructor.addCourse(course1);
		instructor.addCourse(course2);

		// saving the instructor
		//
		// NOTE: this will also save the courses
		// because of CascadeType.PERSIST
		System.out.println("Saving the instructor: " + instructor);
		System.out.println("The courses: " + instructor.getCourses());
		instructorRepository.save(instructor);

		System.out.println("Done!");
	}
}
