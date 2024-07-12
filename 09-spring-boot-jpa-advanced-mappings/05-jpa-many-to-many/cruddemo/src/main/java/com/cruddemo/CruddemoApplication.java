package com.cruddemo;

import com.cruddemo.dao.CourseRepository;
import com.cruddemo.dao.InstructorRepository;
import com.cruddemo.dao.StudentRepository;
import com.cruddemo.entity.Course;
import com.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourseRepository courseRepository, StudentRepository studentRepository) {

		return runner -> {
//			createCourseAndStudent(courseRepository);
//			findCourseAndStudents(courseRepository);
//			findStudentAndCourses(studentRepository);
//			addMoreCoursesForStudent(studentRepository);
			deleteStudent(studentRepository);
		};
	}

	private void deleteStudent(StudentRepository studentRepository) {
		int studentId = 3;
		System.out.println("Deleting student with id: " + studentId);
		studentRepository.deleteStudentById(studentId);
        System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(StudentRepository studentRepository) {

		int studentId = 2;

		Student student = studentRepository.findStudentAndCoursesByStudentId(studentId);

		// create more courses
		Course course1 = new Course("Spring Boot");
		Course course2 = new Course("Spring Data");

		// add courses to student
		student.addCourse(course1);
		student.addCourse(course2);

		// save student
		System.out.println("Updating student: " + student);
		System.out.println("Associated courses: " + student.getCourses());
		studentRepository.update(student);
		System.out.println("Done!");
	}

	private void findStudentAndCourses(StudentRepository studentRepository) {
		int id = 1;
		Student student = studentRepository.findStudentAndCoursesByStudentId(id);
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(CourseRepository courseRepository) {
		int id = 10;
		Course course = courseRepository.findCourseAndStudentsByCourseId(id);
		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudent(CourseRepository courseRepository) {

		// create a course
		Course course = new Course("Spring MVC");

		// create students
		Student s1 = new Student("leon", "younes", "leon@email.com");
		Student s2 = new Student("guitta", "saade", "guitta@email.com");
		Student s3 = new Student("charbel", "sadde", "charbel@email.com");

		// add students to course
		course.addStudent(s1);
		course.addStudent(s2);
		course.addStudent(s3);

		// save the course and associated students
		System.out.println("Saving the courses: " + course);
		System.out.println("Associated students: " + course.getStudents());
		courseRepository.save(course);
		System.out.println("Done!");
	}

}
