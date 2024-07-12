package com.cruddemo.dao;

import com.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
    final EntityManager entityManager;

    @Autowired
    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int studentId) {

        TypedQuery<Student> query = entityManager.
                createQuery("FROM Student s JOIN FETCH s.courses WHERE s.id = :studentId", Student.class);

        query.setParameter("studentId", studentId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {

        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
    }
}
