package com.javaplanet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaplanet.dto.EmployeeDto;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	MockMvc mockmvc;
	
	private WebApplicationContext appContext;
	
	ObjectMapper objMapper=new ObjectMapper();
	
	@Before
	public void setup() {
		mockmvc=MockMvcBuilders.webAppContextSetup(appContext).build();
	}
	
	@Test
	public void addEmployeeTest() throws Exception {
		
		EmployeeDto dto=new EmployeeDto();
		dto.setEmpName("Anand");
		dto.setMobileNo("9158580521");
		dto.setEmailId("anand@gmail.com");
		
		String valueAsString = objMapper.writeValueAsString(dto);
		
		MvcResult result = mockmvc.perform(post("sb-rest-api/employee/register").content(valueAsString).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn();
		
		String resultValue = result.getResponse().getContentAsString();
		EmployeeDto readValue = objMapper.readValue(resultValue, EmployeeDto.class);
		
		System.out.println("readValue : "+readValue);
		
		
	}

}
