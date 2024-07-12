package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}
