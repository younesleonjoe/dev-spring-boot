package com.cruddemo.dao;

import com.cruddemo.entity.Student;

public interface StudentRepository {

    Student findStudentAndCoursesByStudentId(int studentId);
    void update(Student student);
    void deleteStudentById(int studentId);
}
