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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dept_emp")
@IdClass(DeptEmpPk.class)
public class DeptEmp {

	@Id
	@Column(name = "emp_no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer emp_no;

	@Id
	@Column(name = "dept_no",nullable = false,unique = true)	
	@ApiModelProperty(notes = "Department No" ,required = true)
	@NotNull(message = "Depatment No can not be null...")
	private String dept_no;
	
	@Column(name = "from_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "From Date" ,required = true)
	@NotNull(message = "From Date can not be null...")
	@FutureOrPresent(message = "From Date Should be Furture Date or Present Date")
	private LocalDate from_date;
	
	@Column(name = "to_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "To Date" ,required = true)
	@NotNull(message = "To Date can not be null...")
	@Future(message = " To Date Should be Furture Date")
	private LocalDate to_date;
	


}
