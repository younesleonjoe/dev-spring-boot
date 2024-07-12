package com.leon.cruddemo.dao;

import com.leon.cruddemo.dao.StudentDAO;
import com.leon.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
