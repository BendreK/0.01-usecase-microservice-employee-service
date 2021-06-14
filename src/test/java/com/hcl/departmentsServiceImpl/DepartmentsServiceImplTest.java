package com.hcl.departmentsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.hcl.dto.DepartmentsDto;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.model.Departments;
import com.hcl.repository.DepartmentsRepository;
import com.hcl.service.DepartmentsServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceImplTest {
	
	@Mock
	DepartmentsRepository departmentsRepository;
	
	@InjectMocks
	DepartmentsServiceImpl departmentsServiceImpl;
	
	@Mock
	private ModelMapper modelMapper;
	
	static DepartmentsDto departmentsDto;
	static List<DepartmentsDto> deptList;
	
	static Departments departments;
	static List<Departments> departmentsList;

	@BeforeAll
	public static void setUp() {
		
		departments= new Departments();
		departmentsList= new ArrayList<Departments>();
		
		departmentsDto= new DepartmentsDto();
		deptList= new ArrayList<DepartmentsDto>();
		
		departments.setDept_name("Team Lead");
		departments.setDept_no("IT");
		departmentsList.add(departments);
		
		departmentsDto.setDept_name("Team Lead");
		departmentsDto.setDept_no("IT");
		deptList.add(departmentsDto);
		
	}

	@Test
	@DisplayName("Get All Department List")
	public void getAllDepartmentsData() {		
		
		when(departmentsRepository.findAll()).thenReturn(departmentsList);
		
		List<DepartmentsDto> list = departmentsList.stream().map(dept -> modelMapper.map(dept, DepartmentsDto.class))
				.collect(Collectors.toList());

		List<DepartmentsDto> result = departmentsServiceImpl.getAllDepartmentsData();

		assertEquals(list, result);
	}
	
	@Test
	@DisplayName("Save Department Data")
	public void saveDepartmentsData() {
		
		when(departmentsRepository.save(departments)).thenReturn(departments);
		
		DepartmentsDto dept= modelMapper.map(departments, DepartmentsDto.class);
		
		DepartmentsDto result= departmentsServiceImpl.saveDepartmentsData(departmentsDto);
		
		assertEquals(dept, result);
	}
	
	@Test
	@DisplayName("Delete Department Data by DeptNo:Positive case")
	public void deleteDepartmentByName() {
		
		Mockito.when(departmentsRepository.findById("IT")).thenReturn(Optional.of(departments)).thenReturn(null);
	
		 String result =departmentsServiceImpl.deleteDepartmentByName("IT");
		
		  Mockito.verify(departmentsRepository, times(1))
          .delete(departments);
		  
		  assertEquals(result, "Department deleted successfully..");
	}
	
	@Test
	@DisplayName("Delete Department Data by DeptNo:Negative case")
	public void deleteDepartmentByName1() {
		
		Mockito.when(departmentsRepository.findById("IT")).thenThrow(EmployeeNotFoundException.class);
		
		assertThrows(EmployeeNotFoundException.class, () -> departmentsServiceImpl.deleteDepartmentByName("IT"));
	}
	
	@Test
	@DisplayName("Update Department Data:Positive case")
	public void updateDepartmentsData() {
		
		Mockito.when(departmentsRepository.findById("IT")).thenReturn(Optional.of(departments)).thenReturn(null);
		
		departmentsRepository.save(departments);
		
		DepartmentsDto dept= modelMapper.map(departments, DepartmentsDto.class);
		
		DepartmentsDto ndept= departmentsServiceImpl.updateDepartmentsData(departmentsDto);
		assertEquals(dept, ndept);
		
	}
	
	@Test
	@DisplayName("Update Department Data:Negative case")
	public void updateDepartmentsData1() {
		
		Mockito.when(departmentsRepository.findById("IT"))
		.thenThrow(EmployeeNotFoundException.class);
		
		assertThrows(EmployeeNotFoundException.class, () -> departmentsServiceImpl.updateDepartmentsData(departmentsDto));
	}
}
