package com.cruddemo.dao;

import com.cruddemo.entity.InstructorDetail;

public interface InstructorDetailRepository {
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
