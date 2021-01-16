package com.stackroute.employee.controller;


import com.stackroute.employee.domain.Address;
import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.AddressAlreadyExistsException;
import com.stackroute.employee.exception.EmployeeAlreadyExistsException;
import com.stackroute.employee.service.IAddressService;
import com.stackroute.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/addressservice")
public class AddressController {

    private IAddressService addressService;

    private ResponseEntity responseEntity;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")

    //http://localhost:8081/api/v1/employeeservice/employee
    public ResponseEntity<?> saveAddress(@RequestBody Address address)  {

        try {
            Address createdEmployee =  addressService.saveAddress(address);
            responseEntity = new ResponseEntity(createdEmployee , HttpStatus.CREATED);
        }catch (AddressAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e)
        {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/address")
    public ResponseEntity getAllAddress()
    {
        try{
            List<Address> addressList =  addressService.getAllAddress();
            responseEntity = new ResponseEntity(addressList , HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
}
