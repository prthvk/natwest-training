package com.stackroute.springmongodb.service;

import com.stackroute.springmongodb.domain.Employee;
import com.stackroute.springmongodb.exception.EmployeeAlreadyExistsException;
import com.stackroute.springmongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException {

        Optional<Employee> optional =  employeeRepository.findById(employee.getEmpid());
        if(optional.isPresent())
        {
            throw new EmployeeAlreadyExistsException("Employee Alreday Exists");
        }
        Employee createdEmployee=  employeeRepository.save(employee);
        return createdEmployee;

    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }



    @Override
    public List<Employee> getAllEmployeeByNameStartingWith(String name) {
        return employeeRepository.findByempnameStartingWith(name);
    }

    @Override
    public List<Employee> getAllEmployeesFromCity(String city) {
        return employeeRepository.findAllEmployeeFromCity(city);
    }


}
