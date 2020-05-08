package com.sam.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sam.employee.model.Employee;
import com.sam.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="Employee Management System with Mongo DB", description="Operations pertaining to employee in Employee Management System")
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    

    @ApiOperation(value = "welcome page", response = String.class)
    @GetMapping(value = "/welcome")
    public String createStudent() {
        logger.info("In welcome method");
        return employeeService.welcome();
    }

    @ApiOperation(value = "View a list of available employees", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> listAllStudents() {
        logger.info("IN listAllTasks method");
        List<Employee> employeeList = employeeService.findAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "View employee details by id", response = ResponseEntity.class)
    @GetMapping(value = "/employee/{id}")
    public Employee getEmployeeByID(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @Valid @PathVariable("id") String id){
        logger.info("IN getEmployee METHOD");
     Employee employee = employeeService.findEmployeeByID(Integer.parseInt(id));
        return employee;
    }

    @ApiOperation(value = "delete employee by id", response = ResponseEntity.class)
    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@ApiParam(value = "Employee id from which employee object will be deleted", required = true) @Valid @PathVariable("id") String employeeID) throws Exception {
        logger.info("IN delete METHOD");
        String status = "Internal server error";
        try {
            status = employeeService.deleteEmployeeByID(Integer.parseInt(employeeID));
        } catch (Exception e) {
            throw new Exception("Employee delete is not allowed.");
        }
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @ApiOperation(value = "Create new employee", response = ResponseEntity.class)
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@ApiParam(value = "Employee json for creating new employee", required = true) @Valid @RequestBody Employee employee) throws Exception {
        logger.info("IN create METHOD");
        Employee status = employeeService.createEmployee(employee);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update employee details by id", response = ResponseEntity.class)
    @PutMapping(value = "/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@ApiParam(value = "Employee id from which employee object will be updated", required = true) @Valid @PathVariable("id") String id,
                                                 @ApiParam(value = "Employee json for updating", required = true) @Valid @RequestBody Employee employee) throws Exception {
    	Employee employeee = null;
        logger.info("IN update METHOD");
        if(employee.getEmployeeID() == null){
            throw new Exception("Unable to update. Employee with id " + id + " not found.");
        }
        try {
        	 employeee = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            throw new Exception("Employee update is not allowed.Please reload your edit student view.");
        }
        return new ResponseEntity<>(employeee, HttpStatus.OK);
    }

}