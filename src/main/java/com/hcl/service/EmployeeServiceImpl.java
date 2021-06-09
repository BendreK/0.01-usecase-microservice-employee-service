package com.hcl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dto.EmployeeDto;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.model.Employee;
import com.hcl.model.Salaries;
import com.hcl.model.Titles;
import com.hcl.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;	

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public List<EmployeeDto> getAllEmployeeList() {
		logger.info("inside service of getAllEmployeeList.");
		List<Employee> empList = employeeRepository.findAll();		
		return empList.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public String saveEmployee(EmployeeDto employeeDto) {
		logger.info("inside service of saveEmployee.");	
		List<Titles> title = new ArrayList<>();
		List<Salaries> salary = new ArrayList<Salaries>();
		Employee newEmp= new Employee();
		newEmp.setBirth_date(employeeDto.getBirth_date());
		newEmp.setFirst_name(employeeDto.getFirst_name());
		newEmp.setLast_name(employeeDto.getLast_name());
		newEmp.setGender(employeeDto.getGender());
		newEmp.setHire_date(employeeDto.getHire_date());		
		
		Employee empData = employeeRepository.save(newEmp);

		for (Titles title1 : employeeDto.getTitlesList()) {
			Titles tt = new Titles();
			tt.setEmp_no(newEmp.getEmp_no());
			tt.setTitle(title1.getTitle());
			tt.setFrom_date(title1.getFrom_date());
			tt.setTo_date(title1.getTo_date());
			title.add(tt);		
		}

		for (Salaries salaries : employeeDto.getSalariesList()) {
			Salaries ss = new Salaries();
			ss.setEmp_no(newEmp.getEmp_no());
			ss.setFrom_date(salaries.getFrom_date());
			ss.setSalary(salaries.getSalary());
			ss.setTo_date(salaries.getTo_date());
			salary.add(ss);
	
		}
		newEmp.setTitlesList(title);
		newEmp.setSalariesList(salary);
		System.out.println("List before save--->" + newEmp);
	    employeeRepository.save(newEmp);
		
	    System.out.println("List After save--->" + empData);			
		return "Save Data Sucessfully";
	}

	@Override
	public String deleteEmployeeById(int empNo) {
		Optional<Employee> emp = employeeRepository.findById(empNo);
		if (emp != null) {
			employeeRepository.deleteById(empNo);
			return "Employee deleted successfully..";
		}
		throw new EmployeeNotFoundException("Employee with this empNo not found to delete!..");

	}



}
