package com.leon.cruddemo.dao;

import com.leon.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

	// add a method to sort by last name
	// Spring Data JPA will parse the method name
	// Looks for a specific format and pattern
	// Creates the appropriate query ... behind the scenes
	// (findAllBy, OrderByLastNameAsc) => "from Employee order by lastName asc"
	List<Employee> findAllByOrderByLastNameAsc();

}
