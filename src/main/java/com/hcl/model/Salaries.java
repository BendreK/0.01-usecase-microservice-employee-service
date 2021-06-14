package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Salary" ,required = true)
	@NotNull(message = "Salary can not be null...")
	@Positive
	private Integer salary;
	
	@Id
	@Column(name = "to_date",nullable = false,unique = true )
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "To Date" ,required = true)
	@NotNull(message = "To Date can not be null...")
	@Future(message = " To Date Should be Furture Date")
	private LocalDate to_date;
	
	@Column(name = "from_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "From Date" ,required = true)
	@NotNull(message = "From Date can not be null...")
	@FutureOrPresent(message = "From Date Should be Furture Date or Present Date")
	private LocalDate from_date;
	
	

}
