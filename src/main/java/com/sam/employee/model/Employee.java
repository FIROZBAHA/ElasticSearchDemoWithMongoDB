package com.sam.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Employee")
@Document(collection="Employee")
public class Employee {
    @ApiModelProperty(notes = "The employee ID", required = true)
    @Id
    private Integer employeeID;
    @ApiModelProperty(notes = "The employee name")
    private String employeeName;
    @ApiModelProperty(notes = "The employee age")
    private String age;
    @ApiModelProperty(notes = "The employeemobile number")
    private String mobileNumber;
    @ApiModelProperty(notes = "The employee salary")
    private String salary;
    @ApiModelProperty(notes = "The employee city")
    private String city;

    public Employee() { }
    public Employee(Integer employeeID, String employeeName, String age, String mobileNumber, String salary, String city) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.salary = salary;
        this.city = city;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", EmployeeName='" + employeeName + '\'' +
                ", age='" + age + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", salary='" + salary + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
