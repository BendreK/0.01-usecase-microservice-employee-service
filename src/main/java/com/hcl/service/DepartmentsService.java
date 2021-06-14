package com.hcl.service;

import java.util.List;

import com.hcl.dto.DepartmentsDto;

public interface DepartmentsService {

	public List<DepartmentsDto> getAllDepartmentsData();
	
	public DepartmentsDto saveDepartmentsData(DepartmentsDto departmentsDto);
	
	public String deleteDepartmentByName(String dept_No);
	
	public DepartmentsDto updateDepartmentsData(DepartmentsDto departmentsDto); 
}
