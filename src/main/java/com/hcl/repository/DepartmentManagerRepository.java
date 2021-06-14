package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.DeptManager;
import com.hcl.model.DeptManagerPk;


@Repository
public interface DepartmentManagerRepository extends JpaRepository<DeptManager, DeptManagerPk>{

}
