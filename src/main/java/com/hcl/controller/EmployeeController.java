package com.hcl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.EmployeeDto;
import com.hcl.model.Employee;
import com.hcl.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Employee", consumes ="Operations on Employee data")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("/getAllEmployeeData")
	@ApiOperation(value = "Get all Employee List. " ,response =Employee.class)
	public List<EmployeeDto> getAllEmployeeList(){
		logger.info("Inside controller of getAllEmployeeList..");
		return employeeService.getAllEmployeeList();
	}
	
		

	@PostMapping("/save")
	@ApiOperation(value = "Save Employee Data " ,response =Employee.class)
	public String saveEmployee(@RequestBody EmployeeDto employeeDto) {
		logger.info("Inside controller of saveEmployee..");
		return employeeService.saveEmployee(employeeDto);
	}

	@DeleteMapping("/deleteEmployee/{empNo}")
	@ApiOperation(value = "Delete Employee Data " ,response =Employee.class)
	public String deleteEmployeeById(@PathVariable("empNo") int empNo) {
		logger.info("Inside controller of deleteEmployeeById..");
		return employeeService.deleteEmployeeById(empNo);
	}
	
	

	
}
