package com.javaplanet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaplanet.dto.EmployeeDto;
import com.javaplanet.entity.Employee;
import com.javaplanet.exception.ResourceNotFoundException;

public interface IEmployeeService {

	public EmployeeDto registerEmployee(EmployeeDto employee);
	
	public Page<Employee> fetchAllEmployees(Pageable pageable);
	
	public EmployeeDto fetchEmployeeById(String empId) throws ResourceNotFoundException;
	
	public EmployeeDto findBymobileNo(String mobileno);

}