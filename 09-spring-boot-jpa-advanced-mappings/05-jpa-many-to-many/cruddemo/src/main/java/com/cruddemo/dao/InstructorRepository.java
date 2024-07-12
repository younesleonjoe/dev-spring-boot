package com.cruddemo.dao;

import com.cruddemo.entity.Instructor;

public interface InstructorRepository {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    Instructor findInstructorByIdJoinFetch(int instructorId);

    void update(Instructor instructor);
}
