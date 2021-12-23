package com.javaplanet.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaplanet.dto.AddressDto;
import com.javaplanet.dto.EmployeeDto;
import com.javaplanet.entity.Address;
import com.javaplanet.entity.Employee;
import com.javaplanet.exception.ResourceNotFoundException;
import com.javaplanet.repository.AddressRepository;
import com.javaplanet.repository.EmployeeRepository;
import com.javaplanet.service.IEmployeeService;
import com.javaplanet.utils.CollectionUtilis;
import com.javaplanet.utils.DtoUtils;

@Service
public class EmployeeService<R> implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CollectionUtilis collectionUtils;
	
	@Override
	public EmployeeDto registerEmployee(EmployeeDto employeeDto) {
		Set<AddressDto> addressDto = employeeDto.getAddressDto();
		
		Employee employee = DtoUtils.map(employeeDto, Employee.class);
		
		 List<Address> addressList = DtoUtils.mapAll(addressDto, Address.class);
		
		 for(Address address:addressList) {
			 address.setEmployee(employee);
		 }
		 
		Employee responseObj = this.employeeRepository.save(employee);
		System.out.println("Register Employee");
		List<Address> addressResponse = this.addressRepository.saveAll(addressList);
		
		Set<Address> collect = addressResponse.stream().collect(Collectors.toSet());
		
		Set<AddressDto> mapAll = DtoUtils.mapAll(collect, AddressDto.class).stream().collect(Collectors.toSet());
		
		EmployeeDto map = DtoUtils.map(responseObj, EmployeeDto.class);
		
		map.setAddressDto(mapAll);
		
		return map;
	}
	
	@Override
	public Page<Employee> fetchAllEmployees(Pageable pageable) {
		Page<Employee> employeesData = this.employeeRepository.findAll(pageable);
		return employeesData;
	}
	
	@Override
	public EmployeeDto fetchEmployeeById(String empId) throws ResourceNotFoundException {
		Employee employee = this.employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found : "+empId));
		return DtoUtils.map(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto findBymobileNo(String mobileno) {
		Employee employee = this.employeeRepository.findBymobileNo(mobileno);
		return DtoUtils.map(employee, EmployeeDto.class);
	}
}
