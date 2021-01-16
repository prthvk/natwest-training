package com.stackroute.employee.service;

import com.stackroute.employee.domain.Address;
import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.AddressAlreadyExistsException;

import java.util.List;

public interface IAddressService {

    public Address saveAddress(Address address) throws AddressAlreadyExistsException;

    public List<Address> getAllAddress() ;
}
