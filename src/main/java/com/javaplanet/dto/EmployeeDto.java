package com.javaplanet.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeDto {
	
	
	private String empId;
	
	@NotNull
	@Size(min = 2, message = "Employee name should have atleast 2 characters")
	private String empName;
	
	@NotBlank
	@Email
	private String emailId;
	
	@NotNull
	@Size(max = 10,message = "Mobile number should be limit as a 10 digits")
	private String mobileNo;
	
	private String photoPath;
	
	private Set<AddressDto> addressDto;
	

}
