package com.leon.cruddemo.controller;

import com.leon.cruddemo.entity.Employee;
import com.leon.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for /list
	@GetMapping("/list")
	public String listEmployees(Model model) {

		// get employees from service
		List<Employee> employees = employeeService.findAll();

		// add employees to model
		model.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "employees/employees-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {

		// save the employee
		employeeService.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {

		// get the employee from the service
		Employee employee = employeeService.findById(employeeId);
		model.addAttribute("employee", employee);
		return "employees/employees-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		if (employee != null) {
			employeeService.deleteById(employeeId);
		}
		return "redirect:/employees/list";
	}
}
