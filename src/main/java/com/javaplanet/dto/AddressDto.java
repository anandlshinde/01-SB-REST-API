package com.javaplanet.dto;

import com.javaplanet.entity.Employee;

import lombok.Data;

@Data
public class AddressDto {

	private String addId;
	
	private String street;
	
	private String city;
	
	private String pincode;
	
	private Employee employee;
}
