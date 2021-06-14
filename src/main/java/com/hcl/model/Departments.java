package com.hcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String dept_no;
	
	@Column(name = "dept_name")
	private String dept_name;

	@Override
	public String toString() {
		return "Departments [dept_no=" + dept_no + ", dept_name=" + dept_name + "]";
	}
	
//	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false,mappedBy = "departments")	
//	@JoinColumn(name="dept_no",nullable = false,updatable = false, insertable = false)
//	private List<DeptEmp>deptEmpList;
	
	
//	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = false,mappedBy = "departments")
//	@JoinColumn(name="dept_no",nullable = false,updatable = false, insertable = false)
//	private List<DeptManager> departmentManagerList;
	
	
	
}
