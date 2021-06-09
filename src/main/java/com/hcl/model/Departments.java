package com.hcl.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Departments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String dept_no;
	
	@Column(name = "dept_name")
	private String dept_name;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)	
	@JoinColumn(name="dept_no")
	private List<DeptEmp>deptEmpList;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
	@JoinColumn(name="dept_no")
	private List<DepartmentManager> departmentManagerList;
	
}
