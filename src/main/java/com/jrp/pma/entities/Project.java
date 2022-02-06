package com.jrp.pma.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_seq")
	@SequenceGenerator(name="project_seq", allocationSize = 1)
//	@Column(name = "projectId", updatable = false, nullable = false)
	private long projectId;
	
//	@NotNull(message="project name should not be empty!!")
//	@Size(min=2,max=50)
	private String name;

	private String stage; //NOTSTARTED, COMPLETED, INPROGRESS
	
//	@NotNull(message="description should not be empty!!")
//	@Size(min=2,max=50)
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE},
			fetch= FetchType.LAZY)
	@JoinTable(name="project_employee",
		joinColumns=@JoinColumn(name="project_id"),
		inverseJoinColumns=@JoinColumn(name="employee_id"))
	@JsonIgnore
	private List<Employee> employees;

	
	public Project() {
		
	}

	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// convenience method
	
}
