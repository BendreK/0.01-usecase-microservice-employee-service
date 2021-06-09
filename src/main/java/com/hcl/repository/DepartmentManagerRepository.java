package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.DepartmentManager;
import com.hcl.model.DepartmentManagerPk;


@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, DepartmentManagerPk>{

}
