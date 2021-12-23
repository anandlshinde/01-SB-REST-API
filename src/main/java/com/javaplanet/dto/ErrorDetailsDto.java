package com.javaplanet.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailsDto {

	private Date errorDate;
	private String message;
	private String details;
}
