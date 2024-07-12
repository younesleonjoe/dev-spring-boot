package com.leon.mvcaop.service;

import java.util.List;

import com.leon.mvcaop.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int theId);
	
	void save(Employee theEmployee);
	
	void deleteById(int theId);
	
}
