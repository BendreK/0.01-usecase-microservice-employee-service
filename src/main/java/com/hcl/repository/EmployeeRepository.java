package com.hcl.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dto.EmployeeRequest;
import com.hcl.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {	
	

	Employees findByLastNameAndGenderAndBirthDate(String last_name, String gender, LocalDate birth_date);

	

	
}
