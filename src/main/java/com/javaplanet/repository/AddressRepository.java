package com.javaplanet.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaplanet.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Serializable> {

	
}
