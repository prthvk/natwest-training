package com.stackroute.employee.repository;


import com.stackroute.employee.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository {


    List<Employee> employeeList;
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }




    public EmployeeRepository(List<Employee> employeeList) {
        this.employeeList = new ArrayList<>();
    }

    public Employee addEmployee(Employee employee)
    {
       this.employeeList.add(employee);
       return employee;
    }

    public void deleteEmployee(final int empId){
        this.employeeList.removeIf(employee -> employee.getEmpId() == empId);
    }



}
