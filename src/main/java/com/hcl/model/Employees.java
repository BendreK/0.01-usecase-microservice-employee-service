package com.hcl.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employees {
	
	@Id	
	@Column(name = "emp_no")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment",strategy = "increment")
	private Integer emp_no;
	
	@Column(name = "birth_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate ; 
	
	@Column(name = "first_name")
	private String first_name ; 
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private String gender ;
	
	@Column(name = "hire_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate hire_date;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name="emp_no",nullable = false,updatable = false, insertable = false)
	private List<Titles> titlesList;	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name="emp_no",nullable = false,updatable = false, insertable = false)
	private List<Salaries> salariesList;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="emp_no",nullable = false,updatable = false, insertable = false)
	private List<DeptEmp> deptEmpList;



	
	//@LazyCollection(LazyCollectionOption.FALSE)
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)		
//	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="emp_no", updatable = false, insertable = false)
	private List<DeptManager> departmentManagerList;




	@Override
	public String toString() {
		return "Employees [emp_no=" + emp_no + ", birthDate=" + birthDate + ", first_name=" + first_name + ", lastName="
				+ lastName + ", gender=" + gender + ", hire_date=" + hire_date + ", titlesList=" + titlesList
				+ ", salariesList=" + salariesList + ", deptEmpList=" + deptEmpList + ", departmentManagerList="
				+ departmentManagerList + "]";
	}




	




	

	

	
	
	
}
