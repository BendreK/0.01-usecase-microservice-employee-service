package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "titles")
@IdClass(TitlePk.class)
public class Titles {
	
	
	@Id
	@Column(name = "emp_no")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer emp_no ;
	
	@Id	
	@Column(name = "title",nullable = false,unique = true)
	private String title;

	
	@Column(name = "from_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate from_date;
	
	@Column(name = "to_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate to_date;
	
	
	
}
