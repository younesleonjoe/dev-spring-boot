package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final EntityManager entityManager;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {

        // create query
        TypedQuery<Course> query = entityManager
                .createQuery("from Course where instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", instructorId);

        // execute query that returns a list of courses
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {

        // find course by id
        Course course = entityManager.find(Course.class, courseId);

        // remove course from persistence context
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int courseId) {

        // create query
        TypedQuery<Course> query = entityManager
                .createQuery(
                        "from Course c join fetch c.reviews where c.id = :courseId",
                        Course.class
                );

        query.setParameter("courseId", courseId);

        // execute query
        return query.getSingleResult();
    }
}
