package com.hcl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcl.dto.DepartmentsDto;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.service.DepartmentsService;

@ExtendWith(MockitoExtension.class)
public class DepartmentsControllerTest {

	@Mock
	DepartmentsService departmentsService;
	
	@InjectMocks
	DepartmentsController departmentsController;
	
	static DepartmentsDto departmentsDto;
	static List<DepartmentsDto> deptList;
	
	@BeforeAll
	public static void setUp() {
		departmentsDto= new DepartmentsDto();
		deptList = new ArrayList<DepartmentsDto>();
		
		departmentsDto.setDept_name("Team Lead");
		departmentsDto.setDept_no("IT");
		
		deptList.add(departmentsDto);
		
	}
	
	@Test
	@DisplayName("Get All Department List")
	public void getAllDepartmentsData() {		
		when(departmentsService.getAllDepartmentsData()).thenReturn(deptList);
		
		List<DepartmentsDto> list= departmentsController.getAllDepartmentsData();
		verify(departmentsService).getAllDepartmentsData();
		assertEquals(deptList, list);
	}
	
	@Test
	@DisplayName("Save Department Data")
	public void saveDepartmentsData() {
		
		when(departmentsService.saveDepartmentsData(departmentsDto)).thenReturn(departmentsDto);
		
		DepartmentsDto dept= departmentsController.saveDepartmentsData(departmentsDto);
		
		verify(departmentsService).saveDepartmentsData(departmentsDto);
		
		assertEquals(departmentsDto, dept);
	}
	
	@Test
	@DisplayName("Delete Department Data by DeptNo:Positive case")
	public void deleteDepartmentByName() {
		
		when(departmentsService.deleteDepartmentByName("IT")).thenReturn("Department deleted successfully..");
		
		String result= departmentsController.deleteDepartmentByName("IT");
		verify(departmentsService).deleteDepartmentByName("IT");
		
		assertEquals("Department deleted successfully..", result);
	}
	
	@Test
	@DisplayName("Delete Department Data by DeptNo:Negative case")
	public void deleteDepartmentByName1() {
		when(departmentsService.deleteDepartmentByName("IT")).thenThrow(EmployeeNotFoundException.class);
		assertThrows(EmployeeNotFoundException.class, () -> departmentsController.deleteDepartmentByName("IT"));
	}
	
	@Test
	@DisplayName("Update Department Data:Positive case")
	public void updateDepartmentsData() {
		
		Mockito.when(departmentsService.updateDepartmentsData(departmentsDto)).thenReturn(departmentsDto);
		
		DepartmentsDto dept= departmentsController.updateDepartmentsData(departmentsDto);
		
		verify(departmentsService).updateDepartmentsData(departmentsDto);
		
		assertEquals(departmentsDto, dept);
		
	}
	
	@Test
	@DisplayName("Update Department Data:Negative case")
	public void updateDepartmentsData1() {
		Mockito.when(departmentsService.updateDepartmentsData(departmentsDto))
		.thenThrow(EmployeeNotFoundException.class);
		
		assertThrows(EmployeeNotFoundException.class, () -> departmentsController.updateDepartmentsData(departmentsDto));
	}
}
