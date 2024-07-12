package com.leon.cruddemo.dao;

/*
Step 1 - Define DAO interface
 */

import com.leon.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);
}
