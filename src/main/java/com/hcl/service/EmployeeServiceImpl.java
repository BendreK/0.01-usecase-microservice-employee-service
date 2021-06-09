package com.hcl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dto.EmployeeDto;
import com.hcl.model.Employee;
import com.hcl.model.Salaries;
import com.hcl.model.Titles;
import com.hcl.repository.EmployeeRepository;
import com.hcl.repository.SalaryRepository;
import com.hcl.repository.TitlesRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TitlesRepository titlesRepository;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public List<EmployeeDto> getAllEmployeeList() {
		logger.info("inside service of getAllEmployeeList.");
		List<Employee> empList=employeeRepository.findAll();
		System.out.println("List-->"+empList);
		return empList.stream().map(emp-> modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());		
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		logger.info("inside service of saveEmployee.");		
		Employee emp= modelMapper.map(employeeDto, Employee.class);
		Titles title= new Titles();	
		Salaries salary= new Salaries();
		Employee empData=employeeRepository.save(emp);
		
		title.setEmp_no(empData.getEmp_no());
		title.setTitle(employeeDto.getTitlesList().get(0).getTitle());
		title.setFrom_date(employeeDto.getTitlesList().get(0).getFrom_date());
		title.setTo_date(employeeDto.getTitlesList().get(0).getTo_date());
		titlesRepository.save(title);		
		
		salary.setEmp_no(empData.getEmp_no());
		salary.setFrom_date(employeeDto.getSalariesList().get(0).getFrom_date());
		salary.setSalary(employeeDto.getSalariesList().get(0).getSalary());
		salary.setTo_date(employeeDto.getSalariesList().get(0).getTo_date());
		
		salaryRepository.save(salary);
		
		return modelMapper.map(empData, EmployeeDto.class);		
	}

//	@Override
//	public EmployeeDto getEmployeeById(int emp_No) {
////		logger.info("inside service of getEmployeeById.");
////		Employee emp=employeeRepository.findByEmp_No(emp_No);
////		if(emp!=null) {
////			return modelMapper.map(emp, EmployeeDto.class);
////		}
//		return null;
//	}

}
