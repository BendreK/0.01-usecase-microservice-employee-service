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
public class TitlePk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Column(name = "title")
	private String title;
	
	@Column(name = "emp_no" )
	private Integer emp_no ;
}
