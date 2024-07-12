package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findCoursesByInstructorId(int instructorId);

    Course findCourseById(int courseId);

    void update(Course course);

    void deleteCourseById(int courseId);
}
