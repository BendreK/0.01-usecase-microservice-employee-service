package com.hcl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcl.dto.EmployeeDto;
import com.hcl.dto.EmployeeRequest;
import com.hcl.model.DeptEmp;
import com.hcl.model.DeptManager;
import com.hcl.model.Salaries;
import com.hcl.model.Titles;
import com.hcl.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	static EmployeeRequest employeeRequest;
	static EmployeeDto employeeDto;
	
	static Titles titles;
	static List<Titles> titlesList;
	
	static Salaries salaries;
	static List<Salaries> SalariesList;
	
	static DeptManager deptManager;
	static List<DeptManager> deptManagerList;
	
	static DeptEmp deptEmp;
	static List<DeptEmp> deptEmpList;
	
	static List<EmployeeDto> employeeDtoList;
	
	
	@BeforeAll
	public static void setUp() {
		
		employeeDtoList= new ArrayList<EmployeeDto>();
		employeeDto= new EmployeeDto();
		
		titles= new Titles();
		titlesList= new ArrayList<Titles>();
		
		salaries = new Salaries();
		SalariesList= new ArrayList<Salaries>();
		
		deptManager= new DeptManager();
		deptManagerList= new ArrayList<DeptManager>();
		
		
		deptEmp= new DeptEmp();
		deptEmpList= new ArrayList<DeptEmp>();
		
		
		String birthDate="1985-01-11";
		employeeDto.setBirthDate(LocalDate.parse(birthDate));
		employeeDto.setEmp_no(11);
		employeeDto.setFirst_name("Shreya");
		employeeDto.setLastName("Patil");
		employeeDto.setGender("F");
		String hireDate="2021-01-01";		
		employeeDto.setHire_date(LocalDate.parse(hireDate));
		
		titles.setEmp_no(11);
		String fromDate="2021-01-01";
		titles.setFrom_date(LocalDate.parse(fromDate));
		titles.setTitle("Developer");
		String toDate="9999-01-01";
		titles.setTo_date(LocalDate.parse(toDate));
		
		titlesList.add(titles);
		
		salaries.setEmp_no(11);
		salaries.setFrom_date(LocalDate.parse(fromDate));
		salaries.setTo_date(LocalDate.parse(toDate));
		salaries.setSalary(55555);
		
		SalariesList.add(salaries);
		
		deptManager.setDept_no("IT");
		deptManager.setEmp_no(11);
		deptManager.setFrom_date(LocalDate.parse(fromDate));
		deptManager.setTo_date(LocalDate.parse(toDate));
		deptManagerList.add(deptManager);
		
		deptEmp.setDept_no("IT");
		deptEmp.setEmp_no(11);
		deptEmp.setFrom_date(LocalDate.parse(fromDate));
		deptEmp.setTo_date(LocalDate.parse(toDate));
		deptEmpList.add(deptEmp);
		
		employeeDto.setTitlesList(titlesList);
		employeeDto.setSalariesList(SalariesList);
		employeeDto.setDepartmentManagerList(deptManagerList);
		employeeDto.setDeptEmpList(deptEmpList);
		
		employeeRequest= new EmployeeRequest();
		
		employeeRequest.setBirth_date(LocalDate.parse(birthDate));
		employeeRequest.setGender("F");
		employeeRequest.setLast_name("Patil");
		
		
	}

	
	
	@Test
	@DisplayName("Get all Employees List")
	public void getAllEmployeeListTest() {		
		// given or context
		when(employeeService.getAllEmployeeList()).thenReturn(employeeDtoList);		
		// when or event
		List<EmployeeDto> result= employeeController.getAllEmployeeList();		
		verify(employeeService).getAllEmployeeList();
		//outcome
		assertEquals(employeeDtoList, result);
	}
	
	@Test
	@DisplayName("Save Employees Data")
	public void saveEmployeeTest() {
		
		when(employeeService.saveEmployee(employeeDto)).thenReturn("Save Data Sucessfully");
		
		String result= employeeController.saveEmployee(employeeDto);
		verify(employeeService).saveEmployee(employeeDto);
		assertEquals("Save Data Sucessfully", result);			
	}
	
	@Test
	@DisplayName("Delete Employees Data by EmpNo")
	public void deleteEmployeeByIdTest() {
		
		when(employeeService.deleteEmployeeById(11)).thenReturn("Employees deleted successfully..");
		
		String result= employeeController.deleteEmployeeById(11);
		verify(employeeService).deleteEmployeeById(11);
		assertEquals("Employees deleted successfully..", result);
		
	}
	
	@Test
	@DisplayName("Update Employees Data")
	public void updateEmployeeData() {
		
		when(employeeService.updateEmployeeData(employeeDto)).thenReturn(employeeDto);
		
		EmployeeDto emp= employeeController.updateEmployeeData(employeeDto);
		verify(employeeService).updateEmployeeData(emp);
		assertEquals(employeeDto, emp);
	}
	
	
	@Test
	@DisplayName("Search Employees Record by LastName, BirthDate and Gender ")
	public void serachByLastNameAndBirthDateAndGender() {
		
		when(employeeService.serachByLastNameAndBirthDateAndGender(employeeRequest)).thenReturn(employeeDto);
		
		EmployeeDto emp= employeeController.serachByLastNameAndBirthDateAndGender(employeeRequest);
		verify(employeeService).serachByLastNameAndBirthDateAndGender(employeeRequest);
		assertEquals(employeeDto, emp);
	}
}
