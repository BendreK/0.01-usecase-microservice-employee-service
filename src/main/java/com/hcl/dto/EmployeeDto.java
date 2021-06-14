package com.hcl.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcl.model.DeptEmp;
import com.hcl.model.DeptManager;
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
	@NotNull(message = "Birth Date can not be null...")
	@Past(message = "Birth Date Should be Past Date")
	private LocalDate birthDate ;	
	
	@ApiModelProperty(notes = "first_name" ,required = true)
	@NotEmpty(message = "First Name can not be null...")
	private String first_name ;	
	
	@ApiModelProperty(notes = "last_name" ,required = true)
	@NotEmpty(message = "Last Name can not be null...")
	private String lastName;	
	
	@ApiModelProperty(notes = "gender" ,required = true)
	@NotNull(message = "Gender can not be null...")
	@Size(max=1, message = "Gender Write Female for 'F' and Male for 'M' respectively.")
	private String gender ;	
	
	@ApiModelProperty(notes = "hire_date" ,required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Hire Date can not be null...")
	@FutureOrPresent(message = "Hire Date Should be Furture Date or Present Date")
	private LocalDate hire_date;	
	
	private List<Titles> titlesList;	
	private List<Salaries> salariesList;
	private List<DeptManager> departmentManagerList;
	private List<DeptEmp> deptEmpList;

}
