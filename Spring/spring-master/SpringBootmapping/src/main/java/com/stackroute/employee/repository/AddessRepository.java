package com.stackroute.employee.repository;

import com.stackroute.employee.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddessRepository extends JpaRepository<Address, Integer> {
}
