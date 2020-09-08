package com.itacademy.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee {
	
	
		
	//ATTRIBUTES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Finds the last id and assigns the id incremented
	private Long id;
	@Column
	private String name;
	@Column
	private Job job;
	@Column
	private Double salary;
	
	//CONSTRUCTORS
	public Employee() {}
	
	public Employee(Long id, String name, Job job) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = calculateSalary(job);
	}
	
	//GETTERS & SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Job job) {
		this.salary = calculateSalary(job);
	}
	
	//METHODS
	
	//Enumeration variable of jobs categories 
	public enum Job{
		EMPLOYEE, SUPERVISOR, MANAGER, DIRECTOR; 
	}
		
	/* JOBS SALARIES: 
	* Employee = base salary
	* Supervisor = 25% + base salary 
	* Manager = 50% + base salary 
	* Director = 2 x base salary
	* */
		
	//Calculate salary according to job category
	private Double calculateSalary(Job job) {
		
		//Base salary for all employees
		final Double baseSalary = 1500.00;
		
		switch(job) {
			case EMPLOYEE: 
				salary = baseSalary;
				break;
			case SUPERVISOR: 
				salary = baseSalary*1.25;
				break;
			case MANAGER: 
				salary = baseSalary*1.50;
				break;
			case DIRECTOR: 
				salary = baseSalary*2.00;
				break;
			default:
				salary = baseSalary;
				break;
		}
		return salary;
	}
	
	//Show employee information
	public String toString() {
		return "ID: " + this.id +" - Name: "+this.name+" - Job position: "+this.job+" - Salary: "+this.salary+"€";
	}

}
