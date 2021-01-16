package com.stackroute.employee.service;

import com.stackroute.employee.domain.Address;
import com.stackroute.employee.domain.Employee;
import com.stackroute.employee.exception.AddressAlreadyExistsException;
import com.stackroute.employee.exception.EmployeeAlreadyExistsException;
import com.stackroute.employee.repository.AddessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {


    private AddessRepository addessRepository;

    @Autowired
    public AddressServiceImpl(AddessRepository addessRepository) {
        this.addessRepository = addessRepository;
    }

    @Override
    public Address saveAddress(Address address) throws AddressAlreadyExistsException {
        Optional<Address> optional =  addessRepository.findById(address.getId());
        if(optional.isPresent())
        {
            throw new AddressAlreadyExistsException("Address Alreday Exists");
        }
        Address createdAddress=  addessRepository.save(address);
        return createdAddress;
    }

    public List<Address> getAllAddress(){

        return addessRepository.findAll();

    }


}
