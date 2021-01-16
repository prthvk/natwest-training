package com.stackroute.employee.service;

import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.EmployeeAlreadyExistsException;

import java.util.List;

public interface IEmployeeService {

    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException;

    public List<Employee> getAllEmployees() ;
}
