package com.stackroute.employeemongo.service;

import com.stackroute.employeemongo.controller.EmployeeController;
import com.stackroute.employeemongo.domain.Employee;
import com.stackroute.employeemongo.exception.EmployeeExistsException;
import com.stackroute.employeemongo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements IEmployeeService{

 /*   Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);*/

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeExistsException {
        Optional<Employee> optional = this.employeeRepository.findById(employee.getId());
 /*logger.debug(String.valueOf(optional));*/

        if(optional.isPresent()) {
            /*logger.info(String.valueOf(optional.isPresent()));*/
            throw new EmployeeExistsException("Employee already exists");
        }

        Employee createdEmployee = 	employeeRepository.save(employee);


        return createdEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeeByNameStartingWith(String name) {
       return employeeRepository.findByNameStartingWith(name);
    }

    @Override
    public List<Employee> getAllEmployeesFromCity(String city) {

        return employeeRepository.findAllEmployeeFromCity(city);
    }
}
