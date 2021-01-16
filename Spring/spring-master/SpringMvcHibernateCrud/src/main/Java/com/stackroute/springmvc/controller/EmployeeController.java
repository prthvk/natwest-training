package com.stackroute.springmvc.controller;

import com.stackroute.springmvc.dao.IEmployeeDao;
import com.stackroute.springmvc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {



    private IEmployeeDao employeeDAO;

    @Autowired
    public EmployeeController(IEmployeeDao employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

  /*  @GetMapping("/")
    public ResponseEntity<?> getEmployee()
    {
        return new ResponseEntity<>(employeeDAO.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{empName}")
    public ResponseEntity<?>getEmployeeByName(@PathVariable String empName)
    {
        return new ResponseEntity<>(employeeDAO.getEmployeeByName(empName),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeDAO.saveEmployee(employee),HttpStatus.CREATED);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@RequestParam int empId){
        return new ResponseEntity<>(employeeDAO.deleteEmployee(empId),HttpStatus.OK);
    }*/


     @GetMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("employees",employeeDAO.getAllEmployees());
        return modelAndView;

    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(Employee employee, ModelMap modelMap) {



        boolean status = employeeDAO.saveEmployee(employee);
        modelMap.addAttribute("employees", employeeDAO.getAllEmployees());

        return "index";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int empId) {
        employeeDAO.deleteEmployee(empId);
        return "redirect:/";
    }
}
