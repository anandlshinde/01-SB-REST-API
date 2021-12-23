package com.javaplanet.dto;

import javax.validation.constraints.Email;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Message {
	
	@NonNull
	@Email
	private String recipient;
	
	private String subject;
	
	private String message;
	
	
	
	
	
	

}
