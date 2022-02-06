package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects= proService.getAll();
		model.addAttribute("projects",projects);
		return "projects/show-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees",employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(@Valid Project project,/*@RequestParam List<Long> employees,*/ Model model, Errors errors  ) {
		//this should handle saving to the database
		proService.save(project);
		
		 if(errors.hasErrors())
		 	return "projects/new-project";

		//use a redirect to prevent duplicate submissions 
		return "redirect:/projects";
	}
	
}
