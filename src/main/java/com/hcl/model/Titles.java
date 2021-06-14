package com.hcl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
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
	@NotNull(message = "Title can not be null...")
	@ApiModelProperty(notes = "Title" ,required = true)
	private String title;

	
	@Column(name = "from_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "From Date" ,required = true)
	@NotNull(message = "From Date can not be null...")
	@FutureOrPresent(message = "Hire Date Should be Furture Date or Present Date")	
	private LocalDate from_date;
	
	@Column(name = "to_date" )
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "To Date" ,required = true)
	@NotNull(message = "To Date can not be null...")
	@Future(message = " To Date Should be Furture Date")
	private LocalDate to_date;
	

	
}
