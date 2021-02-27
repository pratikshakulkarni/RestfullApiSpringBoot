package com.psk.crud.crudDemo.Service;

import java.util.List;

import com.psk.crud.crudDemo.entity.Employee;

public interface EmployeeService {
	
	public void save(Employee employee);
	
	public void delete(int id);
	
	public List<Employee> findAll();
	
	public Employee findById(int id);

}
