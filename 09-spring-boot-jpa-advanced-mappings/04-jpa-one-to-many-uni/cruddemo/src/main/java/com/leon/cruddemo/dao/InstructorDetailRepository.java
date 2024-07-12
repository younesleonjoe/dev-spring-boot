package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.InstructorDetail;

public interface InstructorDetailRepository {
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
