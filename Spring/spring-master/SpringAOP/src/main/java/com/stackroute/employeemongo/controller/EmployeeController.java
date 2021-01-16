package com.stackroute.employeemongo.controller;


import com.stackroute.employeemongo.domain.Employee;
import com.stackroute.employeemongo.exception.EmployeeExistsException;
import com.stackroute.employeemongo.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employeeservice")
public class EmployeeController {

    //getting logger object from Loggerfactory
   /* Logger logger = LoggerFactory.getLogger(EmployeeController.class);*/



    private IEmployeeService employeeService;

    private ResponseEntity responseEntity;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws EmployeeExistsException {

        try {
            Employee createdEmployee =  employeeService.saveEmployee(employee);
            responseEntity = new ResponseEntity(createdEmployee , HttpStatus.CREATED);
           /* logger.info("---->inside the savEmployee method ---->"+ createdEmployee);*/
        }catch (EmployeeExistsException e) {
          /*  logger.error("Error" + e.getMessage());*/
             responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);

        }catch (Exception e)
        {

            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/employees")
    public ResponseEntity getAllEmployee()
    {
        try{


            List<Employee> employeeList =  employeeService.getAllEmployee();
            responseEntity = new ResponseEntity(employeeList , HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/employees/name/{startswith}")
    public ResponseEntity getAllEmployeeStartsWith(@PathVariable("startswith") String startswith){
        return new ResponseEntity(employeeService.getAllEmployeeByNameStartingWith(startswith),HttpStatus.OK);
    }

    @GetMapping("/employees/city/{cityname}")
    public ResponseEntity getAllEmployeesFromCity(@PathVariable("cityname") String cityname){
        return new ResponseEntity(employeeService.getAllEmployeesFromCity(cityname),HttpStatus.OK);
    }

}
