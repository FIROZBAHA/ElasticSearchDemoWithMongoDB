package com.sam.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sam.employee.model.Employee;
import com.sam.employee.repository.EmlployeeElasticRepo;
import com.sam.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	EmlployeeElasticRepo emlployeeElasticRepo;

	@Override
	public String welcome() {
		return "Welcome to employee management system 1.0.0";
	}

	@Override
	public List<Employee> findAllEmployees() {
		emlployeeElasticRepo.saveAll(employeeRepository.findAll());
		return employeeRepository.findAll();
	}

	@Override
	public Employee findEmployeeByID(Integer employeeID) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(employeeID));
		return mongoTemplate.findOne(query, Employee.class);
	}

	@Override
	public String deleteEmployeeByID(Integer employeeID) {
		employeeRepository.deleteById(employeeID);
		return "Record deleted successfully...";
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.insert(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		Optional<Employee> employeee = employeeRepository.findById(employee.getEmployeeID());

		if (null != employeee) {
			return employeeRepository.save(employee);
		}
		return employee;

	}
	
	
}
