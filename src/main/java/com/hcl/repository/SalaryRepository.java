package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.Salaries;
import com.hcl.model.SalaryPk;

@Repository
public interface SalaryRepository extends JpaRepository<Salaries, SalaryPk> {

}
