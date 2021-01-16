package com.stackroute.employee.controller;


import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.EmployeeAlreadyExistsException;
import com.stackroute.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employeeservice")
public class EmployeeController {

    private IEmployeeService employeeService;

    private ResponseEntity responseEntity;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //create a new Employee

    //Boolean , String , List<Employee> , Json data (HttpStatus - Created , Httpstatus code - 200 success)

    @PostMapping("/employee")

    //http://localhost:8081/api/v1/employeeservice/employee
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee)  {

        try {
           Employee createdEmployee =  employeeService.saveEmployee(employee);
           responseEntity = new ResponseEntity(createdEmployee , HttpStatus.CREATED);
        }catch (EmployeeAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

  return responseEntity;
    }

    @GetMapping("/employees")
    public ResponseEntity getAllEmployee()
    {
        try{


       List<Employee> employeeList =  employeeService.getAllEmployees();
            responseEntity = new ResponseEntity(employeeList , HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }



    //delete emp obj.

    //update email id emp.  @Requestparameter , @Pathvariable

}
