package com.hcl.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeptManagerPk implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_no",nullable = false,unique = true)
	private Integer emp_no;
	
	@Column(name = "dept_no",nullable = false,unique = true)
	private String dept_no;
}
