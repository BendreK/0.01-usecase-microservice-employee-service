package com.hcl.service;

import java.util.List;

import com.hcl.dto.EmployeeDto;
import com.hcl.dto.EmployeeRequest;

public interface EmployeeService {

	public List<EmployeeDto> getAllEmployeeList();	
	
	public String saveEmployee(EmployeeDto employeeDto);
	
	public String deleteEmployeeById(int empNo);

	public EmployeeDto updateEmployeeData(EmployeeDto employeeDto);
	
	
	public EmployeeDto serachByLastNameAndBirthDateAndGender(EmployeeRequest employeeRequest);
	
	
}
