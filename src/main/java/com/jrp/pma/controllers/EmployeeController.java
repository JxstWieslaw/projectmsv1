package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees= empService.getAll();
		model.addAttribute("employees",employees);
		return "employees/show-employees";
	}
	
	@GetMapping("/new")
	public String processEmployeeForm(Model model) {
		//used to populate the object and on submit as per the form action, 
		//the object is passed to the displayEmployeeData method
		Employee employee=new Employee();
		model.addAttribute("employee", employee);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Model model,/*@Valid*/ Employee employee /*Errors errors*/) {
		
//		 if(errors.hasErrors())
//		 	return "employees/new-employee";
		
		//saves to the database the populated object
		// save to the database using an employee crud repository
		empService.save(employee);
		
		return "redirect:/employees";
	}

	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		
		model.addAttribute("employee", theEmp);
		
		
		return "employees/new-employee";
	}
	
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}
}
