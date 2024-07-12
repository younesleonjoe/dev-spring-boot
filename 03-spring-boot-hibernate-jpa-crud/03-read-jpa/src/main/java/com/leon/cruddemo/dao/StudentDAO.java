package com.leon.cruddemo.dao;

/*
Step 1 - Define DAO interface
 */

import com.leon.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    int updateAllLastName(String lastName);

    void delete(Integer id);

    int deleteAll();

    int deleteByLastName(String lastName);
}
