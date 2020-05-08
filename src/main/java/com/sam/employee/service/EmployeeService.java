package com.sam.employee.service;

import java.util.List;

import com.sam.employee.model.Employee;

public interface EmployeeService {
    public String welcome();
    public List<Employee> findAllEmployees();
    public Employee findEmployeeByID(Integer employeeID);
    public String deleteEmployeeByID(Integer employeeID);
    public Employee createEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
}
