package com.hcl.service;

import java.util.List;

import com.hcl.dto.EmployeeDto;

public interface EmployeeService {

	public List<EmployeeDto> getAllEmployeeList();	
	
	public String saveEmployee(EmployeeDto employeeDto);
	
	public String deleteEmployeeById(int empNo);
	
	
}
