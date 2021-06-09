package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.DeptEmp;
import com.hcl.model.DeptEmpPk;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpPk>{

}
