package com.kundan.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "studentId")
	List<Address> studentAddress;
}
