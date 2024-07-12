package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Instructor;

public interface InstructorRepository {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    Instructor findInstructorByIdJoinFetch(int instructorId);

    void update(Instructor instructor);
}
