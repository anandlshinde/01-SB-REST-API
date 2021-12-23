package com.javaplanet.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaplanet.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Serializable>{

	
	public Employee findBymobileNo(String mobileno);
}
