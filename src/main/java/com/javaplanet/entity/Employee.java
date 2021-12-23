package com.javaplanet.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import com.javaplanet.utils.SequenceIdGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_mst_employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq")
	@GenericGenerator(
				name="employee_seq",
				strategy = "com.javaplanet.utils.SequenceIdGenerator",
				parameters = {
						@Parameter(name=SequenceIdGenerator.INCREMENT_PARAM,value = "1"),
						@Parameter(name=SequenceIdGenerator.VALUE_PREFIX_PARAMETER,value = "EMP_"),
						@Parameter(name=SequenceIdGenerator.NUMBER_PREFIX_PARAMETER,value = "%05d")
				}
			)
	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile_number")
	private String mobileNo;
	
	@Column(name = "photo_path")
	private String photoPath;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	
	@OneToMany(targetEntity = Address.class,mappedBy = "employee",fetch = FetchType.LAZY,
			    cascade = CascadeType.ALL)
	private Set address;
	

}
