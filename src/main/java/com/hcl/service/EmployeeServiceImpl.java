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
import org.springframework.util.ObjectUtils;

import com.hcl.dto.EmployeeDto;
import com.hcl.dto.EmployeeRequest;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.model.DeptManager;
import com.hcl.model.DeptEmp;
import com.hcl.model.Employees;
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
		List<Employees> empList = employeeRepository.findAll();		
		return empList.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public String saveEmployee(EmployeeDto employeeDto) {
		logger.info("inside service of saveEmployee.");			
		
		Employees newEmp = saveEmployeeData(employeeDto);		
		List<Titles> title=	saveTitleData(newEmp,employeeDto);		
		List<Salaries> salary= saveSalaryData(newEmp,employeeDto);		
		List<DeptManager> deptManagerList= saveDepartmentManager(newEmp, employeeDto);
		List<DeptEmp> deptEmpList= saveDeptEmp(newEmp,employeeDto);
		newEmp.setTitlesList(title);
		newEmp.setSalariesList(salary);
		newEmp.setDepartmentManagerList(deptManagerList);
		newEmp.setDeptEmpList(deptEmpList);
		System.out.println("List before save--->" + newEmp);
	    employeeRepository.save(newEmp);
	   		
		return "Save Data Sucessfully";
	}
	
	
	

	private List<DeptEmp> saveDeptEmp(Employees newEmp, EmployeeDto employeeDto) {
		List<DeptEmp> list= new ArrayList<>();
		for (DeptEmp deptEmp : employeeDto.getDeptEmpList()) {
			DeptEmp dp= new DeptEmp();
			dp.setEmp_no(newEmp.getEmp_no());
			dp.setDept_no(deptEmp.getDept_no());
			dp.setFrom_date(deptEmp.getFrom_date());
			dp.setTo_date(deptEmp.getTo_date());
			list.add(dp);
		}
		return list;
	}

	private List<DeptManager> saveDepartmentManager(Employees newEmp, EmployeeDto employeeDto) {
		List<DeptManager> list= new ArrayList<DeptManager>();
		
		for (DeptManager dept : employeeDto.getDepartmentManagerList()) {
			DeptManager deManager= new DeptManager();
			deManager.setEmp_no(newEmp.getEmp_no());
			deManager.setDept_no(dept.getDept_no());
			deManager.setFrom_date(dept.getFrom_date());
			deManager.setTo_date(dept.getTo_date());
			list.add(deManager);
		}
		return list;
	}

	private List<Salaries> saveSalaryData(Employees emp, EmployeeDto employeeDto) {
		List<Salaries> salary = new ArrayList<Salaries>();
		
		for (Salaries salaries : employeeDto.getSalariesList()) {
			Salaries ss = new Salaries();
			ss.setEmp_no(emp.getEmp_no());
			ss.setFrom_date(salaries.getFrom_date());
			ss.setSalary(salaries.getSalary());
			ss.setTo_date(salaries.getTo_date());
			salary.add(ss);
	
		}
		return salary;
	}
	

	private List<Titles> saveTitleData(Employees emp, EmployeeDto employeeDto) {
		List<Titles> title = new ArrayList<>();
		for (Titles title1 : employeeDto.getTitlesList()) {
			Titles tt = new Titles();
			tt.setEmp_no(emp.getEmp_no());
			tt.setTitle(title1.getTitle());
			tt.setFrom_date(title1.getFrom_date());
			tt.setTo_date(title1.getTo_date());
			title.add(tt);		
		}
		return title;
		
	}

	private Employees saveEmployeeData(EmployeeDto employeeDto) {
		Employees newEmp= new Employees();
		newEmp.setBirthDate(employeeDto.getBirthDate());
		newEmp.setFirst_name(employeeDto.getFirst_name());
		newEmp.setLastName(employeeDto.getLastName());
		newEmp.setGender(employeeDto.getGender());
		newEmp.setHire_date(employeeDto.getHire_date());				
		Employees empData = employeeRepository.save(newEmp);
		return empData;	
	}

	@Override
	public String deleteEmployeeById(int empNo) {
		try {
		Optional<Employees> emp = employeeRepository.findById(empNo);
		if (emp != null) {
			employeeRepository.deleteById(empNo);			
		}
		}catch(Exception e) {
		throw new EmployeeNotFoundException("Employees with this empNo not found to delete!..");
		}
		return "Employees deleted successfully..";
	}

	@Override
	@Transactional
	public EmployeeDto updateEmployeeData(EmployeeDto employeeDto) {
		Optional<Employees> emp= employeeRepository.findById(employeeDto.getEmp_no());
		if(emp.isPresent()){
			
			Employees newEmp = emp.get();		
			List<Titles> title=	saveTitleData(newEmp,employeeDto);		
			List<Salaries> salary= saveSalaryData(newEmp,employeeDto);	
			newEmp.getTitlesList().clear();
			newEmp.getTitlesList().addAll(title);
			
			newEmp.getSalariesList().clear();
			newEmp.getSalariesList().addAll(salary);
			Employees emp1=employeeRepository.save(newEmp);
			return modelMapper.map(emp1, EmployeeDto.class);
		}
		
		throw new EmployeeNotFoundException("Employees With this id does not found!..");
	}

	@Override
	public EmployeeDto serachByLastNameAndBirthDateAndGender(EmployeeRequest employeeRequest) {
		
		Employees emp= employeeRepository.findByLastNameAndGenderAndBirthDate(employeeRequest.getLast_name(),employeeRequest.getGender(),employeeRequest.getBirth_date());
		if(!ObjectUtils.isEmpty(emp)) {
			
			return modelMapper.map(emp, EmployeeDto.class);
		}
		throw new EmployeeNotFoundException("Employee With this details not found..");
	}



}
