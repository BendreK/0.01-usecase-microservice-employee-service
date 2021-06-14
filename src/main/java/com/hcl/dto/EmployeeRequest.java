package com.hcl.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

	@ApiModelProperty(notes = "last_name" ,required = true)
	@NotEmpty(message = "Last Name can not be null...")
	private String last_name;
	
	@ApiModelProperty(notes = "gender" ,required = true)
	@NotNull(message = "Gender can not be null...")
	@Size(max=1, message = "Gender Write Female for 'F' and Male for 'M' respectively.")
	private String gender ;
	
	@ApiModelProperty(notes = "birth_date" ,required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Birth Date can not be null...")
	@Past(message = "Birth Date Should be Past Date")
	private LocalDate birth_date ;	
	
	
}
