package com.stackroute.employee.controller;


import com.stackroute.employee.model.Employee;
import com.stackroute.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    //http://localhost:808:/SpringMVCHelloDemo/hello/myrequest




    //Show the index.jsp - form
    @GetMapping("/")
    public String getHomePage(ModelMap modelMap)
    {
        modelMap.addAttribute("employeeList",employeeRepository.getEmployeeList());
        return "index"; //my view page name
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployeeData( Employee employee )
    {
          ModelAndView modelAndView = new ModelAndView("index");
         this.employeeRepository.addEmployee(employee);
         modelAndView.addObject("employeeList",this.employeeRepository.getEmployeeList());
         return modelAndView;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int empId){

        this.employeeRepository.deleteEmployee(empId);
        return "redirect:/";

    }

    public String showUpdateJsp()
    {
        return "update";// ---> update.jsp - view
    }

    public String updateEmployee()
    {
        //repository update method
        return "index";
    }


}
