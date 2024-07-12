package com.leon.cruddemo.rest;

import com.leon.cruddemo.entity.Employee;
import com.leon.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeRestController {

    // quick and dirty: inject employee dao, expose "/employees" and return list of employees

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return employee;
    }

    @PostMapping("employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // in case an id is added in the body make it 0 so the merge can process it as an insert
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        employeeService.deleteById(id);
        return "Deleted employee id - " + id;
    }

}
