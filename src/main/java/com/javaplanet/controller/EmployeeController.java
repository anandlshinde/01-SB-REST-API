package com.javaplanet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javaplanet.dto.EmployeeDto;
import com.javaplanet.dto.Message;
import com.javaplanet.entity.Employee;
import com.javaplanet.exception.ResourceNotFoundException;
import com.javaplanet.service.IEmployeeService;
import com.javaplanet.utils.DtoUtils;
import com.javaplanet.utils.EmailUtils;
import com.javaplanet.utils.HateoasUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Api(value = "Rest Api for employee module")
@Slf4j
public class EmployeeController {

	private IEmployeeService employeeService;

	private EmailUtils emailUtils;
	


	public EmployeeController(IEmployeeService employeeService,
							  EmailUtils emailUtils) {
		this.employeeService=employeeService;
		this.emailUtils=emailUtils;
	}

	@ApiOperation(value = "Register new employee")

	@PostMapping("/register")
	public ResponseEntity<EmployeeDto> registerEmployee(
			@ApiParam(value = "Employee object store in database table", required = true) 
			@Valid @RequestBody EmployeeDto employeeDto) {

		log.info("Employee register controller method :: " + employeeDto);

		EmployeeDto responseData = this.employeeService.registerEmployee(employeeDto);
		if (responseData == null)
			log.info("Employee not registered. error occured in employee registration");
		else
			//sendEmail(employeeDto);
		log.info("Employee registered successfully");
		return new ResponseEntity<EmployeeDto>(responseData, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Fetch All employees list", response = Page.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All employees fetched successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Resource<EmployeeDto>> fetchAllEmployee(@PageableDefault(page = 0, size = 5) Pageable pageable)
			throws ResourceNotFoundException {
		Page<Employee> fetchAllEmployees = this.employeeService.fetchAllEmployees(pageable);
		List<EmployeeDto> mapAll = DtoUtils.mapAll(fetchAllEmployees.getContent(), EmployeeDto.class);

		List<Resource<EmployeeDto>> employeeList = new ArrayList<>();
		for (EmployeeDto employeeDto : mapAll) {
			employeeList.add(HateoasUtils.getEmployeeResource(employeeDto));
		}
		Page<Resource<EmployeeDto>> empDtoList = new PageImpl<>(employeeList, pageable,
				fetchAllEmployees.getTotalElements());
		return empDtoList;
	}

	@ApiOperation(value = "Fetch specific employee details by employee id")
	@GetMapping("/fetchById/{empId}")
	public ResponseEntity<EmployeeDto> fetchEmployeeById(@PathVariable(value = "empId") String empId)
			throws ResourceNotFoundException {
		EmployeeDto employeeDto = this.employeeService.fetchEmployeeById(empId);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Fetch Employee details by mobile number")
	@GetMapping("/fetchByMobileno/{mobileno}")
	public ResponseEntity<EmployeeDto> fetchEmployeeByMobile(@PathVariable(value = "mobileno") String mobileno) throws ResourceNotFoundException{
		EmployeeDto employeeDto = this.employeeService.findBymobileNo(mobileno);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}
	
	
	
	

	private void sendEmail(EmployeeDto employeeDto) {
		final String reciepient = employeeDto.getEmailId();

		final String subject = "Suceesfully Registration with EmailWithRegistrationDemoApp";
		final String message = "<html> <body> <p>Dear Sir/Madam,</p><p>You have succesfully Registered with our Services\"\r\n"
				+ "				+ \"<br><br>\"\r\n"
				+ "				+ \"<table border='1' width='300px' style='text-align:center;font-size:20px;'><tr> <td colspan='2'>\"\r\n"
				+ "				+ \"</td></tr><tr><td>Name</td><td>\'" + employeeDto.getEmpName()
				+ "'</td></tr><tr><td>Email</td><td>\'" + reciepient + "' </td></tr></table> </body></html>";

		Message messageObj = new Message();

		messageObj.setRecipient(reciepient);
		messageObj.setSubject(subject);
		messageObj.setMessage(message);

		this.emailUtils.sendmail(messageObj);
	}

}
