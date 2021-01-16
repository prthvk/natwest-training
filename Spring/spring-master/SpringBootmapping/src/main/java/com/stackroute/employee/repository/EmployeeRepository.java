package com.stackroute.employee.repository;

import com.stackroute.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

    // public String getFindByUsername(empid);
}
