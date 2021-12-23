package com.javaplanet.utils;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import com.javaplanet.controller.EmployeeController;
import com.javaplanet.dto.EmployeeDto;
import com.javaplanet.exception.ResourceNotFoundException;

public class HateoasUtils {
	
	// Utility method to prepare the self link For Employee.
		public static Resource<EmployeeDto> getEmployeeResource(EmployeeDto employeeDto) throws ResourceNotFoundException {
			Resource<EmployeeDto> resource = new Resource<EmployeeDto>(employeeDto);
			resource.add(linkTo(methodOn(EmployeeController.class).fetchEmployeeById(employeeDto.getEmpId())).withRel("_self").withTitle("Employee Details").withDeprecation("This endpoint is showing particular employee details "));
			return resource;
		}

}
