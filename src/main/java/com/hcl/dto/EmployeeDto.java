package com.hcl.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcl.model.Salaries;
import com.hcl.model.Titles;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {	

	
	private int emp_no;		
	
	@ApiModelProperty(notes = "birth_date" ,required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth_date ;	
	
	@ApiModelProperty(notes = "first_name" ,required = true)
	private String first_name ;	
	
	@ApiModelProperty(notes = "last_name" ,required = true)
	private String last_name;	
	
	@ApiModelProperty(notes = "gender" ,required = true)
	private String gender ;	
	
	@ApiModelProperty(notes = "hire_date" ,required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate hire_date;	
	
	private List<Titles> titlesList;	
	private List<Salaries> salariesList;

}
