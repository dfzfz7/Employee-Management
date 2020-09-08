package com.itacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itacademy.dto.Employee;
import com.itacademy.dto.Employee.Job;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findAllByJob(Job job);

}
