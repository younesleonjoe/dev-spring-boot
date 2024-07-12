package com.cruddemo.dao;

import com.cruddemo.entity.Course;
import com.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class InstructorRepositoryImpl  implements InstructorRepository {
    private final EntityManager entityManager;

    @Autowired
    public InstructorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

        // check if the instructor exists
        if (instructor == null) return;

        List<Course> courses = instructor.getCourses();

        // break association of all courses for instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {

        // create query
        TypedQuery<Instructor> query = entityManager
                .createQuery(
                        "SELECT i FROM Instructor i " +
                                "JOIN FETCH i.courses " +
                                "JOIN FETCH i.instructorDetail " +
                                "WHERE i.id =  :instructorId",
                        Instructor.class
                );

        query.setParameter("instructorId", instructorId);

        // execute query
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }
}