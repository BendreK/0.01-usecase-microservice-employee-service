package com.hcl.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.EmployeeDto;
import com.hcl.dto.EmployeeRequest;
import com.hcl.model.Employees;
import com.hcl.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Employees", consumes ="Operations on Employees data")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("/getAllEmployeeData")
	@ApiOperation(value = "Get all Employees List. " ,response =Employees.class)
	public List<EmployeeDto> getAllEmployeeList(){
		logger.info("Inside controller of getAllEmployeeList..");
		return employeeService.getAllEmployeeList();
	}
			

	@PostMapping("/saveEmployee")
	@ApiOperation(value = "Save Employees Data " ,response =Employees.class)
	public String saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		logger.info("Inside controller of saveEmployee..");
		return employeeService.saveEmployee(employeeDto);
	}

	@DeleteMapping("/deleteEmployee/{empNo}")
	@ApiOperation(value = "Delete Employees Data by EmpNo " ,response =Employees.class)
	public String deleteEmployeeById(@Valid @PathVariable("empNo") int empNo) {
		logger.info("Inside controller of deleteEmployeeById..");
		return employeeService.deleteEmployeeById(empNo);
	}
	
	@PutMapping("/updateEmployeeData")
	@ApiOperation(value = "Update Employees Data " ,response =Employees.class)
	public EmployeeDto updateEmployeeData(@Valid @RequestBody EmployeeDto employeeDto) {
		
		logger.info("Inside controller of deleteEmployeeById..");
		return employeeService.updateEmployeeData(employeeDto);
	}
	
	
	@PostMapping("/serachRecord")
	@ApiOperation(value = "Search Employees Record by LastName, BirthDate and Gender " ,response =Employees.class)
	public EmployeeDto serachByLastNameAndBirthDateAndGender(@Valid @RequestBody  EmployeeRequest employeeRequest) {
		logger.info("Inside controller of serachByLastNameAndBirthDateAndGender..");
		return employeeService.serachByLastNameAndBirthDateAndGender(employeeRequest);
	}
	
}
