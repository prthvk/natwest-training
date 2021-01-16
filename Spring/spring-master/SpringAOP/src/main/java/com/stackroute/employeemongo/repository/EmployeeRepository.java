package com.stackroute.employeemongo.repository;

import com.stackroute.employeemongo.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    //All basic crud operation

    //findById()
    //findByXXX (XXX -can be replaced with the property of ur class)
    //findByName, findByEmail for all these method implementataion is not required

    //Query methods
    List<Employee> findByNameStartingWith(String name);

    //Now in this scenario city is not property of my document class hence I cannot use above find
    //method rather we will create our own query here ?0 is positional parameter

    @Query("{'address.city': {$in : [?0]}}")
    List<Employee> findAllEmployeeFromCity(String name);

}
