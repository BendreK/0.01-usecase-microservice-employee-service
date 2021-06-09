package com.hcl.service;

import java.util.List;

import com.hcl.dto.EmployeeDto;

public interface EmployeeService {

	public List<EmployeeDto> getAllEmployeeList();
	
	//public EmployeeDto getEmployeeById( int emp_No);
	
	public EmployeeDto saveEmployee(EmployeeDto employeeDto);
}
