package com.hcl.service;

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

import com.hcl.dto.DepartmentsDto;
import com.hcl.exception.EmployeeNotFoundException;
import com.hcl.model.Departments;
import com.hcl.repository.DepartmentsRepository;

@Service

public class DepartmentsServiceImpl implements DepartmentsService{
	
	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(DepartmentsServiceImpl.class);

	@Override
	public List<DepartmentsDto> getAllDepartmentsData() {
		logger.info("inside service of getAllDepartmentsData.");
		List<Departments> deptList= departmentsRepository.findAll();
		System.out.println("dept-->"+deptList);
		return deptList.stream().map(dept->modelMapper.map(dept, DepartmentsDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public DepartmentsDto saveDepartmentsData(DepartmentsDto departmentsDto) {
		logger.info("inside service of saveDepartmentsData.");		
		Departments dept= new Departments();		
		dept.setDept_no(departmentsDto.getDept_no());
		dept.setDept_name(departmentsDto.getDept_name());
		Departments dd = departmentsRepository.save(dept);			
		return modelMapper.map(dd, DepartmentsDto.class);
	}

	@Override
	public String deleteDepartmentByName(String dept_No) {
		logger.info("inside service of deleteDepartmentByName.");
		Optional<Departments> dept= departmentsRepository.findById(dept_No);
		if(! ObjectUtils.isEmpty(dept)) {
			Departments deptt= dept.get();
			departmentsRepository.delete(deptt);
			return "Department deleted successfully..";
		}
		throw new EmployeeNotFoundException("Department With this Id does not exists..");
	}

	@Override
	@Transactional
	public DepartmentsDto updateDepartmentsData(DepartmentsDto departmentsDto) {
		logger.info("inside service of updateDepartmentsData.");
		Optional<Departments> dept= departmentsRepository.findById(departmentsDto.getDept_no());
		
		if(! ObjectUtils.isEmpty(dept)) {
			Departments deptt= dept.get();
			deptt.setDept_name(departmentsDto.getDept_name());
			departmentsRepository.save(deptt);
			return modelMapper.map(deptt, DepartmentsDto.class);
		}
		throw new EmployeeNotFoundException("Department With this Id does not exists.");
	}

}
