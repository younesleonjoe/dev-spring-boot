package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
