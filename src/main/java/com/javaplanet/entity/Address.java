package com.javaplanet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.javaplanet.utils.SequenceIdGenerator;

import lombok.Data;
@Data
@Entity
@Table(name = "tbl_mst_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_seq")
	@GenericGenerator(
			name = "address_seq",
			strategy = "com.javaplanet.utils.SequenceIdGenerator",
			parameters = {
					@Parameter(name=SequenceIdGenerator.INCREMENT_PARAM,value = "1"),
					@Parameter(name=SequenceIdGenerator.VALUE_PREFIX_PARAMETER,value = "add_"),
					@Parameter(name=SequenceIdGenerator.NUMBER_PREFIX_PARAMETER,value = "%05d")
			}
			)
	@Column(name = "address_id")
	private String addId;
	
	private String street;
	
	private String city;
	
	private String pincode;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "employee_id",nullable = false)
	private Employee employee;
	
	
	
	

}
