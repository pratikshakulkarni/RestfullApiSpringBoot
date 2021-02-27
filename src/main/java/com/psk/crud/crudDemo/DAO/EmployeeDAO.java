package com.psk.crud.crudDemo.DAO;

import java.util.List;

import com.psk.crud.crudDemo.entity.Employee;

public interface EmployeeDAO {
	
	//read
	public List<Employee> findAll();
	
	//create or update
	public void save(Employee employee);
	
	//delete
	public void delete(int theId);
	
	//find 1
	public Employee findById(int theId);

}
