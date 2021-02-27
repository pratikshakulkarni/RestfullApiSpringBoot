package com.psk.crud.crudDemo.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psk.crud.crudDemo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	//Entity Manager constructor injection
	private EntityManager entityManager;
	
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query<Employee> myQuery = currentSession.createQuery("from Employee",Employee.class);
		
		//execute query and get reaults
		List<Employee> employees = myQuery.getResultList();
		
		//return the results
		
		return employees;
	}

	@Override
	public void save(Employee employee) {
		//create the session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save the employee employee coming from method parameter
		currentSession.saveOrUpdate(employee); 
	}

	@Override
	public void delete(int theId) {
		// Create the session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Create the query
		Query myQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		myQuery.setParameter("employeeId",theId);
		
		//execute the query delete the record
		myQuery.executeUpdate();	
		
	}

	@Override
	public Employee findById(int theId) {
		
		//Create the session hiberate
		Session currentSession = entityManager.unwrap(Session.class);
		
		//	get the employee from request theId is method parameter
		Employee theEmployee = currentSession.get(Employee.class,theId);
		
		//return the empl from db		
		return theEmployee;
	}

}
