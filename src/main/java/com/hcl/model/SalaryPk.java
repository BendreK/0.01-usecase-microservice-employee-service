package com.hcl.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SalaryPk implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Column(name = "emp_no" )
	private Integer emp_no ;
	
	@Column(name = "from_date" )
	private LocalDate from_date;

}
