package com.cruddemo.dao;

import com.cruddemo.entity.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findCoursesByInstructorId(int instructorId);

    Course findCourseById(int courseId);

    void update(Course course);

    void deleteCourseById(int courseId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int courseId);

    Course findCourseAndStudentsByCourseId(int courseId);
}
