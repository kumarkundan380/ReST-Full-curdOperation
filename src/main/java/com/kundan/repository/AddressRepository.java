package com.kundan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kundan.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
