package com.psk.crud.crudDemo.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psk.crud.crudDemo.Service.EmployeeService;
import com.psk.crud.crudDemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	//Inject DAO.. it is not a good practice, try using service layer using Design patters
	private EmployeeService employeeService;
	
	//const injection
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//Expose "/employee" route
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	//expose "/employee/empid" to get employee by ID
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found" + employeeId);
		}
		
		return theEmployee;
	}
	
	//Add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmp) {
		
		//Id is set to auto increment in db, but if in request ID is passed then we'll set it to 0
		
		theEmp.setId(0);
		
		employeeService.save(theEmp);
		
		return theEmp;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee emp) {
		employeeService.save(emp);
		return emp;
	}
	
	//Delete Mapping
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee == null) {
			throw new RuntimeException("Employee not found"+employeeId);
		}
		
		employeeService.delete(employeeId);
		
		return "Employee deleted" + employeeId;
		
	}
	
}








