package com.itacademy.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.itacademy.dto.Employee;
import com.itacademy.dto.Employee.Job;
import com.itacademy.service.EmployeeService;


@Controller
@RequestMapping("/")
public class EmployeeController {
	
	//List to save employees
	List<Employee> staff;
	
	//Link with Service
	@Autowired
	EmployeeService employeeService;
	
	//Add employees to list
	@GetMapping("/")	
	public String showMenu() {
		//Fill employee list with sample if empty
		staff = employeeService.getAllEmployees();
		if(staff.isEmpty()) { //Add sample employees
			employeeService.addEmployees();
		}
        return "index";
    }
	
	//Get all employees
	@GetMapping("/staff")
	public String getAllEmployees(Model model) {
        staff = employeeService.getAllEmployees();
        model.addAttribute("staff", staff);
        return "showEmployees";
    }
	
	//Add new employee (then save employee)
	@GetMapping("/new")
	  public String newEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
	    return "addEmployee";
	}
	//Save new employee 
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee employee, Model model) {
		model.addAttribute("employee", employee);
		employee.setSalary(employee.getJob());
		employeeService.newEmployee(employee);
		return "employeeAdded";
	}
	
	//View employee
	@GetMapping("/employee/{id}") 
	public String getEmployee (@PathVariable(name="id") Long id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		return "viewEmployee"; 
	}
	
	//Update employee
	@GetMapping("/update/{id}") 
	public String updateEmployee (@PathVariable(name="id")Long id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		
		return "updateEmployee";
	}
	//Employee updated
	@PostMapping("/updated/{id}")
	public String employeeUpdated(@PathVariable(name="id")Long id, Employee employee, Model model) {
		model.addAttribute("employee", employee);
		//employee.setId(id);
		employee.setSalary(employee.getJob());
		String updated = employeeService.updateEmployee(employee);
		model.addAttribute("updated", updated);
		return "employeeUpdated";
	}
	
	
	//Delete employee
	@GetMapping("/delete/{id}")
	public String deleteEmployee (@PathVariable(name="id") Long id, Model model) {
		String deleted = employeeService.deleteEmployee(id);
		model.addAttribute("deleted", deleted);
		return "deleteEmployee";
	}
	
	//Get employees with same job category
	@GetMapping("/staff/{job}")
	//public List<Employee> getJobs(@RequestParam(value = "job", defaultValue = "EMPLOYEE") Job job)
	public String getJobs(@PathVariable(name="job") Job job, Model model) {
		List<Employee> group = employeeService.getJobs(job);
		model.addAttribute("group", group);
		model.addAttribute("job", job);
		return "showByJob";
	}
	
	
	//HANDLING REQUESTS ERRORS
	
	// Employee not found handling error - 500
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoSuchElementException.class)
	public String NoEmployeeFound() {
		return "employeeNotFound";
	}

	// Invalid number ID handling error - 400
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public String invalidId() {
		return "invalidEmployeeId";
	}
	
}
