package com.leon.cruddemo.service;

import com.leon.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int employeeId);

    void save(Employee employee);

    void deleteById(int employeeId);

}
