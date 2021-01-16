package com.stackroute.springmongodb.repository;

import com.stackroute.springmongodb.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    //All crud operations

    //findById()
    //findByXXX (XXX - can be replaced by the property of ur class Document )

    //findByEmpname( - property in my class)
    //findByEmail - (email proeprty in my class)

    List<Employee> findByempnameStartingWith(String name);
//Now in this scenarion city is not property of my document , so I accnot use above findBy methods
    //query  - ?0 - positional parameter


    @Query("{'address.city': {$in : [?0]}}")
    List<Employee> findAllEmployeeFromCity(String city);


    //Select name from table where = 'John'

    //PrepareStatement - Select name from table where = ? and city = ?

    //prepare.setString(1,name);
    //prepare,setString(2,city)
}
