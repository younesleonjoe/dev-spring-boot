package com.leon.cruddemo.dao;

import com.leon.cruddemo.dao.StudentDAO;
import com.leon.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Step 2 - Define DAO implementation
 */

@Repository
/*
Specialized annotation for repositories
Support component scanning
Translate JDBC exceptions
*/
/*
This should be named as a Repo name
 */
public class StudentDAOImplementation implements StudentDAO {

    private EntityManager entityManager;

    // Inject the Entity Manager
    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save the java object
    @Override
    @Transactional /* Handles transaction management */
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        /* No need to add @Transactional since we are doing a query */
        /* If not found, returns null */
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student where lastName = :lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int updateAllLastName(String lastName) {
        return entityManager.createQuery("UPDATE Student SET lastName=:lastName")
                .setParameter("lastName", lastName)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        // Delete all student's
        // Returns the number of students deleted
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }

    @Override
    @Transactional
    public int deleteByLastName(String lastName) {
        // Delete based on condition
        // Returns the number of students deleted
        return entityManager.createQuery(
                "DELETE FROM Student WHERE lastName=:lastName"
        ).setParameter("lastName", lastName).executeUpdate();
    }
}
