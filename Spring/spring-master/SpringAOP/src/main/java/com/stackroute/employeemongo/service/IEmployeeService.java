package com.stackroute.employeemongo.service;

import com.stackroute.employeemongo.domain.Employee;
import com.stackroute.employeemongo.exception.EmployeeExistsException;

import java.util.List;

public interface IEmployeeService {

    public Employee saveEmployee(Employee employee) throws EmployeeExistsException;
    public List<Employee> getAllEmployee();
    public List<Employee> getAllEmployeeByNameStartingWith(String name);
    public List<Employee> getAllEmployeesFromCity(String city);

}
