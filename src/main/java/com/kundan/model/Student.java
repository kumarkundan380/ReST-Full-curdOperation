package com.kundan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4143413597480394738L;

	@Id
	@GeneratedValue
	@Column(name = "student_id")
	private Integer studentId;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_fee")
	private Double studentFee;
	
	@Column(name = "student_email")
	private String studentEmail;
	
	@Column(name = "student_course")
	private String studentCourse;
	
	@Column(name = "student_address")
	private String studentAddress;

}
