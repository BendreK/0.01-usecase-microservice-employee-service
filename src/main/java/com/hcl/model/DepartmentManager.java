package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dept_manager")
@IdClass(DepartmentManagerPk.class)
public class DepartmentManager {

	@Id
	@Column(name = "emp_no")
	private Integer emp_no;

	@Id
	@Column(name = "dept_no")
	private Integer dept_no;
	
	@Column(name = "from_date")
	private LocalDate from_date;
	
	@Column(name = "to_date")
	private LocalDate to_date;

}
