package com.hcl.employeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.hcl.dto.EmployeeDto;
import com.hcl.dto.EmployeeRequest;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.model.DeptEmp;
import com.hcl.model.DeptManager;
import com.hcl.model.Employees;
import com.hcl.model.Salaries;
import com.hcl.model.Titles;
import com.hcl.repository.EmployeeRepository;
import com.hcl.service.EmployeeServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	static EmployeeRequest employeeRequest;
	static EmployeeDto employeeDto;

	static Employees employees;
	static List<Employees> empList;

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

		employeeDtoList = new ArrayList<EmployeeDto>();
		employeeDto = new EmployeeDto();

		titles = new Titles();
		titlesList = new ArrayList<Titles>();

		salaries = new Salaries();
		SalariesList = new ArrayList<Salaries>();

		deptManager = new DeptManager();
		deptManagerList = new ArrayList<DeptManager>();

		deptEmp = new DeptEmp();
		deptEmpList = new ArrayList<DeptEmp>();

		String birthDate = "1985-01-11";
		employeeDto.setBirthDate(LocalDate.parse(birthDate));
		employeeDto.setEmp_no(11);
		employeeDto.setFirst_name("Shreya");
		employeeDto.setLastName("Patil");
		employeeDto.setGender("F");
		String hireDate = "2021-01-01";
		employeeDto.setHire_date(LocalDate.parse(hireDate));

		titles.setEmp_no(11);
		String fromDate = "2021-01-01";
		titles.setFrom_date(LocalDate.parse(fromDate));
		titles.setTitle("Developer");
		String toDate = "9999-01-01";
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

		employees = new Employees();
		empList = new ArrayList<Employees>();

		employees.setBirthDate(LocalDate.parse(birthDate));
		employees.setEmp_no(11);
		employees.setFirst_name("Shreya");
		employees.setLastName("Patil");
		employees.setGender("F");
		employees.setHire_date(LocalDate.parse(hireDate));

		employees.setTitlesList(titlesList);
		employees.setSalariesList(SalariesList);
		employees.setDepartmentManagerList(deptManagerList);
		employees.setDeptEmpList(deptEmpList);

		empList.add(employees);

		employeeRequest = new EmployeeRequest();

		employeeRequest.setBirth_date(LocalDate.parse(birthDate));
		employeeRequest.setGender("F");
		employeeRequest.setLast_name("Patil");

	}

	@Test
	@DisplayName("Get all Employees List")
	public void getAllEmployeeListTest() {

		when(employeeRepository.findAll()).thenReturn(empList);

		List<EmployeeDto> list = empList.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
				.collect(Collectors.toList());

		List<EmployeeDto> result = employeeServiceImpl.getAllEmployeeList();

		assertEquals(list, result);
	}
	
	
	
	
	
	@Test
	@DisplayName("Delete Employees Data by EmpNo :Positive Case")
	public void deleteEmployeeByIdTest() {
		
		Mockito.when(employeeRepository.findById(11)).thenReturn(Optional.of(employees)).thenReturn(null);
		employeeRepository.delete(employees);
		
		 String result = employeeServiceImpl.deleteEmployeeById(11);
		 
		  Mockito.verify(employeeRepository, times(1))
          .delete(employees);
		  assertEquals(result, "Employees deleted successfully..");
		
		
	}

	@Test
	@DisplayName("Delete Employees Data by EmpNo :Negative Case")
	public void deleteEmployeeByIdTest1() {
		Mockito.when(employeeRepository.findById(11)).thenThrow(EmployeeNotFoundException.class);
		
		assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.deleteEmployeeById(11));
	}
	
	@Test
	@DisplayName("Search Employees Record by LastName, BirthDate and Gender ")
	public void serachByLastNameAndBirthDateAndGender() {
		
		Mockito.when(employeeRepository.findByLastNameAndGenderAndBirthDate(employeeRequest.getLast_name(), employeeRequest.getGender(), employeeRequest.getBirth_date()))
		.thenReturn(employees);
		
		EmployeeDto dto= modelMapper.map(employees, EmployeeDto.class);
		EmployeeDto nemp= employeeServiceImpl.serachByLastNameAndBirthDateAndGender(employeeRequest);
		
		assertEquals(dto, nemp);
	}
	
	@Test
	@DisplayName("Search Employees Record by LastName, BirthDate and Gender :Negative Case ")
	public void serachByLastNameAndBirthDateAndGender1() {
		Mockito.when(employeeRepository.findByLastNameAndGenderAndBirthDate(employeeRequest.getLast_name(), employeeRequest.getGender(), employeeRequest.getBirth_date()))
		.thenThrow(EmployeeNotFoundException.class);
		
		assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.serachByLastNameAndBirthDateAndGender(employeeRequest));
	}


}
