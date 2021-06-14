package com.hcl.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsDto {

	@ApiModelProperty(notes = "Depatment No" ,required = true)
	@NotEmpty(message = "Depatment No can not be null...")
	private String dept_no;
	
	@ApiModelProperty(notes = "Depatment Name" ,required = true)
	@NotEmpty(message = "Depatment Name can not be null...")
	private String dept_name;
//	private List<DeptEmp>deptEmpList;
//	private List<DeptManager> departmentManagerList;
}
