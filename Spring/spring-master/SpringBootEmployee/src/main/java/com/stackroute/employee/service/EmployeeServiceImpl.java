package com.stackroute.employee.service;

import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.EmployeeAlreadyExistsException;
import com.stackroute.employee.repository.EmployeeRepository;
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

/*
            1) check whether employee object is already exists or not in db
            2) if yes, throw the EmployeeAlreadyExistsException
            3)If not, persists the employee in db
            4)Return the save employee

 */
          Optional<Employee> optional =  employeeRepository.findById(employee.getEmpid());
          if(optional.isPresent())
          {
              throw new EmployeeAlreadyExistsException("Employee Alreday Exists");
          }
        Employee createdEmployee=  employeeRepository.save(employee);
          return createdEmployee;

}


    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();

    }
}
