package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Instead of the plural of employee we can define a custom like members
@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

// sort in url by lastName
// members?sort=lastName
// sort in url by lastName descending
// members?sort=lastName,desc
// sort in url by firstName ascending and lastName descending
// members?sort=firstName,lastName,desc