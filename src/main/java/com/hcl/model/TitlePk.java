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
public class TitlePk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Column(name = "title",nullable = false,unique = true)
	private String title;
	
	
	@Column(name = "emp_no",nullable = false,unique = true )
	private Integer emp_no ;
	
	@Column(name = "from_date" ,nullable = false,unique = true)
	private LocalDate from_date;
}
