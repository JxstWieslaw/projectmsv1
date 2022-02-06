package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	public String ver;
	
	@Autowired
	ProjectRepository proRepo;
	 
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/welcome")
	public String displayLandingPage() {
		return "home/index";
	}
	@GetMapping("/dashboard")
	public String displayDashboard() {
		return "dashboard/index";
	}
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		HashMap<String, Object> map= new HashMap<>();
		
		//we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		
		model.addAttribute("version",ver);
		
		//we are querying the database for employees
		
		//List<Employee> employees = empRepo.findAll();
		//model.addAttribute("employeesList", employees);
		
		List<ChartData> projectData = proRepo.getProjectStatuses();
		
		//Lets convert projectData into json Structure for use in JavaScript.
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		 
		model.addAttribute("projectListStatusCount",jsonString);
		 
		//we are querying the project_employee table
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCnt", employeesProjectCnt);
		return "main/home" ; 
	}
}
