package com.stackroute.springmvc.dao;

import com.stackroute.springmvc.entity.Employee;

import java.util.List;

public interface IEmployeeDao {

    public boolean saveEmployee(Employee employee);
    public Employee getEmployeeById(int empId);
    public List<Employee> getAllEmployees();
    public boolean deleteEmployee(int empId);
    public boolean updateEmployee(Employee employee);
    public Employee getEmployeeByName(String name);

}
