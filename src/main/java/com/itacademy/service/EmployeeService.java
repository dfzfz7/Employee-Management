package com.itacademy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.dao.EmployeeRepository;
import com.itacademy.dto.Employee;
import com.itacademy.dto.Employee.Job;

@Service
public class EmployeeService {

	//Link with repository
	@Autowired
	EmployeeRepository employeeRepository;
	
	//Add employees to list
	public String addEmployees() {
		List<Employee> Staff = new ArrayList<Employee>();
		Staff.add(new Employee(null,"Daniel",Job.DIRECTOR)); 
		Staff.add(new Employee(null,"Pepe",Job.MANAGER)); 
		Staff.add(new Employee(null,"Juan",Job.SUPERVISOR)); 
		Staff.add(new Employee(null,"Pedro",Job.SUPERVISOR)); 
		Staff.add(new Employee(null,"Pablo",Job.EMPLOYEE)); 
		Staff.add(new Employee(null,"Santiago",Job.EMPLOYEE)); 
		employeeRepository.saveAll(Staff);
		return "Employees added";
	}
	
	//Get all employees List
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	//Add new employee
	public String newEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee.getJob() + " " + employee.getName() + " added \n" + employee.toString();
	}
	
	//Get employee by id
	public Employee getEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		return employee;
	}
	
	//Update employee by id
	public String updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		//TODO
		return employee.getJob() + " " + employee.getName() + " --> Has been UPDATED \n" + employee.toString();
	}
	
	//Delete employee by id
	public String deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).get();;
		employeeRepository.deleteById(id);
		return employee.getJob() + " " + employee.getName() + " --> Has been DELETED";
	}

	//Get employees by job category
	public List<Employee> getJobs(Job job) {
		return employeeRepository.findAllByJob(job);	
	}
		
		
}
