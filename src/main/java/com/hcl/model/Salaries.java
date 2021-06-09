package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "salaries")
@IdClass(SalaryPk.class)
public class Salaries {
	
	@Id
	@Column(name = "emp_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer emp_no ;
	
	@Id
	@Column(name = "salary",nullable = false,unique = true)
	private Integer salary;
	
	@Id
	@Column(name = "to_date",nullable = false,unique = true )
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate to_date;
	
	@Column(name = "from_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate from_date;
	
	

}
