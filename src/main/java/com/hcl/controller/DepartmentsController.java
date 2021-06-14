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

import com.hcl.dto.DepartmentsDto;
import com.hcl.model.Departments;
import com.hcl.service.DepartmentsService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DepartmentsController {
	
	
	@Autowired
	private DepartmentsService departmentsService;
	
	private static final Logger logger=LoggerFactory.getLogger(DepartmentsController.class);

	@GetMapping("/getAllDepartmentsData")
	@ApiOperation(value = "Get All Department List" ,response =Departments.class)
	public List<DepartmentsDto> getAllDepartmentsData(){
		logger.info("Inside controller of getAllDepartmentsData..");
		return departmentsService.getAllDepartmentsData();
	}

	@PostMapping("/saveDepartmentsData")
	@ApiOperation(value = "Save Department Data" ,response =Departments.class)
	public DepartmentsDto saveDepartmentsData(@Valid @RequestBody DepartmentsDto departmentsDto) {
		logger.info("Inside controller of saveDepartmentsData..");
		return departmentsService.saveDepartmentsData(departmentsDto);
	}
	
	@DeleteMapping("/deleteDepartmentByDeptNo/{dept_No}")
	@ApiOperation(value = "Delete Department Data by DeptNo" ,response =Departments.class)
	public String deleteDepartmentByName(@Valid @PathVariable String dept_No) {
		logger.info("Inside controller of deleteDepartmentByName..");
		return departmentsService.deleteDepartmentByName(dept_No);
	}
	
	@PutMapping("/updateDepartment")
	@ApiOperation(value = "Update Department Data" ,response =Departments.class)
	public DepartmentsDto updateDepartmentsData(@Valid @RequestBody DepartmentsDto departmentsDto) {
		logger.info("Inside controller of updateDepartmentsData..");
		return departmentsService.updateDepartmentsData(departmentsDto);
	}
}
